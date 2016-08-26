package warlord.military.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import warlord.comm.CommonProxy;
import warlord.military.network.MessageToServer;

public class ItemCommandSword extends Item{

	  public ItemCommandSword()
	  {
	    this.setMaxStackSize(1);
	    //this.setCreativeTab(CreativeTabs.tabCombat);   // the item will appear on the Miscellaneous tab in creative
	  }

	  // called when the item is right clicked in the air (or when clicked on a block but onItemUse returned false)
	  public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	  {
	    if (!worldIn.isRemote) {  // don't execute on the server side!
	      return itemStackIn;
	    }
		 
		//playerIn.jump();
		//playerIn.fallDistance = 0F;
		//System.out.println("RightClicked");
		
		MovingObjectPosition targetblock = getTargetBlock(playerIn,30.0F,0.0F);
		if(targetblock != null)
		{
			System.out.println(targetblock.blockX);
			System.out.println(targetblock.blockY);
			System.out.println(targetblock.blockZ);
			Vec3 tragetVec = Vec3.createVectorHelper((double)targetblock.blockX,(double)targetblock.blockY,(double)targetblock.blockZ);
			callMessageOnTarget(tragetVec);
		}
	    return itemStackIn;
	  }

	  // send a network message to the server to bombard the target location with a random projectile
	  public void callMessageOnTarget(Vec3 targetVec)
	  {
	    MessageToServer messageToServer = new MessageToServer(targetVec);
	    CommonProxy.simpleNetworkWrapper.sendToServer(messageToServer);
	    return;
	  }
	  
	  private static MovingObjectPosition getTargetBlock(EntityPlayer player, float range, float border)
	  {
		  float yOffset = player.worldObj.isRemote? 0.f : 1.62f;
		  Vec3 look = player.getLookVec();
		  look.xCoord*=range;
		  look.yCoord*=range;
		  look.zCoord*=range;
		  look.xCoord+=player.posX;
		  look.yCoord+=player.posY+yOffset;
		  look.zCoord+=player.posZ;
		  //return tracePath(player.worldObj, player.posX, player.posY+yOffset, player.posZ, look.xCoord, look.yCoord, look.zCoord, border, excluded);
		  //}
		  
		  Vec3 startVec = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
		  Vec3 endVec = Vec3.createVectorHelper(look.xCoord, look.yCoord, look.zCoord);
		  MovingObjectPosition blockHit = player.worldObj.rayTraceBlocks(startVec, endVec);
		  return blockHit;
	  }
	
}
