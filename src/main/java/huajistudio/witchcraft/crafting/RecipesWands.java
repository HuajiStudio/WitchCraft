package huajistudio.witchcraft.crafting;

import com.google.common.collect.Lists;
import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;

public class RecipesWands {
	private final String[] recipePattern = new String[]{"#X#", " W ", " W "};
	private final Collection<ItemStack> recipeMaterials = Lists.newArrayList();
	private final Collection<Item> recipeWands = Lists.newArrayList();

	public RecipesWands() {
		if (Loader.isModLoaded("IC2")) {
			recipeMaterials.addAll(OreDictionary.getOres("ingotCopper"));
			recipeMaterials.addAll(OreDictionary.getOres("ingotBronze"));
			recipeMaterials.addAll(OreDictionary.getOres("ingotSliver"));
			recipeMaterials.addAll(OreDictionary.getOres("ingotLead"));
		}

		recipeMaterials.addAll(OreDictionary.getOres("ingotIron"));
		recipeMaterials.addAll(OreDictionary.getOres("ingotGold"));
		recipeMaterials.addAll(OreDictionary.getOres("plankWood"));
		recipeMaterials.addAll(OreDictionary.getOres("gemDiamond"));
		recipeMaterials.addAll(OreDictionary.getOres("cobblestone"));
		recipeMaterials.addAll(OreDictionary.getOres("blockRedstone"));
		recipeWands.addAll(ItemLoader.WAND_MAP.values());
	}

	public void addRecipes() {
		for (int i = 0; i < recipeWands.size(); i++) {
			GameRegistry.addRecipe(new ItemStack(recipeWands.toArray(new Item[0])[i]), recipePattern,
					'#', recipeMaterials.toArray()[i],
					'X', ItemLoader.MAGIC_CRYSTAL,
					'W', Items.STICK);
		}
	}
}
