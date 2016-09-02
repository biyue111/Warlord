package warlord.military.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;
import warlord.military.entity.EntityFootman;

public class EntityAiFollowCommand extends EntityAIBase{

    private EntityFootman entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private static final String __OBFID = "CL_00001608";

    public EntityAiFollowCommand(EntityFootman i_entity, double i_speed)
    {
        this.entity = i_entity;
        this.speed = i_speed;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if(!this.entity.commandIn)
        {
        	return false;
        }
        else
        {
        	this.xPosition = this.entity.targetPosX;
        	this.yPosition = this.entity.targetPosY;
        	this.zPosition = this.entity.targetPosZ;
        	System.out.println("Command start");
        	this.entity.commandIn = false;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	if(this.entity.getNavigator().getPath() == null) System.out.println("Path NULL");
    	
    	if(this.entity.getNavigator().noPath())
		{
    		System.out.println("Task can't continue");
    		return false;
		}
    	else
    	{
    		return true;
    	}
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        boolean testpt = this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition + 1.0D, this.zPosition, this.speed);
        if(this.entity.getNavigator().getPath() == null) System.out.println("Path start NULL");
        if(testpt) System.out.println("SetPath");
    }
}
