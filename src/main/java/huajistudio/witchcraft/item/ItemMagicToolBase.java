package huajistudio.witchcraft.item;

import com.google.common.collect.Multimap;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public abstract class ItemMagicToolBase extends Item {

	public abstract double getMagicCost();

	public ItemMagicToolBase() {
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add("magic cost: " + getMagicCost());
	}
}
