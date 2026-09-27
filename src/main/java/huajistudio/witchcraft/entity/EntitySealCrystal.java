package huajistudio.witchcraft.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntitySealCrystal extends Entity {
	public int innerRotation;

	public EntitySealCrystal(EntityType<? extends EntitySealCrystal> type, Level level) {
		super(type, level);
		blocksBuilding = true;
		innerRotation = random.nextInt(100000);
	}

	public EntitySealCrystal(EntityType<? extends EntitySealCrystal> type, Level level, double x, double y, double z) {
		this(type, level);
		setPos(x, y, z);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {

	}
}
