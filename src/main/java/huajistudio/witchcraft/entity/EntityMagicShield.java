package huajistudio.witchcraft.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityMagicShield extends Entity {
	private int duration = 600;

	public EntityMagicShield(EntityType<? extends EntityMagicShield> type, Level level) {
		super(type, level);
	}

	public EntityMagicShield(EntityType<? extends EntityMagicShield> type, Level level, double x, double y, double z) {
		this(type, level);
		setPos(x, y, z);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		duration = compound.getInt("duration");
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putInt("duration", duration);
	}
}
