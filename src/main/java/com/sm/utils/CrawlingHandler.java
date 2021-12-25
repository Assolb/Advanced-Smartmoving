package com.sm.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;

public class CrawlingHandler {

	public static List<String> crawlingPlayers = new ArrayList<String>();
	public static boolean isRemoving = false;
	
	public static void addPlayer(String name)
	{
		if(!crawlingPlayers.contains(name))
		crawlingPlayers.add(name);
	}
	
	public static void removePlayer(String name)
	{
		crawlingPlayers.remove(name);
	}
	
	public static void removeSelf()
	{
		isRemoving = true;
	}
}
