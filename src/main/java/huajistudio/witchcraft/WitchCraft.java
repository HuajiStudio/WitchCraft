package huajistudio.witchcraft;

import huajistudio.witchcraft.attachment.AttachmentLoader;
import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.entity.EntityLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.network.MagicStatsPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(WitchCraft.MODID)
public class WitchCraft {
	public static final String MODID = "witchcraft";
	public static final String NAME  = "WitchCraft";

	public WitchCraft(IEventBus modEventBus) {
		BlockLoader.BLOCKS.register(modEventBus);
		ItemLoader.ITEMS.register(modEventBus);
		EntityLoader.ENTITY_TYPES.register(modEventBus);
		CreativeTabsLoader.CREATIVE_MODE_TABS.register(modEventBus);
		AttachmentLoader.ATTACHMENT_TYPES.register(modEventBus);
		modEventBus.addListener(MagicStatsPayload::register);
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
}
