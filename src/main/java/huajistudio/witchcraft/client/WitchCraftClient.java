package huajistudio.witchcraft.client;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.client.renderer.LightBallRenderer;
import huajistudio.witchcraft.registry.WCEntityTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@Mod(value = WitchCraft.MODID, dist = Dist.CLIENT)
public class WitchCraftClient {
	public WitchCraftClient(IEventBus modEventBus) {
		modEventBus.addListener(WitchCraftClient::registerRenderers);
		modEventBus.addListener(WitchCraftClient::registerGuiLayers);
	}

	private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(WCEntityTypes.LIGHT_BALL.get(), LightBallRenderer::new);
	}

	private static void registerGuiLayers(RegisterGuiLayersEvent event) {
		// Right after the health, armor and hunger bars, so that it sits on top of the hunger bar.
		event.registerBelow(VanillaGuiLayers.VEHICLE_HEALTH, WitchCraft.id("magic_stats"), new MagicHudLayer());
	}
}
