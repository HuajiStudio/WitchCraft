package huajistudio.witchcraft.client;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.client.render.entity.RenderLightBall;
import huajistudio.witchcraft.entity.EntityLoader;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@Mod(value = WitchCraft.MODID, dist = Dist.CLIENT)
public class WitchCraftClient {
	public WitchCraftClient(IEventBus modEventBus) {
		modEventBus.addListener(WitchCraftClient::registerRenderers);
		modEventBus.addListener(WitchCraftClient::registerGuiLayers);
	}

	private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EntityLoader.LIGHT_BALL.get(), RenderLightBall::new);
	}

	private static void registerGuiLayers(RegisterGuiLayersEvent event) {
		event.registerAboveAll(WitchCraft.id("magic_stats"), ClientRenderFactory::renderMagicStats);
	}
}
