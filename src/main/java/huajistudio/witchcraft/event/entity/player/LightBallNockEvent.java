package huajistudio.witchcraft.event.entity.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class LightBallNockEvent extends PlayerEvent {
	private final ItemStack wand;
	private final EnumHand hand;
	private final World world;
	private final boolean hasAmmo;
	private ActionResult<ItemStack> action;

	public LightBallNockEvent(EntityPlayer player, ItemStack wand, EnumHand hand, World world, boolean hasAmmo) {
		super(player);
		this.wand = wand;
		this.hand = hand;
		this.world = world;
		this.hasAmmo = hasAmmo;
	}

	public ItemStack getWand() {
		return wand;
	}

	public EnumHand getHand() {
		return hand;
	}

	public World getWorld() {
		return world;
	}

	public boolean hasAmmo() {
		return hasAmmo;
	}

	public ActionResult<ItemStack> getAction() {
		return action;
	}

	public void setAction(ActionResult<ItemStack> action) {
		this.action = action;
	}
}
