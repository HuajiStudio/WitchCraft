package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.LoaderState;

public class EnchantmentLoader {
	public Enchantment explode = new EnchantmentExplode();

	@Load(LoaderState.PREINITIALIZATION)
	public void registerEnchantments() {
		explode.setRegistryName(WitchCraft.MODID + ":explode");
	}
}
