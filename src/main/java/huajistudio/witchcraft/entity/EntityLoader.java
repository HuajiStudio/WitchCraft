package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
	private static int nextID = 0;

	public EntityLoader() {
		registerEntity(EntityLightBall.class, "LightBall", 64, 10, false);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {

	}

	private static void registerEntity(Class<? extends Entity> entityClass, String name,
	                                   int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, name, nextID++, WitchCraft.instance, trackingRange, updateFrequency,
				sendsVelocityUpdates);
	}
}
