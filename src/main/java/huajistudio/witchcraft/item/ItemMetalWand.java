package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemMetalWand extends ItemWand {
	@Override
	public double getMagicCost() {
		return 2.0D;
	}

	@Override
	public EntityFireball newBullet(World world, EntityLivingBase shooter) {
		return new EntityLightBall(world, shooter) {
			@Override
			protected void onImpact(@Nonnull RayTraceResult result) {
				if (world.isRemote)
					return;
				double x, y, z;
				if (result.entityHit != null) {
					x = result.entityHit.posX;
					y = result.entityHit.posY;
					z = result.entityHit.posZ;
				} else {
					x = result.getBlockPos().getX();
					y = result.getBlockPos().getY();
					z = result.getBlockPos().getZ();
				}
				world.addWeatherEffect(new EntityLightningBolt(world, x, y, z, false));
				this.setDead();
			}
		};
	}
}
