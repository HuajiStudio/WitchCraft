package huajistudio.witchcraft.common;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.block.MaterialLoader;
import huajistudio.witchcraft.crafting.CraftingLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.item.ItemLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public MaterialLoader materialLoader;
	public BlockLoader blockLoader;
	public ItemLoader itemLoader;
	public EventHandler eventHandler;
	public EnchantmentLoader enchLoader;
	public CraftingLoader craftingLoader;

	public void preInit(FMLPreInitializationEvent event) {
		new CreativeTabsLoader(event);
		materialLoader = new MaterialLoader();
		materialLoader.register();
		blockLoader = new BlockLoader();
		blockLoader.register();
		itemLoader = new ItemLoader();
		itemLoader.register();
	}

	public void init(FMLInitializationEvent event) {
		eventHandler = new EventHandler();
		enchLoader = new EnchantmentLoader();
	}

	public void postInit(FMLPostInitializationEvent event) {
		craftingLoader = new CraftingLoader();
		craftingLoader.register();
	}
}
