package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingLoader {
	public static void registerRecipe() {

	}

	public static void registerSmelting() {
		GameRegistry.addSmelting(BlockLoader.CRYSTAL_ORE, new ItemStack(ItemLoader.CRYSTAL), 1.5F);
		GameRegistry.addSmelting(ItemLoader.CRYSTAL, new ItemStack(ItemLoader.MAGIC_CRYSTAL), 2.0F);
	}

	public static void registerFuel() {
		GameRegistry.registerFuelHandler(new IFuelHandler() {
			@Override
			public int getBurnTime(ItemStack fuel) {
				return ItemLoader.MAGIC_CRYSTAL != fuel.getItem() ? 0 : 51200;
			}
		});
	}
}
