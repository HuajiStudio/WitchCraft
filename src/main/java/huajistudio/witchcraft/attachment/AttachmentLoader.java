package huajistudio.witchcraft.attachment;

import huajistudio.witchcraft.WitchCraft;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentLoader {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
			DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, WitchCraft.MODID);

	/**
	 * Every player starts with 10 of 20 magic points. The stats are not kept on death.
	 */
	public static final Supplier<AttachmentType<MagicStats>> MAGIC_STATS = ATTACHMENT_TYPES.register("magic_stats",
			() -> AttachmentType.serializable(() -> new MagicStats(20, 10)).build());
}
