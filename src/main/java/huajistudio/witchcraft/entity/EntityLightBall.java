package huajistudio.witchcraft.entity;

import huajistudio.witchcraft.util.WCDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Could be thrown with the magic wand.
 */
public class EntityLightBall extends Entity {

	public EntityLivingBase shootingEntity;

	public EntityLightBall(World worldIn) {
		super(worldIn);
		setSize(0.3125F, 0.3125F);
		noClip = true;
	}

	public EntityLightBall(World worldIn, double x, double y, double z) {
		super(worldIn);
		setSize(0.3125F, 0.3125F);
		setLocationAndAngles(x, y, z, rotationYaw, rotationPitch);
	}

	public EntityLightBall(World worldIn, EntityLivingBase shooter) {
		this(worldIn, shooter.posX, shooter.posY, shooter.posZ);
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setTag("direction", newDoubleNBTList(motionX, motionY, motionZ));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("direction", 9)) {
			NBTTagList tagList = compound.getTagList("direction", 6);
			if (tagList.tagCount() == 3) {
				motionX = tagList.getDoubleAt(0);
				motionY = tagList.getDoubleAt(1);
				motionZ = tagList.getDoubleAt(2);
			}
		} else setDead();
	}

	@Override
	public void onUpdate() {
		if (!worldObj.isRemote || !worldObj.isBlockLoaded(new BlockPos(this))) {
			setDead();
			return;
		}
		super.onUpdate();

	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}

	/**
	 * Called when this EntityLightBall hits a block or entity.
	 * @param result The content of the hit block or entity.
	 **/
	protected void onImpact(RayTraceResult result) {
		if (worldObj.isRemote)
			return;
		// TODO manage the statements
		result.entityHit.attackEntityFrom(WCDamageSource.lightBall, 5.0F);
		setDead();
	}
}
