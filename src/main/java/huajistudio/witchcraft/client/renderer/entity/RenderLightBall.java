package huajistudio.witchcraft.client.renderer.entity;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class RenderLightBall extends Render<EntityLightBall> {

	private final float scale;

	public RenderLightBall(RenderManager renderManager, float scale) {
		super(renderManager);
		this.scale = scale;
	}

	@Override
	public void doRender(EntityLightBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLightBall entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}
