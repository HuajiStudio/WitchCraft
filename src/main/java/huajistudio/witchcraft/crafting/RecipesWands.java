package huajistudio.witchcraft.crafting;

import com.google.common.collect.Lists;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.item.ItemWand;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;

class RecipesWands {
	private final String[] recipePattern = new String[]{"#X#", " W ", " W "};
	private final Collection<ItemStack> recipeMaterials = Lists.newArrayList();

	RecipesWands() {
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
	}

	void addRecipes() {
		ItemLoader.WAND_MAP.entrySet().forEach(entry -> {
			ItemStack wandStack = new ItemStack(entry.getValue());
			NBTTagCompound tagCompound = wandStack.getTagCompound();
			try {
				if (tagCompound != null) {
					tagCompound.setInteger("magicCapability", MathHelper.ceiling_float_int(
							((ItemWand) wandStack.getItem()).getMaterial().getMaxUses() * 4.6125f)
					);
					tagCompound.setTag("magicAmount", tagCompound.getTag("magicCapability"));
					wandStack.setTagCompound(tagCompound);
				}
			} catch (Exception ignored) {}
			GameRegistry.addRecipe(wandStack, recipePattern,
					'#', recipeMaterials.parallelStream().filter(stack -> stack.isItemEqualIgnoreDurability(entry.getKey().getRepairItemStack())).findAny().get(),
					'X', ItemLoader.MAGIC_CRYSTAL,
					'W', Items.STICK);
		});
	}
}
