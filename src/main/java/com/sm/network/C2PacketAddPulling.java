package com.sm.network;

import com.sm.main.Sm;
import com.sm.utils.CrawlingHandler;
import com.sm.utils.PullingHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2PacketAddPulling implements IMessage {

	public String name;
	public double x, y, z;
	
	public C2PacketAddPulling() {}
	
	public C2PacketAddPulling(String name, double x, double y, double z) 
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf data) 
	{
		this.name = ByteBufUtils.readUTF8String(data);
		this.x = data.readDouble();
		this.y = data.readDouble();
		this.z = data.readDouble();
	}

	@Override
	public void toBytes(ByteBuf data) 
	{
		ByteBufUtils.writeUTF8String(data, name);
		data.writeDouble(x);
		data.writeDouble(y);
		data.writeDouble(z);
	}
	
	public static class Handler implements IMessageHandler<C2PacketAddPulling, IMessage>
	{
		public IMessage onMessage(C2PacketAddPulling message, MessageContext ctx) 
		{
			PullingHandler.addPlayer(message.name, new BlockPos(message.x, message.y, message.z));
			return null;
		}
	}
}
