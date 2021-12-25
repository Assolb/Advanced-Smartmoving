package com.sm.network;

import com.sm.main.Sm;
import com.sm.utils.CrawlingHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C1PacketAddCrawling implements IMessage {

	public String name;
	
	public C1PacketAddCrawling() {}
	
	public C1PacketAddCrawling(String name) 
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
	
	public static class Handler implements IMessageHandler<C1PacketAddCrawling, IMessage>
	{
		public IMessage onMessage(C1PacketAddCrawling message, MessageContext ctx) 
		{
			CrawlingHandler.addPlayer(message.name);
			CrawlingHandler.isRemoving = false;
			return null;
		}
	}
}
