package huajistudio.witchcraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

/**
 * Could be thrown with the magic wand.
 */
public class EntityLightBall extends EntityFireball {

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
		posY += shooter.getEyeHeight() - 0.10000000149011612D;
		setSize(0.3125F, 0.3125F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (worldObj.isRemote)
			return;
		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
		EntityAreaEffectCloud effectCloud = new EntityAreaEffectCloud(worldObj, posX, posY, posZ);
		effectCloud.setOwner(shootingEntity);
		effectCloud.setParticle(EnumParticleTypes.SPELL);
		effectCloud.setRadius(3.0F);
		effectCloud.setDuration(20);
		effectCloud.setRadiusPerTick((7.0F - effectCloud.getRadius()) / effectCloud.getDuration());
		effectCloud.addEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE));

		if (!list.isEmpty())
			for (EntityLivingBase entity : list) {
				if (getDistanceSqToEntity(entity) < 16.0D) {
					effectCloud.setPosition(entity.posX, entity.posY, entity.posZ);
					break;
				}
			}

		worldObj.playEvent(2006, new BlockPos(posX, posY, posZ), 0);
		worldObj.spawnEntityInWorld(effectCloud);
		setDead();
	}

	@Override
	public float getBrightness(float partialTicks) {
		return 2.0F;
	}

	@Override
	protected EnumParticleTypes getParticleType() {
		return EnumParticleTypes.SPELL;
	}

	@Override
	protected boolean isFireballFiery() {
		return false;
	}

	/**
	 * Sets throwable heading based on an entity that's throwing it
	 */
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

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
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

	public static float getLightBallVelocity(int charge) {
		float f = charge / 20.0F;
		f = f * (f + 2.0F) / 3.0F;
		return f > 1.0F ? 1.0F : f;
	}
}
