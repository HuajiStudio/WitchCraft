package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ItemMetalWand extends ItemWand {
	@Override
	public double getMagicCost() {
		return 2.0D;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter) {
		return new EntityLightBall(level, shooter) {
			@Override
			protected void onHit(HitResult result) {
				if (level().isClientSide)
					return;
				Vec3 pos;
				if (result instanceof EntityHitResult entityHit)
					pos = entityHit.getEntity().position();
				else
					pos = Vec3.atLowerCornerOf(((BlockHitResult) result).getBlockPos());
				LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level());
				if (bolt != null) {
					bolt.moveTo(pos);
					level().addFreshEntity(bolt);
				}
				discard();
			}
		};
	}
}
