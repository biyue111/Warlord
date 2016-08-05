package military.warmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import military.warmod.Modcst;
import military.comm.ClientProxy;
import military.comm.CommonProxy;

import military.waritem.BeginItem;
import military.warmob.EntityBegin;

@Mod(modid = Modcst.MODID, name = Modcst.NAME, version = Modcst.VERSION)
public class Warmod {
	@Instance("Generic")
	public static Warmod instance;
	
	@SidedProxy(clientSide = "military.comm.ClientProxy",serverSide = "military.comm.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item beginItem;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		beginItem = new BeginItem().setUnlocalizedName("beginItem").setTextureName("beginner:beginItem");
		GameRegistry.registerItem(beginItem, beginItem.getUnlocalizedName());
		EntityBegin.mainRegisty();
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
