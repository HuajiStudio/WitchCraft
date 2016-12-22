package huajistudio.witchcraft.world.gen;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGeneratorLoader {
	private static final IWorldGenerator generatorCrystalOre = (random, chunkX, chunkZ, world, chunkGenerator, chunkProvider) -> {
		WorldGenMinable crystalOreGenerator = new WorldGenMinable(BlockLoader.CRYSTAL_ORE.getDefaultState(), 16);
		if (world.provider.getDimension() != 0)
			return;
		for (int i = 0; i < 3; ++i) {
			int posX = chunkX * 16 + random.nextInt(16);
			int posY = 16 + random.nextInt(16);
			int posZ = chunkZ * 16 + random.nextInt(16);
			BlockPos pos = new BlockPos(posX, posY, posZ);
			IBlockState state = world.getBlockState(pos);
			if (world.getBlockState(pos).getBlock().isReplaceableOreGen(state, world, pos, BlockMatcher.forBlock(Blocks.STONE)))
				crystalOreGenerator.generate(world, random, pos);
		}
	};

	@Load(LoaderState.PREINITIALIZATION)
	public void registerWorldGenerators() {
		GameRegistry.registerWorldGenerator(generatorCrystalOre, 1);
	}
}
