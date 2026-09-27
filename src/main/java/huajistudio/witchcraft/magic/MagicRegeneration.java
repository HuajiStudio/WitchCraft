package huajistudio.witchcraft.magic;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.registry.WCAttachmentTypes;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Regenerates the magic of every player on the server, 1 point per second.
 */
@EventBusSubscriber(modid = WitchCraft.MODID)
public class MagicRegeneration {
	private static final int INTERVAL = 10;
	private static final double AMOUNT = 0.5;

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		// Regenerate in steps, since every change is synced to the client.
		if (event.getEntity() instanceof ServerPlayer player && player.tickCount % INTERVAL == 0) {
			MagicStats stats = MagicStats.get(player);
			if (!stats.isFull())
				player.setData(WCAttachmentTypes.MAGIC_STATS, stats.withAmount(stats.amount() + AMOUNT));
		}
	}
}
