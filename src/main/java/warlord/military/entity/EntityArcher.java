package warlord.military.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.ai.*;
import net.minecraft.world.World;

import warlord.military.entity.ai.EntityAIArrowLongRangeAttack;

public class EntityArcher extends EntityMob implements IRangedAttackMob{

	public EntityArcher(World p_i1681_1_) {
		super(p_i1681_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowLongRangeAttack(this, 1.0D, 20, 60, 40.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        //this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setSize(0.7F, 1.8F);
		//this.tasks.addTask(3, new EntityAIWander(this, 0.25D));
	}
	
    
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.01D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
    }
    
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }
    
    public boolean isAIEnabled()
    {
        return true;
    }
    
    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
    	//EntityArrow(worldObj, this, target, speed, precision)
        //EntityArrow entityarrow = new EntityArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
    	
    	EntityArrow entityarrow = new EntityArrow(this.worldObj);
    	
        entityarrow.shootingEntity = p_82196_1_;

        if (p_82196_1_ instanceof EntityPlayer)
        {
            entityarrow.canBePickedUp = 1;
        }

        entityarrow.posY = this.posY + (double)this.getEyeHeight() - 0.10000000149011612D;
        double d0 = p_82196_1_.posX - this.posX;
        double d1 = p_82196_1_.boundingBox.minY + (double)(p_82196_1_.height / 3.0F) - entityarrow.posY;
        double d2 = p_82196_1_.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        
        d3 = d3 - 1.0D;
        d2 = d2 * d3 /(d3 + 1.0D);
        d0 = d0 * d3 /(d3 + 1.0D);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            entityarrow.setLocationAndAngles(this.posX + d4, entityarrow.posY, this.posZ + d5, f2, f3);
            entityarrow.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            //entityarrow.setThrowableHeading(d0, d1 + (double)f4, d2, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
            //test: deta Y = 0, precision=100
            
            double d6 = (double)((0.05 * d3) / (1.6 * 1.6));
            //System.out.println(d6);
            double d7;
            if(d6 <= 1) {
            	double d8 = ((Math.PI - Math.asin(d6)) / 2);
            	double d9 = (0.0005D * d3*d3) / (12.288D * Math.cos(d8) - 0.24D * d3 * Math.sin(d8));
            	d7 = d3 * Math.tan(d8 + d9);
            	}
            else {d7 = 0; System.out.println("Error for d6");}
            System.out.println(d7);
            entityarrow.setThrowableHeading(d0, d7, d2, 1.6F, 0.0F);
        }
    	
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        
        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
        {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    
}
