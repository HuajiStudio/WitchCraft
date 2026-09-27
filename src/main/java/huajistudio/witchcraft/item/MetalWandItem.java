package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.LightBallEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Shoots light balls that summon a lightning bolt where they hit.
 */
public class MetalWandItem extends WandItem {
	@Override
	public double getMagicCost() {
		return 2.0D;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter, ItemStack stack) {
		return new LightBallEntity(level, shooter, LightBallEntity.Effect.LIGHTNING);
	}
}
