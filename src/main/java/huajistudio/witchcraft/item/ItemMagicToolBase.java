package huajistudio.witchcraft.item;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import net.minecraft.item.Item;

/**
 *
 */
public abstract class ItemMagicToolBase extends Item {
	public abstract int getMagicCost();

	public ItemMagicToolBase() {
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	}
}
