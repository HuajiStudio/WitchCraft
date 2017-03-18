package huajistudio.witchcraft.item;

import com.google.common.collect.Multimap;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

import java.util.UUID;

/**
 *
 */
public abstract class ItemMagicToolBase extends Item {
	public static final IAttribute MAGIC_COST = new RangedAttribute(null, "generic.magicCost", 1.0D, 0.5D, 10.0D).setShouldWatch(true);

	protected static final UUID MAGIC_COST_MODIFIER = UUID.fromString("D96C4C0A-7D3A-400B-9B4A-E832A1087D1A");

	public abstract double getMagicCost();

	public ItemMagicToolBase() {
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(MAGIC_COST.getAttributeUnlocalizedName(), new AttributeModifier(MAGIC_COST_MODIFIER, "Magic Tool Modifier", getMagicCost(), 0));
		}

		return multimap;
	}
}
