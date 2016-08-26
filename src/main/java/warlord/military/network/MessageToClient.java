package warlord.military.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageToClient implements IMessage{
	
	private boolean messageIsValid;
	
	public MessageToClient()
	{ }
	
	public boolean isMessageValid()
	{
		return messageIsValid;
	}
	
	  @Override
	  public void toBytes(ByteBuf buf)
	  {
	    if (!messageIsValid) return;

	    // these methods may also be of use for your code:
	    // for Itemstacks - ByteBufUtils.writeItemStack()
	    // for NBT tags ByteBufUtils.writeTag();
	    // for Strings: ByteBufUtils.writeUTF8String();
	    System.out.println("TargetEffectMessageToClient:toBytes length=" + buf.readableBytes());  // debugging only
	  }
	  
	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
	    try {

	      // these methods may also be of use for your code:
	      // for Itemstacks - ByteBufUtils.readItemStack()
	      // for NBT tags ByteBufUtils.readTag();
	      // for Strings: ByteBufUtils.readUTF8String();

	    } catch (IndexOutOfBoundsException ioe) {
	      System.err.println("Exception while reading TargetEffectMessageToClient: " + ioe);
	      return;
	    }
	    messageIsValid = true;
	  }
	
}
