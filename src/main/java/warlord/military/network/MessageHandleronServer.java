package warlord.military.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;


public class MessageHandleronServer implements IMessageHandler<MessageToServer, IMessage>
{
	  /**
	   * Called when a message is received of the appropriate type.
	   * CALLED BY THE NETWORK THREAD
	   * @param message The message
	   */
	  public IMessage onMessage(final MessageToServer message, MessageContext ctx) {
	    if (ctx.side != Side.SERVER) {
	      System.err.println("MessageToServer received on wrong side:" + ctx.side);
	      return null;
	    }
	    if (!message.isMessageValid()) {
	      System.err.println("MessageToServer was invalid" + message.toString());
	      return null;
	    }

	    // we know for sure that this handler is only used on the server side, so it is ok to assume
	    //  that the ctx handler is a serverhandler, and that WorldServer exists.
	    // Packets received on the client side must be handled differently!  See MessageHandlerOnClient

	    final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
	    if (sendingPlayer == null) {
	      System.err.println("EntityPlayerMP was null when AirstrikeMessageToServer was received");
	      return null;
	    }

	    // This code creates a new task which will be executed by the server during the next tick,
	    //  for example see MinecraftServer.updateTimeLightAndEntities(), just under section
	    //      this.theProfiler.startSection("jobs");
	    //  In this case, the task is to call messageHandlerOnServer.processMessage(message, sendingPlayer)
	    Vec3 targetCood = message.getTargetCood();
	    
	    System.out.println("Recived Servermessage");
	    System.out.println(targetCood.xCoord);
	    System.out.println(targetCood.yCoord);
	    System.out.println(targetCood.zCoord);

	    return null;
	  }
}
