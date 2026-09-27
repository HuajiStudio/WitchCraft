package huajistudio.witchcraft.client;

import com.mojang.blaze3d.vertex.PoseStack;
import huajistudio.witchcraft.client.renderer.MagicItemRenderer;
import huajistudio.witchcraft.item.WandItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

/**
 * While a wand is charged, the arm holding it points it where the player looks, and the other arm is left alone.
 * In first person, the wand rises towards the middle of the screen, and trembles once fully charged like a bow.
 * Element wands are drawn by {@link MagicItemRenderer}.
 */
public class WandClientExtensions implements IClientItemExtensions {
	/** The arm pose {@code WITCHCRAFT_WAND}, added in {@code META-INF/enumextensions.json}. */
	public static final EnumProxy<HumanoidModel.ArmPose> ARM_POSE = new EnumProxy<>(HumanoidModel.ArmPose.class,
			false, (IArmPoseTransformer) WandClientExtensions::pointArm);

	/**
	 * Points the arm like the arm holding a bow.
	 */
	private static void pointArm(HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm) {
		if (arm == HumanoidArm.RIGHT) {
			model.rightArm.xRot = model.head.xRot - Mth.HALF_PI;
			model.rightArm.yRot = model.head.yRot - 0.1F;
		} else {
			model.leftArm.xRot = model.head.xRot - Mth.HALF_PI;
			model.leftArm.yRot = model.head.yRot + 0.1F;
		}
	}

	@Override
	@Nullable
	public HumanoidModel.ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack stack) {
		return entity.isUsingItem() && entity.getUsedItemHand() == hand ? ARM_POSE.getValue() : null;
	}

	@Override
	public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack stack,
			float partialTick, float equipProcess, float swingProcess) {
		InteractionHand hand = arm == player.getMainArm() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		if (!player.isUsingItem() || player.getUseItemRemainingTicks() <= 0 || player.getUsedItemHand() != hand)
			return false;
		int side = arm == HumanoidArm.RIGHT ? 1 : -1;
		float charge = stack.getUseDuration(player) - (player.getUseItemRemainingTicks() - partialTick + 1.0F);
		float progress = WandItem.getChargeProgress(charge);
		// Where an item is normally held, as in ItemInHandRenderer.applyItemArmTransform
		poseStack.translate(side * 0.56F, -0.52F + equipProcess * -0.6F, -0.72F);
		poseStack.translate(side * -0.12F * progress, 0.1F * progress, -0.05F * progress);
		if (progress > 0.1F)
			poseStack.translate(0.0F, Mth.sin((charge - 0.1F) * 1.3F) * (progress - 0.1F) * 0.004F, 0.0F);
		return true;
	}

	@Override
	public BlockEntityWithoutLevelRenderer getCustomRenderer() {
		return MagicItemRenderer.getInstance();
	}
}
