package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

class RecipesWands {
	private final String[] recipePattern = new String[]{"#X#", " W ", " W "};

	void addRecipes() {
		ItemLoader.WAND_MAP.forEach((key, value) ->
			ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("misc"),
					new ItemStack(value), recipePattern,
					'#', key.getRepairItemStack(),
					'X', "gemMagicCrystal",
					'W', "stickWood")
					.setRegistryName(new ResourceLocation(WitchCraft.MODID, "wand" + "_" + key.name()))
			)
		);
	}
}
