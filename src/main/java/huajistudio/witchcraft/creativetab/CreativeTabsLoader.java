package huajistudio.witchcraft.creativetab;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class CreativeTabsLoader {
	public static final CreativeTabs WITCHCRAFT = new CreativeTabs(CreativeTabs.getNextID(), "witchCraft") {
		@Override
		@Nonnull
		public Item getTabIconItem() {
			return ItemLoader.CRYSTAL;
		}
	};
}
