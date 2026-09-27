package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.util.WCDamageSource;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Could be thrown with the magic wand.
 */
public class EntityLightBall extends AbstractHurtingProjectile {
	private int explosionStrength;
	private int knockbackStrength;
	private int life = 100;

	public EntityLightBall(EntityType<? extends EntityLightBall> type, Level level) {
		super(type, level);
		accelerationPower = 0.0;
	}

	public EntityLightBall(Level level, LivingEntity shooter) {
		super(EntityLoader.LIGHT_BALL.get(), shooter, Vec3.ZERO, level);
		accelerationPower = 0.0;
		setPos(getX(), getY() + shooter.getBbHeight() * 0.75, getZ());
	}

	@Override
	protected void onHit(HitResult result) {
		if (level().isClientSide)
			return;
		if (explosionStrength > 0) {
			level().explode(this, getX(), getY(), getZ(), explosionStrength * 2.0F, Level.ExplosionInteraction.TNT);
		}
		if (result instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof Mob target) {
			target.hurt(WCDamageSource.lightBall(this, getOwner()), 3.0F);
			Vec3 motion = getDeltaMovement();
			double motionDist = motion.horizontalDistance();
			if (motionDist > 0.0)
				target.push(motion.x * knockbackStrength * 0.6F / motionDist, 0.1D, motion.z * knockbackStrength * 0.6F / motionDist);
		}
		discard();
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.EFFECT;
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("explosionStrength", explosionStrength);
		compound.putInt("knockbackStrength", knockbackStrength);
		compound.putInt("life", life);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("explosionStrength"))
			explosionStrength = compound.getInt("explosionStrength");
		if (compound.contains("knockbackStrength"))
			knockbackStrength = compound.getInt("knockbackStrength");
		if (compound.contains("life"))
			life = compound.getInt("life");
	}

	@Override
	public boolean isPickable() {
		return getDeltaMovement().length() <= 0.7;
	}

	@Override
	protected float getInertia() {
		return 0.9999F;
	}

	@Override
	public void tick() {
		super.tick();
		if (tickCount > life)
			discard();
	}

	public void setKnockbackStrength(int knockbackStrength) {
		this.knockbackStrength = knockbackStrength;
	}

	public static float getLightBallVelocity(int charge) {
		float f = charge / 20.0F;
		f = f * (f + 2.0F) / 3.0F;
		return f > 1.0F ? 1.0F : f;
	}

	public void setExplosionStrength(int strength) {
		explosionStrength = strength;
	}

	public void setLife(int life) {
		this.life = life;
	}
}
