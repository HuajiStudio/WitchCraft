package huajistudio.witchcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.entity.LightBallEntity;
import huajistudio.witchcraft.magic.MagicElement;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.EnumMap;
import java.util.Map;

/**
 * Renders the light ball as a fully lit sprite that always faces the camera. Balls with an element have its color and symbol.
 */
public class LightBallRenderer extends EntityRenderer<LightBallEntity> {
	private static final ResourceLocation LIGHTBALL_TEXTURE = WitchCraft.id("textures/entity/lightball.png");
	private static final Map<MagicElement, ResourceLocation> ELEMENT_TEXTURES = new EnumMap<>(MagicElement.class);

	static {
		for (MagicElement element : MagicElement.values())
			ELEMENT_TEXTURES.put(element, WitchCraft.id("textures/entity/lightball_" + element.getSerializedName() + ".png"));
	}

	public LightBallRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected int getBlockLightLevel(LightBallEntity entity, BlockPos pos) {
		return 15;
	}

	@Override
	public void render(LightBallEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(0.3125F, 0.3125F, 0.3125F);
		poseStack.mulPose(entityRenderDispatcher.cameraOrientation());
		PoseStack.Pose pose = poseStack.last();
		VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
		vertex(consumer, pose, packedLight, -0.5F, -0.25F, 0.0F, 1.0F);
		vertex(consumer, pose, packedLight, 0.5F, -0.25F, 1.0F, 1.0F);
		vertex(consumer, pose, packedLight, 0.5F, 0.75F, 1.0F, 0.0F);
		vertex(consumer, pose, packedLight, -0.5F, 0.75F, 0.0F, 0.0F);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int packedLight, float x, float y, float u, float v) {
		consumer.addVertex(pose, x, y, 0.0F)
				.setColor(-1)
				.setUv(u, v)
				.setOverlay(OverlayTexture.NO_OVERLAY)
				.setLight(packedLight)
				.setNormal(pose, 0.0F, 1.0F, 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(LightBallEntity entity) {
		MagicElement element = entity.getElement();
		return element == null ? LIGHTBALL_TEXTURE : ELEMENT_TEXTURES.get(element);
	}
}
