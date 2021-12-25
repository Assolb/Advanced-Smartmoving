package com.sm.network;

import com.sm.main.Sm;
import com.sm.utils.CrawlingHandler;
import com.sm.utils.PullingHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C3PacketRemovePulling implements IMessage {

	public String name;
	
	public C3PacketRemovePulling() {}
	
	public C3PacketRemovePulling(String name) 
	{
		this.name = name;
	}
	
	@Override
	public void fromBytes(ByteBuf data) 
	{
		this.name = ByteBufUtils.readUTF8String(data);
	}

	@Override
	public void toBytes(ByteBuf data) 
	{
		ByteBufUtils.writeUTF8String(data, name);
	}
	
	public static class Handler implements IMessageHandler<C3PacketRemovePulling, IMessage>
	{
		public IMessage onMessage(C3PacketRemovePulling message, MessageContext ctx) 
		{
			PullingHandler.removePlayer(message.name);
			return null;
		}
	}
}
