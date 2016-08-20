package warlord.warmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import warlord.warmod.Modcst;
import warlord.comm.ClientProxy;
import warlord.comm.CommonProxy;

import warlord.military.item.BeginItem;
import warlord.military.entity.EntityRegister;

@Mod(modid = Modcst.MODID, name = Modcst.NAME, version = Modcst.VERSION)
public class Warmod {
	@Instance("Generic")
	public static Warmod instance;
	
	@SidedProxy(clientSide = "warlord.comm.ClientProxy",serverSide = "warlord.comm.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item beginItem;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		beginItem = new BeginItem().setUnlocalizedName("beginItem").setTextureName("beginner:beginItem");
		GameRegistry.registerItem(beginItem, beginItem.getUnlocalizedName());
		EntityRegister.mainRegisty();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}

}
