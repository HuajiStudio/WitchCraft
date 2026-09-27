package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.LightBallEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Shoots light balls that leave a large cloud of instant damage where they hit.
 * @author sunday
 **/
public class LightWandItem extends WandItem {
	@Override
	public double getMagicCost() {
		return 4.0D;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter, ItemStack stack) {
		return new LightBallEntity(level, shooter, LightBallEntity.Effect.HARMING_CLOUD);
	}
}
