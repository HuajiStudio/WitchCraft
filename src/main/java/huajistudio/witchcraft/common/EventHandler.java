package huajistudio.witchcraft.common;

import huajistudio.witchcraft.event.entity.player.LightBallNockEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class EventHandler {
	public static final EventBus EVENT_BUS = new EventBus();

	public EventHandler() {

		MinecraftForge.EVENT_BUS.register(this);
		EventHandler.EVENT_BUS.register(this);
	}

	public static ActionResult<ItemStack> onLightBallNock(ItemStack item, World world, EntityPlayer player, EnumHand hand, boolean hasAmmo) {
		LightBallNockEvent event = new LightBallNockEvent(player, item, hand, world, hasAmmo);
		if (EVENT_BUS.post(event))
			return new ActionResult<>(EnumActionResult.FAIL, item);
		return event.getAction();
	}
}
