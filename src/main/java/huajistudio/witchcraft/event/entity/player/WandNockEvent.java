package huajistudio.witchcraft.event.entity.player;

import huajistudio.witchcraft.common.WCEventFactory;
import huajistudio.witchcraft.item.ItemNormalWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * WandNockEvent is fired when a player begins using a wand.
 * This event is fired whenever a player begins using a wand in
 * {@link ItemNormalWand#onItemRightClick(ItemStack, World, EntityPlayer, EnumHand)}.
 * This event is fired on the {@link WCEventFactory#EVENT_BUS}
 **/
public class WandNockEvent extends PlayerEvent {
	private final ItemStack wand;
	private final EnumHand hand;
	private final World world;
	private ActionResult<ItemStack> action;

	public WandNockEvent(EntityPlayer player, ItemStack wand, EnumHand hand, World world) {
		super(player);
		this.wand = wand;
		this.hand = hand;
		this.world = world;
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

	public ActionResult<ItemStack> getAction() {
		return action;
	}

	public void setAction(ActionResult<ItemStack> action) {
		this.action = action;
	}
}
