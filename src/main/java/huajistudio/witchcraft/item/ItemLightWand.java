package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

/**
 * @author sunday
 **/
public class ItemLightWand extends ItemWand {
	@Override
	public double getMagicCost() {
		return 4.0D;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter) {
		return new EntityLightBall(level, shooter) {
			@Override
			protected void onHit(HitResult result) {
				if (level().isClientSide)
					return;
				AreaEffectCloud cloud = new AreaEffectCloud(level(), getX(), getY(), getZ());
				if (getOwner() instanceof LivingEntity owner)
					cloud.setOwner(owner);
				cloud.setParticle(ParticleTypes.ENCHANT);
				cloud.setRadius(75.0F);
				cloud.setDuration(2400);
				cloud.setRadiusPerTick((7.0F - cloud.getRadius()) / (float) cloud.getDuration());
				cloud.addEffect(new MobEffectInstance(MobEffects.HARM, 2400, 1));
				level().addFreshEntity(cloud);
				discard();
			}
		};
	}
}
