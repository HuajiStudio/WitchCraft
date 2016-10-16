package huajistudio.witchcraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader {
	public static CreativeTabs tabWand;

	public CreativeTabsLoader(FMLPreInitializationEvent event) {
		tabWand = new CreativeTabsWand();
	}
}
