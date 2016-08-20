package warlord.military.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;



public class EntityRegister {
	public static void mainRegisty(){
		registerEntity();
	}
	
	public static void registerEntity(){
		createEntity(EntityArcher.class, "Warlord Archer", 0x0004FF, 0xFF001E);
	}
	
	public static void createEntity(Class entityClass, String entityName, int soildColor, int spotColor){
		int randmeId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randmeId, soildColor, spotColor);
	}
}
