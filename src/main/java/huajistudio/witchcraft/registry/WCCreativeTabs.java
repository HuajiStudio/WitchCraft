package huajistudio.witchcraft.registry;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WCCreativeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WitchCraft.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WITCHCRAFT = CREATIVE_MODE_TABS.register("witchcraft",
			() -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.witchCraft"))
					.icon(() -> new ItemStack(WCItems.CRYSTAL.get()))
					.displayItems((parameters, output) -> WCItems.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
					.build());
}
