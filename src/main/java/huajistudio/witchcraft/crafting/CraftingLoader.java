package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingLoader {
	@Load(LoaderState.INITIALIZATION)
	public void registerCrafting() {
		new RecipesWands().addRecipes();

		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(BlockLoader.CRYSTAL_BLOCK), "###", "###", "###", '#', "gemCrystal"
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(BlockLoader.MAGIC_CRYSTAL_BLOCK), "###", "###", "###", '#', "gemMagicCrystal"
		));
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(ItemLoader.CRYSTAL, 9), BlockLoader.CRYSTAL_BLOCK
		));
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(ItemLoader.MAGIC_CRYSTAL, 9), BlockLoader.MAGIC_CRYSTAL_BLOCK
		));
	}

	@Load(LoaderState.INITIALIZATION)
	public void registerSmelting() {
		GameRegistry.addSmelting(BlockLoader.CRYSTAL_ORE, new ItemStack(ItemLoader.CRYSTAL), 1.5F);
		GameRegistry.addSmelting(ItemLoader.CRYSTAL, new ItemStack(ItemLoader.MAGIC_CRYSTAL), 2.0F);
		GameRegistry.addSmelting(BlockLoader.CRYSTAL_BLOCK, new ItemStack(BlockLoader.MAGIC_CRYSTAL_BLOCK), 20.0F);
	}

	@Load(LoaderState.INITIALIZATION)
	public void registerFuel() {
		GameRegistry.registerFuelHandler(fuel -> ItemLoader.MAGIC_CRYSTAL != fuel.getItem() ? 0 : 51200);
		GameRegistry.registerFuelHandler(fuel -> Item.getItemFromBlock(BlockLoader.MAGIC_CRYSTAL_BLOCK) != fuel.getItem() ? 0 : 512000);
	}
}
