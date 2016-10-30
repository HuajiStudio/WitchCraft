package huajistudio.witchcraft.creativetab;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader {
	public static final CreativeTabs WITCHCRAFT = new CreativeTabs(CreativeTabs.getNextID(), "witchCraft") {
		@Override
		public Item getTabIconItem() {
			return ItemLoader.CRYSTAL;
		}
	};

	public CreativeTabsLoader(FMLPreInitializationEvent event) {
	}
}
