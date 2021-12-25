package com.sm.network;

import com.sm.main.Sm;
import com.sm.utils.CrawlingHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class S0PacketTryRemoveCrawling implements IMessage {

	public S0PacketTryRemoveCrawling() {}
	
	@Override
	public void fromBytes(ByteBuf data) {}

	@Override
	public void toBytes(ByteBuf data) {}
	
	public static class Handler implements IMessageHandler<S0PacketTryRemoveCrawling, IMessage>
	{
		public IMessage onMessage(S0PacketTryRemoveCrawling message, MessageContext ctx) 
		{
			EntityPlayer player = Sm.proxy.getEntityPlayer(ctx);
			if(player.world.getBlockState(player.getPosition().add(0, 1, 0)).getBlock() == Blocks.AIR)
			{
				Sm.network.sendToDimension(new C0PacketRemoveCrawling(player.getName()), player.dimension);
				CrawlingHandler.removePlayer(player.getName());
			}
			return null;
		}
	}
}
