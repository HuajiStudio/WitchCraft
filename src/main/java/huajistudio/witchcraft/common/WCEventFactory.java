package huajistudio.witchcraft.common;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.capability.CapabilityMagic;
import huajistudio.witchcraft.capability.MagicStats;
import huajistudio.witchcraft.event.entity.player.MagicBookChantEvent;
import huajistudio.witchcraft.event.entity.player.WandNockEvent;
import huajistudio.witchcraft.event.entity.player.WandShootEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WCEventFactory {
	public static final EventBus EVENT_BUS = new EventBus();

	public WCEventFactory() {
		MinecraftForge.EVENT_BUS.register(this);
		EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static ActionResult<ItemStack> onWandNock(ItemStack item, World world, EntityPlayer player, EnumHand hand) {
		WandNockEvent event = new WandNockEvent(player, item, hand, world);
		if (EVENT_BUS.post(event))
			return new ActionResult<>(EnumActionResult.FAIL, item);
		return event.getAction();
	}

	@SubscribeEvent
	public static int onWandShoot(ItemStack item, World world, EntityPlayer player, int charge) {
		WandShootEvent event = new WandShootEvent(player, item, world, charge);
		if (EVENT_BUS.post(event))
			return -1;
		return event.getCharge();
	}

	@SubscribeEvent
	public static ActionResult<ItemStack> onMagicBookChant(ItemStack item, World world, EntityPlayer player) {
		MagicBookChantEvent event = new MagicBookChantEvent(player, item, world);
		if (EVENT_BUS.post(event))
			return new ActionResult<>(EnumActionResult.FAIL, item);
		return event.getAction();
	}

	@SubscribeEvent
	public static void attachWandStack(AttachCapabilitiesEvent<Entity> attachEvent) {
		if (attachEvent.getObject() instanceof EntityPlayer) {
			attachEvent.addCapability(new ResourceLocation(WitchCraft.MODID, "wand_stats"), new CapabilityMagic.Provider());
			MagicStats stats = attachEvent.getObject().getCapability(CapabilityMagic.CAPABILITY_MAGIC_STATS, null);
			stats.setAmount(10);
			stats.setCapacity(20);
		}
	}
}
