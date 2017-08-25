package huajistudio.witchcraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemMagicBookSummoner extends ItemMagicBook {
	public abstract Entity summoned(World world, EntityLivingBase chanter, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ);

	@Override
	public void onUse(EntityLivingBase entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		world.spawnEntity(summoned(world, entity, pos, hand, facing, hitX, hitY, hitZ));
	}
}
