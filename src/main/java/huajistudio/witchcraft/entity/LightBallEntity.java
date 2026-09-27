package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.magic.MagicElement;
import huajistudio.witchcraft.registry.WCDamageTypes;
import huajistudio.witchcraft.registry.WCEntityTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import org.jetbrains.annotations.Nullable;

/**
 * Could be thrown with the magic wand. What happens when it hits something depends on its {@link Effect}.
 * Balls shot by an element wand have the element of the wand, which only changes how they look.
 */
public class LightBallEntity extends AbstractHurtingProjectile {
	/** 0 for a plain ball, otherwise the ordinal of the {@link MagicElement} plus 1. */
	private static final EntityDataAccessor<Byte> DATA_ELEMENT = SynchedEntityData.defineId(LightBallEntity.class, EntityDataSerializers.BYTE);

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
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ELEMENT, (byte) 0);
	}

	@Override
	protected void onHit(HitResult result) {
		if (level().isClientSide)
			return;
		switch (effect) {
			case DAMAGE -> damage(result);
			case LIGHTNING -> strikeLightning(result);
			case HARMING_CLOUD -> spawnHarmingCloud();
			case NONE -> {}
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
		// Spawned in tick() instead, since runes need an offset to fly from.
		return null;
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("effect", effect.getSerializedName());
		MagicElement element = getElement();
		if (element != null)
			compound.putString("element", element.getSerializedName());
		compound.putInt("explosionStrength", explosionStrength);
		compound.putInt("knockbackStrength", knockbackStrength);
		compound.putInt("life", life);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		effect = Effect.CODEC.byName(compound.getString("effect"), Effect.DAMAGE);
		setElement(MagicElement.CODEC.byName(compound.getString("element")));
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
		if (level().isClientSide)
			spawnRunes();
		if (tickCount > life)
			discard();
	}

	/**
	 * Leaves a trail of enchanting table runes, each flying into the path of the ball from somewhere around it.
	 */
	private void spawnRunes() {
		for (int i = 0; i < 2; i++)
			level().addParticle(ParticleTypes.ENCHANT, getX(), getY(0.5), getZ(),
					random.nextDouble() - 0.5, random.nextDouble() - 0.5, random.nextDouble() - 0.5);
	}

	public Effect getEffect() {
		return effect;
	}

	@Nullable
	public MagicElement getElement() {
		int id = entityData.get(DATA_ELEMENT);
		return id == 0 ? null : MagicElement.values()[id - 1];
	}

	public void setElement(@Nullable MagicElement element) {
		entityData.set(DATA_ELEMENT, (byte) (element == null ? 0 : element.ordinal() + 1));
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
		HARMING_CLOUD("harming_cloud"),
		/** Does nothing, not even hurting what it hits. For the element wands whose balls are not designed yet. */
		NONE("none");

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
