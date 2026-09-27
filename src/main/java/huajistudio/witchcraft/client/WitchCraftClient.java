package huajistudio.witchcraft.client;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.client.renderer.LightBallRenderer;
import huajistudio.witchcraft.client.renderer.MagicItemRenderer;
import huajistudio.witchcraft.item.WandItem;
import huajistudio.witchcraft.registry.WCItems;
import huajistudio.witchcraft.registry.WCEntityTypes;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

@Mod(value = WitchCraft.MODID, dist = Dist.CLIENT)
public class WitchCraftClient {
	public WitchCraftClient(IEventBus modEventBus) {
		modEventBus.addListener(WitchCraftClient::registerRenderers);
		modEventBus.addListener(WitchCraftClient::registerGuiLayers);
		modEventBus.addListener(WitchCraftClient::registerClientExtensions);
		modEventBus.addListener(WitchCraftClient::registerAdditionalModels);
	}

	private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(WCEntityTypes.LIGHT_BALL.get(), LightBallRenderer::new);
	}

	private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return MagicItemRenderer.getInstance();
			}
		}, WCItems.getMagicCrystals().toArray(Item[]::new));
		event.registerItem(new WandClientExtensions(), getItems(WandItem.class).toArray(Item[]::new));
	}

	private static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
		event.register(MagicItemRenderer.CRYSTAL_GLOW);
		for (Item item : WCItems.getMagicCrystals())
			event.register(MagicItemRenderer.getSprite(item));
		for (Item item : getItems(WandItem.class)) {
			event.register(MagicItemRenderer.getSprite(item));
			event.register(MagicItemRenderer.getBodySprite(item));
		}
	}

	private static List<Item> getItems(Class<? extends Item> type) {
		return WCItems.ITEMS.getEntries().stream().map(DeferredHolder::get).filter(type::isInstance).map(Item.class::cast).toList();
	}

	private static void registerGuiLayers(RegisterGuiLayersEvent event) {
		// Right after the health, armor and hunger bars, so that it sits on top of the hunger bar.
		event.registerBelow(VanillaGuiLayers.VEHICLE_HEALTH, WitchCraft.id("magic_stats"), new MagicHudLayer());
	}
}
