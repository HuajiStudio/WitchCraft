package huajistudio.witchcraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntitySealCrystal extends Entity {
	public int innerRotation;

	public EntitySealCrystal(World world) {
		super(world);
		preventEntitySpawning = true;
		innerRotation = rand.nextInt(100000);
	}

	public EntitySealCrystal(World world, double x, double y, double z) {
		this(world);
		setPosition(x, y, z);
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(@Nonnull NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(@Nonnull NBTTagCompound compound) {

	}
}
