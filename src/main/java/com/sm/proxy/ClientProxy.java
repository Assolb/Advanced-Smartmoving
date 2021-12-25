package com.sm.proxy;

import com.sm.client.renderers.SteveRenderer;
import com.sm.entities.SteveEntity;
import com.sm.utils.CrawlingEventsClient;
import com.sm.utils.PullingEventsClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;

public class ClientProxy extends CommonProxy {
	 
	public static SteveRenderer steveRenderer;

	public void preInit(FMLPreInitializationEvent event) 
	{
		MinecraftForge.EVENT_BUS.register(new CrawlingEventsClient());
		MinecraftForge.EVENT_BUS.register(new PullingEventsClient());
	    super.preInit(event);
	}
  
	public void init(FMLInitializationEvent event) 
	{
		GeckoLib.initialize();	
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

		steveRenderer = new SteveRenderer(renderManager);
		GeoReplacedEntityRenderer.registerReplacedEntity(SteveEntity.class, steveRenderer);
		
		super.init(event);
	}
  
	@Override
	public EntityPlayer getEntityPlayer (MessageContext ctx) 
	{
		return ctx.side == Side.CLIENT ? Minecraft.getMinecraft().player : super.getEntityPlayer(ctx);
	}
}
