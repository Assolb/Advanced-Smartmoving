package com.sm.main;

import com.sm.network.C0PacketRemoveCrawling;
import com.sm.network.C1PacketAddCrawling;
import com.sm.network.C2PacketAddPulling;
import com.sm.network.C3PacketRemovePulling;
import com.sm.network.S0PacketTryRemoveCrawling;
import com.sm.network.S1PacketAddCrawling;
import com.sm.network.S2PacketTryAddPulling;
import com.sm.network.S3PacketRemovePulling;
import com.sm.proxy.CommonProxy;
import com.sm.utils.CrawlingEventsCommon;
import com.sm.utils.PullingEventsCommon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Sm.MODID, name = Sm.NAME, version = Sm.VERSION)
public class Sm {

	public static final String MODID = "sm";
	public static final String NAME = "Advanced Smartmoving";
	public static final String VERSION = "1.0.0";
	
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel("sm");
	  
	@Instance("sm")
	public static Sm instance;
	    
	@SidedProxy(clientSide = "com.sm.proxy.ClientProxy", serverSide = "com.sm.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		network.registerMessage(C0PacketRemoveCrawling.Handler.class, C0PacketRemoveCrawling.class, 0, Side.CLIENT);
		network.registerMessage(C1PacketAddCrawling.Handler.class, C1PacketAddCrawling.class, 1, Side.CLIENT);
		network.registerMessage(S0PacketTryRemoveCrawling.Handler.class, S0PacketTryRemoveCrawling.class, 2, Side.SERVER);
		network.registerMessage(S1PacketAddCrawling.Handler.class, S1PacketAddCrawling.class, 3, Side.SERVER);
		network.registerMessage(C2PacketAddPulling.Handler.class, C2PacketAddPulling.class, 4, Side.CLIENT);
		network.registerMessage(C3PacketRemovePulling.Handler.class, C3PacketRemovePulling.class, 5, Side.CLIENT);
		network.registerMessage(S2PacketTryAddPulling.Handler.class, S2PacketTryAddPulling.class, 6, Side.SERVER);
		network.registerMessage(S3PacketRemovePulling.Handler.class, S3PacketRemovePulling.class, 7, Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new CrawlingEventsCommon());
		MinecraftForge.EVENT_BUS.register(new PullingEventsCommon());
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
	}
}
