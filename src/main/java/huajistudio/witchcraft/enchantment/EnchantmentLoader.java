package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.LoaderState;

public class EnchantmentLoader {
	public static final EnumEnchantmentType WAND_ENCHANTMENT_TYPE = EnumHelper.addEnchantmentType("WAND");

	public static final Enchantment EXPLOSION = new EnchantmentWand(Enchantment.Rarity.RARE) {
		@Override
		public int getMaxLevel() {
			return 3;
		}
	}.setName("explosion");

	private static int id = 100;

	@Load(LoaderState.INITIALIZATION)
	public void registerEnchantments() {
		registerEnchantment(EXPLOSION, "explosion");
	}

	private void registerEnchantment(Enchantment enchantment, String name) {
		Enchantment.REGISTRY.register(id++, new ResourceLocation(name), enchantment.setRegistryName(WitchCraft.MODID, name));
	}
}
