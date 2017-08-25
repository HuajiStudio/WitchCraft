package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.ItemWand;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EnchantmentLoader {
	public static final EnumEnchantmentType WAND_ENCHANTMENT_TYPE = EnumHelper.addEnchantmentType("WAND", item -> item instanceof ItemWand);

	public static final Enchantment EXPLOSION = new EnchantmentWand(Enchantment.Rarity.RARE) {
		@Override
		public int getMaxLevel() {
			return 3;
		}
	}.setName("explosion");
	public static final Enchantment STABLE_LIGHTBALL = new EnchantmentWand(Enchantment.Rarity.COMMON) {
		@Override
		public int getMaxLevel() { return 5; }
	}.setName("stable_lightball");

	private static int id = 0;

	@Load(LoaderState.INITIALIZATION)
	public void registerEnchantments() {
		registerEnchantment(EXPLOSION, "explosion");
		registerEnchantment(STABLE_LIGHTBALL, "stable_lightball");
	}

	private void registerEnchantment(Enchantment enchantment, String name) {
		ForgeRegistries.ENCHANTMENTS.register(enchantment.setRegistryName(WitchCraft.MODID, name));
	}
}
