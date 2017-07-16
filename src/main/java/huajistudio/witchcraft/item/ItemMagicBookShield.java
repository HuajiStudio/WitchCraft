package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityMagicShield;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMagicBookShield extends ItemMagicBookSummoner {
	@Override
	public Entity summoned(World world, EntityLivingBase chanter, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return null; // TODO Spawn the shield
	}

	@Override
	public double getMagicCost() {
		return 6.0D;
	}
}
