package huajistudio.witchcraft.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 *
 */
public abstract class ItemMagicToolBase extends Item {

	public abstract double getMagicCost();

	public ItemMagicToolBase(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
		tooltip.add(Component.literal("Magic cost: " + getMagicCost()));
	}
}
