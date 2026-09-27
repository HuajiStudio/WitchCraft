package huajistudio.witchcraft.event.entity.player;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * @author sunday
 **/
public class MagicBookChantEvent extends PlayerEvent implements ICancellableEvent {
	private final ItemStack item;
	private final Level level;
	private InteractionResultHolder<ItemStack> action;

	public MagicBookChantEvent(Player player, ItemStack item, Level level) {
		super(player);
		this.item = item;
		this.level = level;
	}

	public ItemStack getItem() {
		return item;
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
