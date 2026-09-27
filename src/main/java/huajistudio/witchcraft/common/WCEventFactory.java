package huajistudio.witchcraft.common;

import huajistudio.witchcraft.event.entity.player.MagicBookChantEvent;
import huajistudio.witchcraft.event.entity.player.WandNockEvent;
import huajistudio.witchcraft.event.entity.player.WandShootEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;

import javax.annotation.Nullable;

/**
 * Fires the WitchCraft events on {@link NeoForge#EVENT_BUS}.
 */
public final class WCEventFactory {
	/**
	 * @return the result set by a listener, or {@code null} if the wand should be used as usual
	 */
	@Nullable
	public static InteractionResultHolder<ItemStack> onWandNock(ItemStack item, Level level, Player player, InteractionHand hand) {
		WandNockEvent event = NeoForge.EVENT_BUS.post(new WandNockEvent(player, item, hand, level));
		if (event.isCanceled())
			return InteractionResultHolder.fail(item);
		return event.getAction();
	}

	/**
	 * @return the charge to shoot with, or -1 if the shot was cancelled
	 */
	public static int onWandShoot(ItemStack item, Level level, Player player, int charge) {
		WandShootEvent event = NeoForge.EVENT_BUS.post(new WandShootEvent(player, item, level, charge));
		if (event.isCanceled())
			return -1;
		return event.getCharge();
	}

	/**
	 * @return the result set by a listener, or {@code null} if the book should be chanted as usual
	 */
	@Nullable
	public static InteractionResultHolder<ItemStack> onMagicBookChant(ItemStack item, Level level, Player player) {
		MagicBookChantEvent event = NeoForge.EVENT_BUS.post(new MagicBookChantEvent(player, item, level));
		if (event.isCanceled())
			return InteractionResultHolder.fail(item);
		return event.getAction();
	}
}
