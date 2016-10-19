package huajistudio.witchcraft.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Could be thrown with the magic wand.
 */
public class EntityLightBall extends EntityThrowable {
	public EntityLightBall(World worldIn) {
		super(worldIn);
	}

	public EntityLightBall(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityLightBall(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@Override
	protected float getGravityVelocity()
	{ return 0; }

	@Override
	protected void onImpact(RayTraceResult result) {
		if (worldObj.isRemote)
			return;
		if (result.entityHit instanceof EntityLiving) {
			// TODO get damage
		} else if (result.entityHit instanceof EntityArrow) {
			result.entityHit.setDead();
		} else {}
		setDead();
	}
}
