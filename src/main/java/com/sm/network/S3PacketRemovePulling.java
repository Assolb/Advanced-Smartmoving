package com.sm.network;

import com.sm.main.Sm;
import com.sm.utils.CrawlingHandler;
import com.sm.utils.PullingHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class S3PacketRemovePulling implements IMessage {

	public S3PacketRemovePulling() {}
	
	@Override
	public void fromBytes(ByteBuf data) {}

	@Override
	public void toBytes(ByteBuf data) {}
	
	public static class Handler implements IMessageHandler<S3PacketRemovePulling, IMessage>
	{
		public IMessage onMessage(S3PacketRemovePulling message, MessageContext ctx) 
		{
			EntityPlayer player = Sm.proxy.getEntityPlayer(ctx);
			Sm.network.sendToDimension(new C3PacketRemovePulling(player.getName()), player.dimension);
			PullingHandler.removePlayer(player.getName());
			return null;
		}
	}
}
