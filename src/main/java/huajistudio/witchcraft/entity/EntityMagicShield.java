package huajistudio.witchcraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMagicShield extends Entity {
	private int duration;

	public EntityMagicShield(World worldIn) {
		super(worldIn);
		duration = 600;
	}

	public EntityMagicShield(World worldIn, double x, double y, double z) {
		this(worldIn);
		setPosition(x, y, z);
	}

	@Override
	protected void entityInit() {
		duration = 100;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		duration = compound.getInteger("duration");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("duration", duration);
	}
}
