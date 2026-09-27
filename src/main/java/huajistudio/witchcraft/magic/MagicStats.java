package huajistudio.witchcraft.magic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import huajistudio.witchcraft.registry.WCAttachmentTypes;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

/**
 * The magic points of a player, attached to it through {@link WCAttachmentTypes#MAGIC_STATS}.
 * It is immutable: replace it with {@link Player#setData}, which also syncs it to the player's client.
 */
public record MagicStats(double capacity, double amount) {
	public static final MagicStats DEFAULT = new MagicStats(20, 10);

	public static final Codec<MagicStats> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.DOUBLE.fieldOf("capacity").forGetter(MagicStats::capacity),
			Codec.DOUBLE.fieldOf("amount").forGetter(MagicStats::amount)
	).apply(instance, MagicStats::new));
	public static final StreamCodec<ByteBuf, MagicStats> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.DOUBLE, MagicStats::capacity,
			ByteBufCodecs.DOUBLE, MagicStats::amount,
			MagicStats::new);

	public MagicStats {
		amount = Math.min(amount, capacity);
	}

	public boolean isFull() {
		return amount >= capacity;
	}

	public MagicStats withAmount(double amount) {
		return new MagicStats(capacity, amount);
	}

	public static MagicStats get(Player player) {
		return player.getData(WCAttachmentTypes.MAGIC_STATS);
	}

	/**
	 * Takes {@code cost} magic from the player. Players in creative mode always succeed without paying.
	 * @return whether the player could pay
	 */
	public static boolean consume(Player player, double cost) {
		if (player.getAbilities().instabuild)
			return true;
		MagicStats stats = get(player);
		if (stats.amount() < cost)
			return false;
		player.setData(WCAttachmentTypes.MAGIC_STATS, stats.withAmount(stats.amount() - cost));
		return true;
	}
}
