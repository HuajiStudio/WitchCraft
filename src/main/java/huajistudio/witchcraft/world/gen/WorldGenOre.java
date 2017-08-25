package huajistudio.witchcraft.world.gen;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * From cn.mccraft.chinacraft.world.gen.WorldGenCCOre
 *
 * @author Mouse
 **/
public class WorldGenOre extends WorldGenerator {
	private final Set<Integer> dimensionID = new HashSet<>();
	private final Set<Integer> biomeID = new HashSet<>();
	private final int frequency;
	private final int highest, lowest;
	private final WorldGenMinable gen;

	public WorldGenOre(int dimensions[], int biomes[], int frequency, int highest, int lowest, int size, IBlockState oreBlock) {
		this(dimensions, biomes, frequency, highest, lowest, size, oreBlock, null);
	}

	public WorldGenOre(int dimensions[], int frequency, int highest, int lowest, int size, IBlockState oreBlock) {
		this(dimensions, frequency, highest, lowest, size, oreBlock, null);
	}

	public WorldGenOre(int dimensions[], int frequency, int highest, int lowest, int size, IBlockState oreBlock, Predicate<IBlockState> predicate) {
		this(dimensions, new int[0], frequency, highest, lowest, size, oreBlock, predicate);
	}

	public WorldGenOre(int dimensions[], int biomes[], int frequency, int highest, int lowest, int size, IBlockState oreBlock, Predicate<IBlockState> predicate) {
		Arrays.stream(dimensions).forEach(dimensionID::add);
		Arrays.stream(biomes).forEach(biomeID::add);
		this.frequency = frequency;
		this.highest = highest;
		this.lowest = lowest;
		if (predicate == null) gen = new WorldGenMinable(oreBlock, size);
		else gen = new WorldGenMinable(oreBlock, size, predicate);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (!dimensionID.contains(worldIn.provider.getDimension())) return true;

		for (int i = 0; i < frequency; i++) {
			int x = position.getX() + rand.nextInt(16);
			int y = rand.nextInt(highest - lowest) + lowest;
			int z = position.getZ() + rand.nextInt(16);
			BlockPos pos = new BlockPos(x, y, z);

			if (!biomeID.isEmpty() && !biomeID.contains(Biome.getIdForBiome(worldIn.getBiomeForCoordsBody(pos))))
				return true;

			gen.generate(worldIn, rand, pos);
		}
		return true;
	}

	static class CustomPredicate implements Predicate<IBlockState> {
		private final IBlockState block;

		private CustomPredicate(IBlockState block) {
			this.block = block;
		}

		public boolean apply(IBlockState blockState) {
			return blockState != null && blockState.getBlock() == block.getBlock();
		}
	}
}
