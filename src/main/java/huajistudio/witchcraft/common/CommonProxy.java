package huajistudio.witchcraft.common;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.item.ItemLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		new CreativeTabsLoader(event);
		new ItemLoader(event);
	}

	public void init(FMLInitializationEvent event) {}

	public void postInit(FMLPostInitializationEvent event) {}
}
