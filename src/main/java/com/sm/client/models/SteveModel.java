package com.sm.client.models;

import org.lwjgl.opengl.GL11;

import com.sm.entities.SteveEntity;
import com.sm.utils.PullingHandler;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SteveModel extends AnimatedGeoModel<SteveEntity> {
	
	@Override
	public ResourceLocation getModelLocation(SteveEntity object) 
	{
		return new ResourceLocation("sm", "geo/steve.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(SteveEntity object) 
	{
		return null;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(SteveEntity animatable) 
	{
		return new ResourceLocation("sm", "animations/steve.animation.json");
	}
	
	@Override
	public void setLivingAnimations(SteveEntity entity, Integer uniqueID, AnimationEvent customPredicate)
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		IBone rightarm = this.getAnimationProcessor().getBone("right_arm");
		IBone leftarm = this.getAnimationProcessor().getBone("left_arm");
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		EntityLivingBase living = ((EntityLivingBase)customPredicate.getExtraData().get(1));
	    float headPitch = extraData.headPitch;
	    if(living.isInWater() && entity.underWater(living))
	    {
	    	float rotate = extraData.headPitch + 70 > 90 ? 90 : extraData.headPitch + 70;
	    	head.setRotationX(rotate * ((float) Math.PI / 180F));
	    } 
	    else head.setRotationX((extraData.headPitch) * ((float) Math.PI / 180F));
	    if(PullingHandler.pullingPlayers.containsKey(living.getName()))
	    {
	    	BlockPos pos = PullingHandler.pullingPlayers.get(living.getName());
	    	double y = pos.getY() - living.posY;
	    	if(y < -0.3) y = -0.3f;
	    	if(y > 1) y = 1;
	    	rightarm.setRotationX((float) ((y * 90 + 45) * ((float) Math.PI / 180F)));
	    	leftarm.setRotationX((float) ((y * 90 + 45) * ((float) Math.PI / 180F)));
	    }
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        if(living.swingProgress > 0)
	    {
        	if(headPitch > -20)
        		rightarm.setRotationX(headPitch / 2 * ((float) Math.PI / 180F));
	    }
        
	}
	
	public void postRenderRightArmDown(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);

		bone = (GeoBone) getAnimationProcessor().getBone("right_arm");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("right_arm_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("right_arm_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderLeftArmDown(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);

		bone = (GeoBone) getAnimationProcessor().getBone("left_arm");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("left_arm_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("left_arm_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderRightArm(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);

		bone = (GeoBone) getAnimationProcessor().getBone("right_arm");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("right_arm_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderLeftArm(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);

		bone = (GeoBone) getAnimationProcessor().getBone("left_arm");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("left_arm_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderHead(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("head");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderLeftLeg(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("left_leg_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderLeftLegDown(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("left_leg_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("left_leg_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderRightLeg(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("right_leg_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderRightLegDown(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("right_leg_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("right_leg_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}		

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderBody(EntityLivingBase entity, float partialTick,boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_up_1");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
	
	public void postRenderBodyDown(EntityLivingBase entity, float partialTick, boolean needsRotate)
	{
		if(needsRotate) GlStateManager.rotate(-(entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick) + 180, 0, 1, 0);
		
		GeoBone bone = (GeoBone) getAnimationProcessor().getBone("body2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down_2");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	
		
		GL11.glTranslated(-bone.rotationPointX / 16, -bone.rotationPointY / 16, -bone.rotationPointZ / 16);
		
		bone = (GeoBone) getAnimationProcessor().getBone("body_down_3");
        
        GL11.glTranslated(-bone.getPositionX() / 16, bone.getPositionY() / 16, bone.getPositionZ() / 16);
        GL11.glTranslated(bone.rotationPointX / 16, bone.rotationPointY / 16, bone.rotationPointZ / 16);
        
        if (bone.getRotationZ() != 0.0F)
		{  
        	GlStateManager.rotate((float)Math.toDegrees(bone.getRotationZ()), 0, 0, 1);
		}

		if (bone.getRotationY() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationY()), 0, 1, 0);
		}

		if (bone.getRotationX() != 0.0F)
		{
			GlStateManager.rotate((float)Math.toDegrees(bone.getRotationX()), 1, 0, 0);
		}	

        GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.1, -0.6F);
	}
}
