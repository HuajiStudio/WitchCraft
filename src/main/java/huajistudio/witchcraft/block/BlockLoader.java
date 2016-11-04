package huajistudio.witchcraft.block;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockLoader implements ILoader<Block> {
	public static final Block CRYSTAL_ORE = new BlockOre() {
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
	}.setUnlocalizedName("crystalOre")
			.setHardness(3.0F)
			.setResistance(5.0F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Block CRYSTAL_BLOCK = (new Block(Material.IRON, MapColor.PURPLE))
			.setUnlocalizedName("crystalBlock")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Block MAGIC_CRYSTAL_BLOCK = (new Block(Material.IRON, MapColor.PURPLE))
			.setUnlocalizedName("magicCrystalBlock")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setLightLevel(2.5F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);

	@Override
	public void register() {
		registerBlock(BlockLoader.CRYSTAL_ORE, "crystal_ore");
		registerBlock(BlockLoader.CRYSTAL_BLOCK, "crystal_block");
		registerBlock(BlockLoader.MAGIC_CRYSTAL_BLOCK, "magic_crystal_block");
	}

	private void registerBlock(Block block, String name) {
		GameRegistry.register(block.setRegistryName(name));
	}
}
