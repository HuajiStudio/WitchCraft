package huajistudio.witchcraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * A tool that costs magic points to use.
 */
public abstract class MagicToolItem extends Item {
	public MagicToolItem(Properties properties) {
		super(properties);
	}

	public abstract double getMagicCost();

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
		tooltip.add(Component.translatable("tooltip.witchcraft.magic_cost", getMagicCost()).withStyle(ChatFormatting.GRAY));
	}
}
