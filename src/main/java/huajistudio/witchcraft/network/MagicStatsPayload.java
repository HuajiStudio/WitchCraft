package huajistudio.witchcraft.network;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.attachment.AttachmentLoader;
import huajistudio.witchcraft.attachment.MagicStats;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Sends a player's magic stats to their client, so that the HUD can show them.
 */
public record MagicStatsPayload(double capacity, double amount) implements CustomPacketPayload {
	public static final Type<MagicStatsPayload> TYPE = new Type<>(WitchCraft.id("magic_stats"));
	public static final StreamCodec<ByteBuf, MagicStatsPayload> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.DOUBLE, MagicStatsPayload::capacity,
			ByteBufCodecs.DOUBLE, MagicStatsPayload::amount,
			MagicStatsPayload::new);

	public static void register(RegisterPayloadHandlersEvent event) {
		event.registrar("1").playToClient(TYPE, STREAM_CODEC, MagicStatsPayload::handle);
	}

	public static void sync(ServerPlayer player) {
		MagicStats stats = player.getData(AttachmentLoader.MAGIC_STATS);
		PacketDistributor.sendToPlayer(player, new MagicStatsPayload(stats.getCapacity(), stats.getAmount()));
	}

	private void handle(IPayloadContext context) {
		MagicStats stats = context.player().getData(AttachmentLoader.MAGIC_STATS);
		stats.setCapacity(capacity);
		stats.setAmount(amount);
	}

	@Override
	public Type<MagicStatsPayload> type() {
		return TYPE;
	}
}
