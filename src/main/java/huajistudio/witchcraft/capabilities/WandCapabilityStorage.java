package huajistudio.witchcraft.capabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class WandCapabilityStorage implements Capability.IStorage<ItemStack> {
	@Override
	public NBTBase writeNBT(Capability<ItemStack> capability, ItemStack instance, EnumFacing side) {
		NBTTagCompound stackCompound = instance.getTagCompound();
		//TODO Write instance's nbt data into stackCompound
		return stackCompound;
	}

	@Override
	public void readNBT(Capability<ItemStack> capability, ItemStack instance, EnumFacing side, NBTBase nbt) {
		if (!(nbt instanceof NBTTagCompound))
			return;
		NBTTagCompound stackCompound = (NBTTagCompound) nbt;
		//TODO Read nbt data into instance
		instance.setTagCompound(stackCompound);
	}
}
