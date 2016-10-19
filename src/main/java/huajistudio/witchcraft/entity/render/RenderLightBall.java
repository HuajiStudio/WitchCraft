package huajistudio.witchcraft.entity.render;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

public class RenderLightBall extends RenderSnowball<EntityLightBall> {
	public RenderLightBall(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}
}
