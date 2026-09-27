package huajistudio.witchcraft.registry;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.magic.MagicStats;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class WCAttachmentTypes {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
			DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, WitchCraft.MODID);

	/**
	 * Only synced to the player it belongs to, for the HUD. It is not kept on death.
	 */
	public static final Supplier<AttachmentType<MagicStats>> MAGIC_STATS = ATTACHMENT_TYPES.register("magic_stats",
			() -> AttachmentType.builder(() -> MagicStats.DEFAULT)
					.serialize(MagicStats.CODEC)
					.sync((holder, to) -> holder == to, MagicStats.STREAM_CODEC)
					.build());
}
