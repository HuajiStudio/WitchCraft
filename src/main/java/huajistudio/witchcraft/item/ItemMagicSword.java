package huajistudio.witchcraft.item;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;

public abstract class ItemMagicSword extends ItemMagicToolBase {
	protected abstract float getAttackDamage();

	public ItemMagicSword() {
		maxStackSize = 1;
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	}
}
