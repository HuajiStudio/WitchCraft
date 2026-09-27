package huajistudio.witchcraft.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ItemWitherWand extends ItemWand {
	@Override
	public double getMagicCost() {
		return 3.0D;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter) {
		return new WitherSkull(level, shooter, Vec3.ZERO);
	}
}
