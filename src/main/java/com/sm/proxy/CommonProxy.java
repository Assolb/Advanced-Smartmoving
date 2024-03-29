package com.sm.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {}
  
	public void init(FMLInitializationEvent event) {}
  
	public void postInit(FMLPostInitializationEvent event) {}
  
	public void serverStarting(FMLServerStartingEvent event) {}
  
	public EntityPlayer getEntityPlayer (MessageContext ctx) 
	{
		return ctx.getServerHandler().player;
	}
}
