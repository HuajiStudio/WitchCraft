package huajistudio.witchcraft.client;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.WandItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import org.jetbrains.annotations.Nullable;

/**
 * Tells how far a wand being rendered has been charged, so that its floating crystal can turn white.
 */
@EventBusSubscriber(modid = WitchCraft.MODID, value = Dist.CLIENT)
public class WandChargeEffects {
	/** The entity being rendered, whose items in hand are rendered with it. */
	@Nullable
	private static LivingEntity renderedEntity;

	@SubscribeEvent
	public static void onRenderLivingPre(RenderLivingEvent.Pre<?, ?> event) {
		renderedEntity = event.getEntity();
	}

	@SubscribeEvent
	public static void onRenderLivingPost(RenderLivingEvent.Post<?, ?> event) {
		renderedEntity = null;
	}

	/**
	 * @return how far the wand being rendered has been charged, from 0 to 1, or 0 if it is not being used
	 */
	public static float getCharge(ItemStack stack, ItemDisplayContext displayContext) {
		LivingEntity holder = displayContext.firstPerson() ? Minecraft.getInstance().player
				: displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND ? renderedEntity
				: null;
		if (holder == null || !holder.isUsingItem() || holder.getUseItem() != stack)
			return 0;
		float partialTick = Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(false);
		return WandItem.getChargeProgress(holder.getTicksUsingItem() + partialTick);
	}
}
