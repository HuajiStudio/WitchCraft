package huajistudio.witchcraft.block;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockLoader {
	public static final Block CRYSTAL_ORE = new BlockOre() {
		@Nullable
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return ItemLoader.CRYSTAL;
		}
	}
			.setUnlocalizedName("crystalOre")
			.setHardness(3.0F)
			.setResistance(5.0F);
	public static final Block CRYSTAL_BLOCK = (new Block(Material.IRON, MapColor.PURPLE))
			.setUnlocalizedName("crystalBlock")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static final Block MAGIC_CRYSTAL_BLOCK = (new Block(Material.IRON, MapColor.PURPLE))
			.setUnlocalizedName("magicCrystalBlock")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setLightLevel(0.5F)
			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
}
