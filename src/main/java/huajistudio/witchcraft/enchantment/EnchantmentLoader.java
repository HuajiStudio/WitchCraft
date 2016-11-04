package huajistudio.witchcraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentLoader {
	public static final Enchantment explode = (new Enchantment(Enchantment.Rarity.RARE, EnumEnchantmentType.BOW,
			new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}) {
		@Override
		public boolean canApplyTogether(Enchantment ench) {
			return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT;
		}
	})
			.setName("explode");
}
