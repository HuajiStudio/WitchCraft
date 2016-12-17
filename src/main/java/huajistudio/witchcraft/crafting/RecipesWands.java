package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesWands {
	private final String[] recipePatterns = new String[]{"#X#", " W ", " W "};
	private final Object[] recipeMaterials = new Object[]{Blocks.PLANKS, Blocks.COBBLESTONE, Items.IRON_INGOT, Items.DIAMOND, Items.GOLD_INGOT};
	private final Item[] recipeWands = new Item[]{ItemLoader.WOODEN_WAND, ItemLoader.STONE_WAND, ItemLoader.IRON_WAND, ItemLoader.DIAMOND_WAND, ItemLoader.GOLDEN_WAND};

	public void addRecipes() {
		for (int i = 0; i < recipeWands.length; i++) {
			GameRegistry.addRecipe(new ItemStack(recipeWands[i]), recipePatterns,
					'#', recipeMaterials[i],
					'X', ItemLoader.MAGIC_CRYSTAL,
					'W', Items.STICK);
		}
	}
}
