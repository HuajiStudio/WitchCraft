package huajistudio.witchcraft.item;

import huajistudio.witchcraft.event.WCEventHooks;
import huajistudio.witchcraft.magic.MagicStats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public abstract class MagicBookItem extends MagicToolItem {
	public MagicBookItem() {
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
		if (context.getLevel().isClientSide)
			return InteractionResult.SUCCESS;
		Player player = context.getPlayer();
		InteractionResult result = player == null ? InteractionResult.SUCCESS : chant(context.getItemInHand(), context.getLevel(), player);
		if (result == InteractionResult.SUCCESS)
			onUse(context);
		return result;
	}

	private InteractionResult chant(ItemStack stack, Level level, Player player) {
		InteractionResultHolder<ItemStack> result = WCEventHooks.onMagicBookChant(stack, level, player);
		if (result != null && result.getResult() != InteractionResult.SUCCESS)
			return result.getResult();
		return MagicStats.consume(player, getMagicCost()) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}
}
