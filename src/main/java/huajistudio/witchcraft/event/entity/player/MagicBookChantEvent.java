package huajistudio.witchcraft.event.entity.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * @author sunday
 **/
public class MagicBookChantEvent extends PlayerEvent {
	private final ItemStack item;
	private final World world;
	private ActionResult<ItemStack> action;

	public MagicBookChantEvent(EntityPlayer player, ItemStack item, World world) {
		super(player);
		this.item = item;
		this.world = world;
	}

	public ItemStack getItem() {
		return item;
	}

	public World getWorld() {
		return world;
	}

	public ActionResult<ItemStack> getAction() {
		return action;
	}

	public void setAction(ActionResult<ItemStack> action) {
		this.action = action;
	}
}
