package huajistudio.witchcraft.block;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockLoader {
	/**
	 * Don't set the registry name straightly!
	 **/
	public static final Block CRYSTAL_ORE = new BlockOre() {
		@Nullable
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return ItemLoader.CRYSTAL;
		}

		@Override
		public int getExpDrop(@Nullable IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
		{
			Random rand = world instanceof World ? ((World)world).rand : new Random();
			if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
				return MathHelper.getRandomIntegerInRange(rand, 3, 7);
			return 0;
		}
	}.setUnlocalizedName("crystalOre")
			.setHardness(3.0F)
			.setResistance(5.0F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Block CRYSTAL_BLOCK = (new Block(MaterialLoader.CRYSTAL))
			.setUnlocalizedName("crystalBlock")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Block MAGIC_CRYSTAL_BLOCK = (new Block(MaterialLoader.CRYSTAL))
			.setUnlocalizedName("magicCrystalBlock")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setLightLevel(2.5F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Block CRYSTAL_CLUSTER = (new BlockCrystalCluster())
			.setUnlocalizedName("crystalCluster")
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setLightLevel(2.5F)
			.setCreativeTab(CreativeTabsLoader.WITCHCRAFT);

	@Load(LoaderState.PREINITIALIZATION)
	public void registerBlocks() {
		registerBlock(BlockLoader.CRYSTAL_ORE, "crystal_ore", "oreCrystal");
		registerBlock(BlockLoader.CRYSTAL_BLOCK, "crystal_block", "blockCrystal");
		registerBlock(BlockLoader.MAGIC_CRYSTAL_BLOCK, "magic_crystal_block", "blockMagicCrystal");
		registerBlock(CRYSTAL_CLUSTER, "crystal_cluster.json");
	}

	private void registerBlock(Block block, String registryName) {
		GameRegistry.register(block.setRegistryName(registryName));
		GameRegistry.register((new ItemBlock(block)).setRegistryName(registryName));
	}

	private void registerBlock(Block block, String registryName, String oreDictName) {
		registerBlock(block, registryName);
		OreDictionary.registerOre(oreDictName, block);
	}
}
