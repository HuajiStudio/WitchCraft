package huajistudio.witchcraft.crafting;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class WandRecipe extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
		boolean hasMaterial = Arrays.stream(Item.ToolMaterial.values()).anyMatch(material -> {
			ItemStack stack = material.getRepairItemStack();
			return OreDictionary.itemMatches(stack, inv.getStackInRowAndColumn(0, 0), true) && OreDictionary.itemMatches(stack, inv.getStackInRowAndColumn(0, 2), true);
		});
		boolean hasStick = OreDictionary.itemMatches(inv.getStackInRowAndColumn(1, 1), new ItemStack(Items.STICK), false) && OreDictionary.itemMatches(inv.getStackInRowAndColumn(2, 1), new ItemStack(Items.STICK), false);
		boolean hasGem = OreDictionary.itemMatches(inv.getStackInRowAndColumn(0, 1), new ItemStack(ItemLoader.MAGIC_CRYSTAL), false);
		return hasGem && hasStick && hasMaterial;
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
		return Arrays.stream(Item.ToolMaterial.values())
			.filter(toolMaterial -> OreDictionary.itemMatches(toolMaterial.getRepairItemStack(), inv.getStackInRowAndColumn(0, 0), true))
			.filter(ItemLoader.WAND_MAP.keySet()::contains).findFirst().map(ItemLoader.WAND_MAP::get).map(ItemStack::new).orElse(ItemStack.EMPTY);
	}

	@Override
	public boolean canFit(int width, int height) {
		return width >= 9 && height >= 9;
	}

	@Nonnull
	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
}
