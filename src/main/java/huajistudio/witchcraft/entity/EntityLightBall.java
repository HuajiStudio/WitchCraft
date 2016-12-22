package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.util.WCDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Could be thrown with the magic wand.
 */
public class EntityLightBall extends EntityFireball implements IProjectile {

	private int knockbackStrength;

	public EntityLightBall(World worldIn) {
		super(worldIn);
		setSize(0.3125F, 0.3125F);
	}

	public EntityLightBall(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z, 0, 0, 0);
		setSize(0.3125F, 0.3125F);
	}

	public EntityLightBall(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, 0, 0, 0);
		posY += shooter.height * 0.75;
		setSize(0.3125F, 0.3125F);
	}

	@Override
	protected void onImpact(@Nonnull RayTraceResult result) {
		if (worldObj.isRemote)
			return;
		if (result.entityHit instanceof EntityLiving) {
			result.entityHit.attackEntityFrom(WCDamageSource.lightBall, 3.0F);
			float motionDist = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			if (motionDist > 0.0F)
				result.entityHit.addVelocity(motionX * knockbackStrength * 0x1.333334p-1 / (double) motionDist, 0.1D, motionZ * knockbackStrength * 0x1.333334p-1 / (double) motionDist);
		}
		setDead();
	}

	@Override
	public float getBrightness(float partialTicks) {
		return 2.0F;
	}

	@Override
	@Nonnull
	protected EnumParticleTypes getParticleType() {
		return EnumParticleTypes.SPELL;
	}

	@Override
	protected boolean isFireballFiery() {
		return false;
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 **/
	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy)
	{
		float f = MathHelper.sqrt_double(x * x + y * y + z * z);
		x = x / (double)f;
		y = y / (double)f;
		z = z / (double)f;
		x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		x = x * (double)velocity;
		y = y * (double)velocity;
		z = z * (double)velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt_double(x * x + z * z);
		this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
		this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
	}

	public void setKnockbackStrength(int knockbackStrength) {
		this.knockbackStrength = knockbackStrength;
	}

	/**
	 * Sets throwable heading based on an entity that's throwing it
	 **/
	public void setHeadingFromThrower(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy)
	{
		float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
		float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
		float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
		this.setThrowableHeading((double)f, (double)f1, (double)f2, velocity, inaccuracy);
		this.motionX += entityThrower.motionX;
		this.motionZ += entityThrower.motionZ;

		if (!entityThrower.onGround)
		{
			this.motionY += entityThrower.motionY;
		}
	}

	public static float getLightBallVelocity(int charge) {
		float f = charge / 20.0F;
		f = f * (f + 2.0F) / 3.0F;
		return f > 1.0F ? 1.0F : f;
	}
}
