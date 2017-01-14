package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.item.ItemWand;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentWand extends Enchantment {
	public EnchantmentWand(Rarity rarityIn) {
		super(rarityIn, EnchantmentLoader.WAND_ENCHANTMENT_TYPE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof ItemWand;
	}
}
