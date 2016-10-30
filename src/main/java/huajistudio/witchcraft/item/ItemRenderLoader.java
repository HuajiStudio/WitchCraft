package huajistudio.witchcraft.item;

import huajistudio.witchcraft.WitchCraft;

public class ItemRenderLoader {
	public ItemRenderLoader() {
		WitchCraft.proxy.itemLoader.registerRenders();
	}
}
