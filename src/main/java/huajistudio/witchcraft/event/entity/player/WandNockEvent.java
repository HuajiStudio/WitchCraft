package huajistudio.witchcraft.event.entity.player;

import huajistudio.witchcraft.item.ItemWand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * WandNockEvent is fired when a player begins using a wand.
 * This event is fired whenever a player begins using a wand in
 * {@link ItemWand#use(Level, Player, InteractionHand)}.
 * This event is fired on the {@link net.neoforged.neoforge.common.NeoForge#EVENT_BUS}
 **/
public class WandNockEvent extends PlayerEvent implements ICancellableEvent {
	private final ItemStack wand;
	private final InteractionHand hand;
	private final Level level;
	private InteractionResultHolder<ItemStack> action;

	public WandNockEvent(Player player, ItemStack wand, InteractionHand hand, Level level) {
		super(player);
		this.wand = wand;
		this.hand = hand;
		this.level = level;
	}

	public ItemStack getWand() {
		return wand;
	}

	public InteractionHand getHand() {
		return hand;
	}

	public Level getLevel() {
		return level;
	}

	public InteractionResultHolder<ItemStack> getAction() {
		return action;
	}

	public void setAction(InteractionResultHolder<ItemStack> action) {
		this.action = action;
	}
}
