package warlord.comm;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import warlord.military.network.MessageHandleronServer;
import warlord.military.network.MessageToServer;

public class CommonProxy {
	public static SimpleNetworkWrapper simpleNetworkWrapper;

	
	public void preInit()
	{
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("warmod");
	    simpleNetworkWrapper.registerMessage(MessageHandleronServer.class, MessageToServer.class,
                35, Side.SERVER);
	    //simpleNetworkWrapper.registerMessage(MessageHandlerOnServerDummy.class, TargetEffectMessageToClient.class,
        //        TARGET_EFFECT_MESSAGE_ID, Side.SERVER);

	}
}
