package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.client.renderer.entity.EntityRenderFactory;
import huajistudio.witchcraft.client.renderer.entity.RenderLightBall;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
	private static int nextID = 0;

	@SideOnly(Side.CLIENT)
	public void registerRenders() {
		registerRender(EntityLightBall.class, RenderLightBall.class);
	}

	@Load(LoaderState.PREINITIALIZATION)
	public void registerEntities() {
		registerEntity(EntityLightBall.class, "LightBall", 64, 10, true);
	}

	private <T extends Entity> void registerEntity(Class<T> entityClass, String name,
	                                   int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, name, nextID++, WitchCraft.instance,
				trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	private <T extends Entity> void registerRender(Class<T> entityClass, Class<? extends Render<T>> render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<T>(render));
	}
}
