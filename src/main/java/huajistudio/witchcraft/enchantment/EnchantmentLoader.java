package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentLoader {
	public static Enchantment explode = new EnchantmentExplode();

	public EnchantmentLoader() {
		explode.setRegistryName(WitchCraft.MODID + ":explode");
	}
}
