package huajistudio.witchcraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

/**
 * A crystal cluster growing out of stone or cobblestone.
 */
public class BlockCrystalCluster extends DirectionalBlock {
	public static final MapCodec<BlockCrystalCluster> CODEC = simpleCodec(BlockCrystalCluster::new);

	public BlockCrystalCluster(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends BlockCrystalCluster> codec() {
		return CODEC;
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState support = level.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
		return support.is(Blocks.STONE) || support.is(Blocks.COBBLESTONE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getClickedFace());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
