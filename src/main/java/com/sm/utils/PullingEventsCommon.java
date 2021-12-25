package com.sm.utils;

import com.sm.main.Sm;
import com.sm.network.C3PacketRemovePulling;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class PullingEventsCommon {

	@SubscribeEvent
	public void tick(PlayerTickEvent e)
	{
		EntityPlayer player = e.player;	 
		if(PullingHandler.pullingPlayers.containsKey(e.player.getName()))
		{ 
			player.setNoGravity(true);
		    BlockPos pos = PullingHandler.pullingPlayers.get(e.player.getName());
		    if(!e.player.world.isRemote)
	    	{ 
			    if(e.player.world.getBlockState(pos).getBlock() == Blocks.AIR)
			    {
			    	Sm.network.sendToDimension(new C3PacketRemovePulling(player.getName()), player.dimension);
					PullingHandler.removePlayer(player.getName());
			    }
			    else if(new Vec3d(player.getPosition().getX(), 0, player.getPosition().getZ()).distanceTo(new Vec3d(pos.getX(), 0, pos.getZ())) > 1) 
			    {
		    		Sm.network.sendToDimension(new C3PacketRemovePulling(player.getName()), player.dimension);
					PullingHandler.removePlayer(player.getName());
			    }
			    else if(player.posY > pos.getY() + 1)
			    {
			    	Sm.network.sendToDimension(new C3PacketRemovePulling(player.getName()), player.dimension);
					PullingHandler.removePlayer(player.getName());
			    }
	    	}
		}
		else player.setNoGravity(false);
	}
}

