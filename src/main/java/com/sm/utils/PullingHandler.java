package com.sm.utils;

import java.util.HashMap;

import net.minecraft.util.math.BlockPos;

public class PullingHandler {

	public static HashMap<String, BlockPos> pullingPlayers = new HashMap<String, BlockPos>();
	
	public static void addPlayer(String name, BlockPos block)
	{
		pullingPlayers.put(name, block);
	}
	
	public static void removePlayer(String name)
	{
		pullingPlayers.remove(name);
	}
}
