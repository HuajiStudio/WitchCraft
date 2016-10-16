package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.item.wand.ItemWand;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingLoader {
	private static void registerRecipe() {
		for (ItemWand wand: ItemLoader.wands) {
			GameRegistry.addShapedRecipe(new ItemStack(wand),
					" * ", " # ", " # ",
					'*', wand.getGemMaterial(), '#', wand.getHandleMaterial());
		}
	}
}
