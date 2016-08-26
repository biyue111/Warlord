package warlord.military.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageHandleronClient implements IMessageHandler<MessageToClient, IMessage>{
	  /**
	   * Called when a message is received of the appropriate type.
	   * CALLED BY THE NETWORK THREAD, NOT THE CLIENT THREAD
	   * @param message The message
	   */
	  public IMessage onMessage(final MessageToClient message, MessageContext ctx) {
	    if (ctx.side != Side.CLIENT) {
	      System.err.println("TargetEffectMessageToClient received on wrong side:" + ctx.side);
	      return null;
	    }
	    if (!message.isMessageValid()) {
	      System.err.println("TargetEffectMessageToClient was invalid" + message.toString());
	      return null;
	    }

	    // we know for sure that this handler is only used on the client side, so it is ok to assume
	    //  that the ctx handler is a client, and that Minecraft exists.
	    // Packets received on the server side must be handled differently!  See MessageHandlerOnServer

	    // This code creates a new task which will be executed by the client during the next tick,
	    //  for example see Minecraft.runGameLoop() , just under section
	    //    this.mcProfiler.startSection("scheduledExecutables");
	    //  In this case, the task is to call messageHandlerOnClient.processMessage(worldclient, message)
	    System.out.println("Recived Clientmessage");

	    return null;
	  }
	
}
