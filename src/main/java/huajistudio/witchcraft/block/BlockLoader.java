package huajistudio.witchcraft.block;

import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockLoader implements ILoader<Block> {
	public static final Block CRYSTAL_ORE = new BlockOre(Material.ROCK.getMaterialMapColor()) {
		@Nullable
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return ItemLoader.CRYSTAL;
		}

		@Override
		public int getExpDrop(@Nullable IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
		{
			Random rand = world instanceof World ? ((World)world).rand : new Random();
			if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
			{
				// Experiences dropped by a emerald ore
				return MathHelper.getRandomIntegerInRange(rand, 3, 7);
			}
			return 0;
		}
	}.setUnlocalizedName("crystal_ore");

	public static final Block CRYSTAL_BLOCK = new Block(Material.IRON).setUnlocalizedName("crystal_block");

	@Override
	public void register() {
		registerBlock(CRYSTAL_ORE, "crystal_ore", 1);
		registerBlock(CRYSTAL_BLOCK, "crystal_block", 2);
	}

	private void registerBlock(Block block, String name, int id) {
		block.setRegistryName(name);
		registerImpl(id, block.getRegistryName(), block);
	}

	@Override
	public RegistryNamespaced<ResourceLocation, Block> getRegistry() {
		return Block.REGISTRY;
	}
}
