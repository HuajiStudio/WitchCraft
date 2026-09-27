package huajistudio.witchcraft;

import huajistudio.witchcraft.registry.WCAttachmentTypes;
import huajistudio.witchcraft.registry.WCBlocks;
import huajistudio.witchcraft.registry.WCCreativeTabs;
import huajistudio.witchcraft.registry.WCEntityTypes;
import huajistudio.witchcraft.registry.WCItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(WitchCraft.MODID)
public class WitchCraft {
	public static final String MODID = "witchcraft";

	public WitchCraft(IEventBus modEventBus) {
		WCBlocks.BLOCKS.register(modEventBus);
		WCItems.ITEMS.register(modEventBus);
		WCEntityTypes.ENTITY_TYPES.register(modEventBus);
		WCCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
		WCAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
}
