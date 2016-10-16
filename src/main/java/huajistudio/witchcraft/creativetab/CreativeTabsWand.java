package huajistudio.witchcraft.creativetab;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsWand extends CreativeTabs {
	public CreativeTabsWand() {
		super("wands");
	}

	@Override
	public Item getTabIconItem() {
		return ItemLoader.wands.get(0);
	}
}
