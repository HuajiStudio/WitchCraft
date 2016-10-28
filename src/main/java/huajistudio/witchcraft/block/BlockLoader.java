package huajistudio.witchcraft.block;

import huajistudio.witchcraft.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
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
	}.setUnlocalizedName("crystal_ore");
}
