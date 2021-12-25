package com.sm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.objectweb.asm.Type;

import com.sm.entities.SteveEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CrawlingEventsCommon {
	
	@SubscribeEvent
	public void tick(PlayerTickEvent e)
	{
		EntityPlayer player = e.player;	 
		if(CrawlingHandler.crawlingPlayers.contains(e.player.getName()))
		{ 
		    try 
		    {
		    	player.setSprinting(false);
		    	player.setSneaking(true);
		    	player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 2));
                player.eyeHeight = 0.45F;
				setSize(player, 0.6f, 0.6f);
			}
		    catch (Exception e1) { e1.printStackTrace(); }
		}
		else if((player.isInWater() && SteveEntity.underWater(player)))
		{
			try 
		    {
				setSize(player, 0.6f, 0.6f);
			}
			catch (Exception e1) { e1.printStackTrace(); }
		}
		else
		{
			player.eyeHeight = 1.5f;
		}
	}
	
	public void setSize(Entity e, float width, float height)
	{
		if (width != e.width || height != e.height)
        {
            float f = e.width;
            e.width = width;
            e.height = height;

            if (e.width < f)
            {
                double d0 = (double)width / 2.0D;
                e.setEntityBoundingBox(new AxisAlignedBB(e.posX - d0, e.posY, e.posZ - d0, e.posX + d0, e.posY + (double)e.height, e.posZ + d0));
                return;
            }

            AxisAlignedBB axisalignedbb = e.getEntityBoundingBox();
            e.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)e.width, axisalignedbb.minY + (double)e.height, axisalignedbb.minZ + (double)e.width));

            if (e.width > f && !e.world.isRemote)
            {
                e.move(MoverType.SELF, (double)(f - e.width), 0.0D, (double)(f - e.width));
            }
        }
	}
}

