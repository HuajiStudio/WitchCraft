package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
	private static int nextID = 0;

	@SideOnly(Side.CLIENT)
	@Load(LoaderState.POSTINITIALIZATION)
	public void registerRenders() {

	}

	@Load(LoaderState.PREINITIALIZATION)
	public void registerEntities() {
		registerEntity(EntityLightBall.class, "LightBall", 64, 10, false);
	}

	private void registerEntity(Class<? extends Entity> entityClass, String name,
	                                   int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, name, nextID++, WitchCraft.instance, trackingRange, updateFrequency,
				sendsVelocityUpdates);
	}
}
