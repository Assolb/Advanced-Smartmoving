package com.sm.entities;

import com.sm.utils.CrawlingHandler;
import com.sm.utils.PullingHandler;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SteveEntity implements IAnimatable {

	private AnimationFactory factory = new AnimationFactory(this);

	@Override
	public void registerControllers(AnimationData data)
	{
		data.addAnimationController(new AnimationController(this, "moveController", 5, this::moveState));
		data.addAnimationController(new AnimationController(this, "attackController", 0, this::attackState));
	}
	
	private <P extends IAnimatable> PlayState moveState(AnimationEvent<SteveEntity> event)
	{
		EntityLivingBase player = (EntityLivingBase) event.getExtraData().get(1);
		if(player == null) return PlayState.CONTINUE;
		event.getController().transitionLengthTicks = 5;
		if(player.isRiding())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.sit", true));
		}
		else if(player.isPlayerSleeping())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.sleep", true));
		}
		else if(PullingHandler.pullingPlayers.containsKey(player.getName()))
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.pulling", true));
		}
		else if(CrawlingHandler.crawlingPlayers.contains(player.getName()))
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.crawling", true));
		}
		else if(player.isInWater() && underWater(player))
		{
			if(player.isSprinting())
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.runswim", true));
			else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.swim", true));
		}
		else if(player.isSneaking())
		{
			if(event.isMoving()) event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.sneakingwalk", true));
			else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.sneaking", true));
		}
		else if(player.isSprinting())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.run", true));
		}
		else if(event.isMoving())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.walk", true));
		}
		else
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.idle", true));
		}
		return PlayState.CONTINUE;
	}
	
	private <P extends IAnimatable> PlayState attackState(AnimationEvent<SteveEntity> event)
	{
		EntityLivingBase player = (EntityLivingBase) event.getExtraData().get(1);
		if(player == null) return PlayState.CONTINUE;
		if(player.swingProgress > 0)
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.steve.hit", true));
		}
		else if(event.getController().getCurrentAnimation() != null)
		{
			return PlayState.STOP;
		}
		return PlayState.CONTINUE;
	}
	
	public static boolean underWater(Entity player) 
	{
	      World world = player.getEntityWorld();
	      double eyeBlock = player.posY + (double)player.getEyeHeight() - 0.25D;
	      BlockPos blockPos = new BlockPos(player.posX, eyeBlock, player.posZ);
	      return world.getBlockState(blockPos).getMaterial() == Material.WATER && !(player.getRidingEntity() instanceof EntityBoat);
	}

	@Override
	public AnimationFactory getFactory()
	{
		return factory;
	}
}
