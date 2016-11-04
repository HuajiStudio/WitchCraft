package huajistudio.witchcraft.item;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemWand extends Item {
	private final float attackDamage;
	private final ToolMaterial material;

	public ItemWand(ToolMaterial material) {
		this.material = material;
		maxStackSize = 1;
		setMaxDamage(material.getMaxUses());
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
		attackDamage = 3.0F;
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Block block = state.getBlock();
		if (block == Blocks.WEB)
			return 15.0F;
		else {
			Material material = state.getMaterial();
			return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}
}
