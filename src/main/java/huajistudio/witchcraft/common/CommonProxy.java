package huajistudio.witchcraft.common;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.item.ItemLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		new CreativeTabsLoader(event);
		ItemLoader.registerItems();
	}

	public void init(FMLInitializationEvent event) {
		new EventHandler();
		new EnchantmentLoader();
	}

	public void postInit(FMLPostInitializationEvent event) {}
}
