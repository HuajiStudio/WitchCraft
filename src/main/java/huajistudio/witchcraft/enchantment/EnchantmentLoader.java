package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.item.ItemWand;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.LoaderState;

public class EnchantmentLoader {
	public static final Enchantment EXPLOSION;

	static {
		EnumHelper.addEnchantmentType("WAND");
		EXPLOSION = new Enchantment(Enchantment.Rarity.RARE, EnumEnchantmentType.valueOf("WAND"),
				new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}) {
			/**
			 * A temporary method to make the enchantment.
			 **/
			@Override
			public boolean canApplyAtEnchantingTable(ItemStack stack) {
				return stack.getItem() instanceof ItemWand;
			}
		}.setName("explosion");
	}

	@Load(LoaderState.INITIALIZATION)
	public void registerEnchantments() {
		// TODO register those enchantments
	}
}
