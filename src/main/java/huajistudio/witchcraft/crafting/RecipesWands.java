package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.item.ItemWand;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

class RecipesWands {
	private final String[] recipePattern = new String[]{"#X#", " W ", " W "};

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
					'#', entry.getKey().getRepairItemStack(),
					'X', ItemLoader.MAGIC_CRYSTAL,
					'W', Items.STICK);
		});
	}
}
