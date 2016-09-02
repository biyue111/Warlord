package warlord.military.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;
import warlord.military.entity.ai.EntityAIArrowLongRangeAttack;
import warlord.military.entity.ai.EntityAiFollowCommand;

public class EntityFootman extends EntityAnimal{
	
	public double targetPosX;
	public double targetPosY;
	public double targetPosZ;
	public boolean commandIn;
	
	public EntityFootman(World p_i1681_1_) {
		super(p_i1681_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this,1.0D));
        //this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        //this.tasks.addTask(2, new EntityAIWander(this, 0.25D));
        this.tasks.addTask(2, new EntityAiFollowCommand(this, 1.0D));
		this.setSize(0.7F, 1.0F);
		
		this.commandIn = false;
	}
	
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
    }
    
    @Override
    protected boolean isAIEnabled()
    {
    	return true;
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
    

}
