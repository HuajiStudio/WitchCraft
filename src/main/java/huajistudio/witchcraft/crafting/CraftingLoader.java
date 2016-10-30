package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingLoader implements ILoader<IRecipe> {
	@Override
	public void register() {
		registerCrafting();
		registerFuel();
		registerSmelting();
	}

	private void registerCrafting() {
		GameRegistry.addRecipe(new ItemStack(BlockLoader.CRYSTAL_BLOCK), "XXX", "XXX", "XXX", 'X', ItemLoader.CRYSTAL);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.CRYSTAL, 9), BlockLoader.CRYSTAL_BLOCK);
	}

	private void registerSmelting() {
		GameRegistry.addSmelting(BlockLoader.CRYSTAL_ORE, new ItemStack(ItemLoader.CRYSTAL), 1.5F);
		GameRegistry.addSmelting(ItemLoader.CRYSTAL, new ItemStack(ItemLoader.MAGIC_CRYSTAL), 2.0F);
	}

	private void registerFuel() {
		GameRegistry.registerFuelHandler(fuel -> ItemLoader.MAGIC_CRYSTAL != fuel.getItem() ? 0 : 51200);
	}
}
