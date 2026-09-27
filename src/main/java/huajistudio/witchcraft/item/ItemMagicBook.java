package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public abstract class ItemMagicBook extends ItemMagicToolBase {
	public ItemMagicBook() {
		super(new Properties());
	}

	public void onUse(ItemStack stack, Level level, LivingEntity entity, int charge) {}

	public void onUse(UseOnContext context) {}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (level.isClientSide)
			return;
		if (!(entity instanceof Player player) || chant(stack, level, player) == InteractionResult.SUCCESS)
			onUse(stack, level, entity, getUseDuration(stack, entity) - timeLeft);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		InteractionResult result = player == null ? InteractionResult.SUCCESS : chant(context.getItemInHand(), context.getLevel(), player);
		if (result == InteractionResult.SUCCESS)
			onUse(context);
		return result;
	}

	private static InteractionResult chant(ItemStack stack, Level level, Player player) {
		InteractionResultHolder<ItemStack> result = WCEventFactory.onMagicBookChant(stack, level, player);
		return result == null ? InteractionResult.SUCCESS : result.getResult();
	}
}
