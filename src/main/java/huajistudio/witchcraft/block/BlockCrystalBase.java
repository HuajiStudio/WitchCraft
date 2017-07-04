package huajistudio.witchcraft.block;

import net.minecraft.block.BlockBreakable;
import net.minecraft.util.BlockRenderLayer;

public class BlockCrystalBase extends BlockBreakable {
	public BlockCrystalBase() {
		super(MaterialLoader.CRYSTAL, false);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
