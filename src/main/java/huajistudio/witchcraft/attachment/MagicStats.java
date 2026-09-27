package huajistudio.witchcraft.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

/**
 * The magic points of a player, attached to it through {@link AttachmentLoader#MAGIC_STATS}.
 */
public class MagicStats implements INBTSerializable<CompoundTag> {
	private double capacity;
	private double amount;

	public MagicStats(double capacity, double amount) {
		this.capacity = capacity;
		this.amount = amount;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = Math.min(amount, capacity);
	}

	@Override
	public CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag compound = new CompoundTag();
		compound.putDouble("capacity", capacity);
		compound.putDouble("amount", amount);
		return compound;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
		setCapacity(nbt.getDouble("capacity"));
		setAmount(nbt.getDouble("amount"));
	}
}
