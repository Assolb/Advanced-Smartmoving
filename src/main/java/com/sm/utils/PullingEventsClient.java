package com.sm.utils;

import org.lwjgl.input.Keyboard;

import com.sm.main.Sm;
import com.sm.network.S0PacketTryRemoveCrawling;
import com.sm.network.S1PacketAddCrawling;
import com.sm.network.S2PacketTryAddPulling;
import com.sm.network.S3PacketRemovePulling;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.MoverType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class PullingEventsClient {

	@SubscribeEvent
	public void keyInputEvent(KeyInputEvent e)
	{
		
	}
	
	@SubscribeEvent
	public void tickEvent(TickEvent.ClientTickEvent e)
	{
		if(Minecraft.getMinecraft().world == null) return;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LMENU))
		{
			Sm.network.sendToServer(new S2PacketTryAddPulling(Minecraft.getMinecraft().player.rayTrace(4, 0).getBlockPos()));
		}
		else if(PullingHandler.pullingPlayers.containsKey(Minecraft.getMinecraft().player.getName()))
		{
			Sm.network.sendToServer(new S3PacketRemovePulling());
		}
		
		if(PullingHandler.pullingPlayers.containsKey(Minecraft.getMinecraft().player.getName()))
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_W))
				Minecraft.getMinecraft().player.move(MoverType.SELF, 0, 0.03, 0);
		}
	}
}

