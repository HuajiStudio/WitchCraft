package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.Namer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

class RecipesWands {
	private final String[] recipePattern = new String[]{"#X#", " W ", " W "};

	void addRecipes() {
		ItemLoader.WAND_MAP.forEach((key, value) -> OreDictionary.getOres("gemMagicCrystal").forEach(gemEntry ->
			GameRegistry.addShapedRecipe(
				new ResourceLocation(WitchCraft.MODID, Namer.buildUnlocalizedName("wand", key.name())),
				new ResourceLocation(WitchCraft.MODID, "wand"),
				new ItemStack(value), recipePattern,
				'#', key.getRepairItemStack(),
				'X', gemEntry.getItem(),
				'W', Items.STICK)
        ));
	}
}
