package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.registry.WCDamageTypes;
import huajistudio.witchcraft.registry.WCEntityTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Could be thrown with the magic wand. What happens when it hits something depends on its {@link Effect}.
 */
public class LightBallEntity extends AbstractHurtingProjectile {
	private Effect effect = Effect.DAMAGE;
	private int explosionStrength;
	private int knockbackStrength;
	private int life = 100;

	public LightBallEntity(EntityType<? extends LightBallEntity> type, Level level) {
		super(type, level);
		accelerationPower = 0.0;
	}

	public LightBallEntity(Level level, LivingEntity shooter, Effect effect) {
		super(WCEntityTypes.LIGHT_BALL.get(), shooter, Vec3.ZERO, level);
		accelerationPower = 0.0;
		this.effect = effect;
		setPos(getX(), getY() + shooter.getBbHeight() * 0.75, getZ());
	}

	@Override
	protected void onHit(HitResult result) {
		if (level().isClientSide)
			return;
		switch (effect) {
			case DAMAGE -> damage(result);
			case LIGHTNING -> strikeLightning(result);
			case HARMING_CLOUD -> spawnHarmingCloud();
		}
		discard();
	}

	private void damage(HitResult result) {
		if (explosionStrength > 0) {
			level().explode(this, getX(), getY(), getZ(), explosionStrength * 2.0F, Level.ExplosionInteraction.TNT);
		}
		if (result instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof Mob target) {
			target.hurt(WCDamageTypes.lightBall(this, getOwner()), 3.0F);
			Vec3 motion = getDeltaMovement();
			double motionDist = motion.horizontalDistance();
			if (motionDist > 0.0)
				target.push(motion.x * knockbackStrength * 0.6F / motionDist, 0.1D, motion.z * knockbackStrength * 0.6F / motionDist);
		}
	}

	private void strikeLightning(HitResult result) {
		LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level());
		if (bolt != null) {
			bolt.moveTo(result instanceof EntityHitResult entityHit ? entityHit.getEntity().position() : result.getLocation());
			level().addFreshEntity(bolt);
		}
	}

	private void spawnHarmingCloud() {
		AreaEffectCloud cloud = new AreaEffectCloud(level(), getX(), getY(), getZ());
		if (getOwner() instanceof LivingEntity owner)
			cloud.setOwner(owner);
		cloud.setParticle(ParticleTypes.ENCHANT);
		cloud.setRadius(75.0F);
		cloud.setDuration(2400);
		cloud.setRadiusPerTick((7.0F - cloud.getRadius()) / (float) cloud.getDuration());
		cloud.addEffect(new MobEffectInstance(MobEffects.HARM, 2400, 1));
		level().addFreshEntity(cloud);
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
		compound.putString("effect", effect.getSerializedName());
		compound.putInt("explosionStrength", explosionStrength);
		compound.putInt("knockbackStrength", knockbackStrength);
		compound.putInt("life", life);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		effect = Effect.CODEC.byName(compound.getString("effect"), Effect.DAMAGE);
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

	public Effect getEffect() {
		return effect;
	}

	public void setKnockbackStrength(int knockbackStrength) {
		this.knockbackStrength = knockbackStrength;
	}

	public void setExplosionStrength(int strength) {
		explosionStrength = strength;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public enum Effect implements StringRepresentable {
		/** Hurts the mob it hits. Affected by the explosion and punch enchantments. */
		DAMAGE("damage"),
		/** Summons a lightning bolt where it hits. */
		LIGHTNING("lightning"),
		/** Leaves a large cloud of instant damage. */
		HARMING_CLOUD("harming_cloud");

		public static final StringRepresentable.EnumCodec<Effect> CODEC = StringRepresentable.fromEnum(Effect::values);

		private final String name;

		Effect(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return name;
		}
	}
}
