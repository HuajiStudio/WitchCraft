package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.util.WitchCraftDamageSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Could be thrown with the magic wand.
 */
public class EntityLightBall extends EntityFireball {
	public EntityLightBall(World worldIn) {
		super(worldIn);
	}

	public EntityLightBall(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(worldIn, x, y, z, accelX, accelY, accelZ);
	}

	public EntityLightBall(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
		super(worldIn, shooter, accelX, accelY, accelZ);
	}

	@Override
	@Nonnull
	protected EnumParticleTypes getParticleType() {
		return EnumParticleTypes.SPELL;
	}

	@Override
	protected void onImpact(@Nonnull RayTraceResult result) {
		if (worldObj.isRemote)
			return;
		if (result.entityHit instanceof EntityLiving) {
			result.entityHit.attackEntityFrom(WitchCraftDamageSource.lightBall, 4);
		} else if (result.entityHit instanceof EntityArrow || result.entityHit instanceof EntityLightBall) {
			result.entityHit.setDead();
		}
		setDead();
	}
}
