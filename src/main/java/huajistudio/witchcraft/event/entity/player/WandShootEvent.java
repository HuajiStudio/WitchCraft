package huajistudio.witchcraft.event.entity.player;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class WandShootEvent extends PlayerEvent implements ICancellableEvent {
	private final ItemStack wand;
	private final Level level;
	private int charge;

	public WandShootEvent(Player player, ItemStack wand, Level level, int charge) {
		super(player);
		this.wand = wand;
		this.level = level;
		this.charge = charge;
	}

	public ItemStack getWand() {
		return wand;
	}

	public Level getLevel() {
		return level;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}
}
