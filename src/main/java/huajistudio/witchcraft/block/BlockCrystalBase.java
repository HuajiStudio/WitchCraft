package huajistudio.witchcraft.block;

import net.minecraft.block.BlockBreakable;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;

public class BlockCrystalBase extends BlockBreakable {
	public BlockCrystalBase() {
		super(MaterialLoader.CRYSTAL, false);
	}

	@Nonnull
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
