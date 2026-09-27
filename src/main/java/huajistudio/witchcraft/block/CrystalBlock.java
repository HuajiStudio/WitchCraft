package huajistudio.witchcraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * A translucent crystal block. The translucent render type is set in its block model.
 */
public class CrystalBlock extends HalfTransparentBlock {
	public static final MapCodec<CrystalBlock> CODEC = simpleCodec(CrystalBlock::new);

	public CrystalBlock(Properties properties) {
		super(properties);
	}

	public static Properties crystalProperties() {
		return BlockBehaviour.Properties.of()
				.mapColor(MapColor.COLOR_PURPLE)
				.noOcclusion()
				.isValidSpawn((state, level, pos, entityType) -> false)
				.isRedstoneConductor((state, level, pos) -> false)
				.isSuffocating((state, level, pos) -> false)
				.isViewBlocking((state, level, pos) -> false);
	}

	@Override
	protected MapCodec<? extends CrystalBlock> codec() {
		return CODEC;
	}
}
