package huajistudio.witchcraft.common;

import huajistudio.witchcraft.event.entity.player.LightBallNockEvent;
import huajistudio.witchcraft.event.entity.player.LightBallShootEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WCEventFactory {
	public static final EventBus EVENT_BUS = new EventBus();

	public WCEventFactory() {
		MinecraftForge.EVENT_BUS.register(this);
		EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static ActionResult<ItemStack> onLightBallNock(ItemStack item, World world, EntityPlayer player, EnumHand hand) {
		LightBallNockEvent event = new LightBallNockEvent(player, item, hand, world);
		if (EVENT_BUS.post(event))
			return new ActionResult<>(EnumActionResult.FAIL, item);
		return event.getAction();
	}

	@SubscribeEvent
	public static int onLightBallShoot(ItemStack item, World world, EntityPlayer player, int charge) {
		LightBallShootEvent event = new LightBallShootEvent(player, item, world, charge);
		if (EVENT_BUS.post(event))
			return -1;
		return event.getCharge();
	}
}
