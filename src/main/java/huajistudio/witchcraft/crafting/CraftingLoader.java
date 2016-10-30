package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingLoader implements ILoader<IRecipe> {
	@Override
	public void register() {
		GameRegistry.addRecipe(new ItemStack(BlockLoader.CRYSTAL_BLOCK), "XXX", "XXX", "XXX", 'X', ItemLoader.CRYSTAL);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.CRYSTAL, 9), BlockLoader.CRYSTAL_BLOCK);
	}

	@Override
	public RegistryNamespaced<ResourceLocation, IRecipe> getRegistry() {
		return null;
	}
}
