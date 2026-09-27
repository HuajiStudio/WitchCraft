package huajistudio.witchcraft.common;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.attachment.AttachmentLoader;
import huajistudio.witchcraft.attachment.MagicStats;
import huajistudio.witchcraft.network.MagicStatsPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Regenerates the magic of every player on the server.
 */
@EventBusSubscriber(modid = WitchCraft.MODID)
public class MagicTickable {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			MagicStats stats = player.getData(AttachmentLoader.MAGIC_STATS);
			stats.setAmount(stats.getAmount() + 0.05);
			if (player.tickCount % 10 == 0)
				MagicStatsPayload.sync(player);
		}
	}
}
