package huajistudio.witchcraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.world.World;

public class ItemWitherWand extends ItemWand {
	@Override
	public int getMagicCost() {
		return 3;
	}

	@Override
	public EntityFireball newBullet(World world, EntityLivingBase shooter) {
		return new EntityWitherSkull(world, shooter, 0, 0, 0);
	}
}
