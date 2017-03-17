package huajistudio.witchcraft.util.wand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.math.MathHelper;

/**
 * @author sunday
 **/
public final class WandHelper {
	public static final float PI_180 = 0.017453292F;
	public static final float PI_R_180 = 57.295779513F;

	public static void initBulletHeading(EntityFireball bullet, EntityLivingBase shooter, float pitchOffset, float velocity, float inaccuracy) {
		if (bullet instanceof IProjectile) {
			((IProjectile) bullet).setThrowableHeading(shooter.posX, shooter.posY, shooter.posZ, velocity, inaccuracy);
			return;
		}
		float f1 = -MathHelper.sin(shooter.rotationYaw * PI_180) * MathHelper.cos(shooter.rotationPitch * PI_180);
		float f2 = -MathHelper.sin(shooter.rotationPitch * PI_180);
		float f3 = MathHelper.cos(shooter.rotationYaw * PI_180) * MathHelper.cos(shooter.rotationPitch * PI_180);
		float x = MathHelper.sqrt_float(f1 * f1 + f2 * f2 + f3 * f3);
		f1 = f1 / x * velocity;
		f2 = f2 / x * velocity;
		f3 = f3 / x * velocity;
		bullet.motionX = f1;
		bullet.motionY = f2;
		bullet.motionZ = f3;
		x = MathHelper.sqrt_float(f1 * f1 + f3 * f3);
		bullet.rotationYaw = (float) (MathHelper.atan2(f1, f3) * PI_R_180);
		bullet.rotationPitch = (float) (MathHelper.atan2(f2, x) * PI_R_180);

		bullet.motionX += shooter.motionX;
		bullet.motionZ += shooter.motionZ;
		if (!shooter.onGround)
			bullet.motionY += shooter.motionY;
	}
}
