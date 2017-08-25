package huajistudio.witchcraft.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface CapabilityMagic {
	@CapabilityInject(MagicStats.class)
	Capability<MagicStats> CAPABILITY_WAND_STATS = null;

	static void register() {
		CapabilityManager.INSTANCE.register(MagicStats.class, new Storage(), Implementation.class);
	}

	class Implementation implements MagicStats {
		private int capacity;
		private int amount;

		@Override
		public int getCapacity() {
			return capacity;
		}

		@Override
		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}

		@Override
		public int getAmount() {
			return amount;
		}

		@Override
		public void setAmount(int amount) {
			this.amount = amount;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Implementation that = (Implementation) o;

			if (capacity != that.capacity) return false;
			return amount == that.amount;
		}

		@Override
		public int hashCode() {
			int result = capacity;
			result = 31 * result + amount;
			return result;
		}
	}
	class Storage implements Capability.IStorage<MagicStats> {
		@Nullable
		@Override
		public NBTBase writeNBT(Capability<MagicStats> capability, MagicStats instance, EnumFacing side) {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setInteger("capacity", instance.getCapacity());
			compound.setInteger("amount", instance.getAmount());
			return compound;
		}

		@Override
		public void readNBT(Capability<MagicStats> capability, MagicStats instance, EnumFacing side, NBTBase nbt) {
			instance.setCapacity(((NBTTagCompound) nbt).getInteger("capacity"));
			instance.setAmount(((NBTTagCompound) nbt).getInteger("amount"));
		}
	}
	class Provider implements ICapabilitySerializable<NBTTagCompound> {
		private MagicStats stats = new Implementation();

		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
			return capability.equals(CAPABILITY_WAND_STATS);
		}

		@Nullable
		@Override
		@SuppressWarnings("unchecked")
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
			return capability.equals(CAPABILITY_WAND_STATS) ? (T) stats : null;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return (NBTTagCompound) CAPABILITY_WAND_STATS.getStorage().writeNBT(CAPABILITY_WAND_STATS, stats, null);
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			CAPABILITY_WAND_STATS.getStorage().readNBT(CAPABILITY_WAND_STATS, stats, null, nbt);
		}
	}
}
