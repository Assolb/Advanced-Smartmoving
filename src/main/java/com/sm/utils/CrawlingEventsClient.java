package com.sm.utils;

import org.lwjgl.input.Keyboard;

import com.sm.main.Sm;
import com.sm.network.S0PacketTryRemoveCrawling;
import com.sm.network.S1PacketAddCrawling;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class CrawlingEventsClient {

	@SubscribeEvent
	public void keyInputEvent(KeyInputEvent e)
	{
		if(Keyboard.getEventKeyState())
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_Q) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
			{
				Sm.network.sendToServer(new S1PacketAddCrawling());
			}
		}
		else
		{
			if(CrawlingHandler.crawlingPlayers.contains(Minecraft.getMinecraft().player.getName()) && !Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				CrawlingHandler.removeSelf();
			}
		}
	}
	
	@SubscribeEvent
	public void tickEvent(TickEvent.ClientTickEvent e)
	{
		if(Minecraft.getMinecraft().world == null) return;
		if(CrawlingHandler.crawlingPlayers.contains(Minecraft.getMinecraft().player.getName()))
		{
			Minecraft.getMinecraft().player.setSprinting(false);
			Minecraft.getMinecraft().player.setSneaking(true);
		}
		if(CrawlingHandler.isRemoving)
		{
			Sm.network.sendToServer(new S0PacketTryRemoveCrawling());
		}
	}
}

