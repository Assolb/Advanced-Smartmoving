package com.sm.network;

import com.sm.main.Sm;
import com.sm.utils.CrawlingHandler;
import com.sm.utils.PullingHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class S2PacketTryAddPulling implements IMessage {

	public int x, y, z;
	
	public S2PacketTryAddPulling() {}
	
	public S2PacketTryAddPulling(BlockPos pos) 
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}
	
	@Override
	public void fromBytes(ByteBuf data) 
	{
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}

	@Override
	public void toBytes(ByteBuf data) 
	{
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
	}
	
	public static class Handler implements IMessageHandler<S2PacketTryAddPulling, IMessage>
	{
		public IMessage onMessage(S2PacketTryAddPulling message, MessageContext ctx) 
		{
			EntityPlayer player = Sm.proxy.getEntityPlayer(ctx);
			BlockPos pos = new BlockPos(message.x, message.y, message.z);
			if(player.world.getBlockState(pos).getBlock() != Blocks.AIR && player.world.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.AIR)
			{
				if(player.world.getBlockState(pos).getBlock() == Blocks.AIR)
			    {
					return null;
			    }
				else if(!player.world.getBlockState(pos).isBlockNormalCube())
				{
					return null;
				}
			    else if(new Vec3d(player.getPosition().getX(), 0, player.getPosition().getZ()).distanceTo(new Vec3d(pos.getX(), 0, pos.getZ())) > 1) 
			    {
		    		return null;
			    }
			    else if(player.posY > pos.getY() + 1)
			    {
			    	return null;
			    }
			    else if(player.posY == pos.getY() + 1 && !PullingHandler.pullingPlayers.containsKey(player.getName()))
			    {
			    	return null;
			    }
			    else if(player.posY + 2.5 < pos.getY())
			    {
			    	return null;
			    }
				Sm.network.sendToDimension(new C2PacketAddPulling(player.getName(), pos.getX(), pos.getY(), pos.getZ()), player.dimension);
				PullingHandler.addPlayer(player.getName(), pos);
			}
			return null;
		}
	}
}
