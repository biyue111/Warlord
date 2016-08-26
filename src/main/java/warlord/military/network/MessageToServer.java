package warlord.military.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.Vec3;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageToServer implements IMessage {
	
	private boolean messageIsValid;
	private Vec3 targetCoor;

	public MessageToServer(Vec3 i_TargetCood)
	{
		targetCoor = i_TargetCood;
		messageIsValid = true;
	}
	
	public MessageToServer()
	{ 
		messageIsValid = false;
	}
	
	public boolean isMessageValid()
	{
		return messageIsValid;
	}
	
	public Vec3 getTargetCoor()
	{
		return targetCoor;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!messageIsValid) return;
		
		// these methods may also be of use for your code:
		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
		buf.writeDouble(targetCoor.xCoord);
		buf.writeDouble(targetCoor.yCoord);
		buf.writeDouble(targetCoor.zCoord);
		System.out.println("TargetEffectMessageToClient:toBytes length=" + buf.readableBytes());  // debugging only
	}
	  
	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
	    try 
	    {
	    	double x = buf.readDouble();
	    	double y = buf.readDouble();
	    	double z = buf.readDouble();
	    	targetCoor = Vec3.createVectorHelper(x,y,z);
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
