package huajistudio.witchcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.client.WandChargeEffects;
import huajistudio.witchcraft.item.ElementWandItem;
import huajistudio.witchcraft.item.WandItem;
import huajistudio.witchcraft.registry.WCItems;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Draws magic crystals floating slightly up and down, and wands in the world with their crystal floating on top,
 * the same way as the crystal item: the magic crystal for plain wands, the element crystal for element wands.
 * While a wand is charged, its crystal turns white. In the inventory, wands are drawn flat with their crystal.
 * <p>
 * The item models only set the display transforms and use this renderer through {@code builtin/entity};
 * what is drawn are the standalone models in {@code models/item/sprite}, whose textures may be animated themselves.
 */
public class MagicItemRenderer extends BlockEntityWithoutLevelRenderer {
	public static final ModelResourceLocation CRYSTAL_GLOW = ModelResourceLocation.standalone(WitchCraft.id("item/sprite/crystal_glow"));
	private static final long FLOAT_PERIOD = 3000;
	private static final float FLOAT_HEIGHT = 0.5F / 16;
	/** Where the center of the crystal floats on an element wand, in the model space of the wand. */
	private static final float WAND_CRYSTAL_X = 0.75F, WAND_CRYSTAL_Y = 0.75F;
	private static final float WAND_CRYSTAL_SCALE = 0.45F;

	@Nullable
	private static MagicItemRenderer instance;

	private MagicItemRenderer(Minecraft minecraft) {
		super(minecraft.getBlockEntityRenderDispatcher(), minecraft.getEntityModels());
	}

	public static MagicItemRenderer getInstance() {
		if (instance == null)
			instance = new MagicItemRenderer(Minecraft.getInstance());
		return instance;
	}

	/**
	 * @return the standalone model drawn for the item, which has to be registered with {@code ModelEvent.RegisterAdditional}
	 */
	public static ModelResourceLocation getSprite(Item item) {
		return getSprite(item, "");
	}

	/**
	 * @return the standalone model of the wand without its crystal
	 */
	public static ModelResourceLocation getBodySprite(Item wand) {
		return getSprite(wand, "_body");
	}

	private static ModelResourceLocation getSprite(Item item, String suffix) {
		return ModelResourceLocation.standalone(WitchCraft.id("item/sprite/" + BuiltInRegistries.ITEM.getKey(item).getPath() + suffix));
	}

	private static Item getCrystal(WandItem wand) {
		return wand instanceof ElementWandItem elementWand ? WCItems.getCrystal(elementWand.getElement()) : WCItems.MAGIC_CRYSTAL.get();
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer,
			int packedLight, int packedOverlay) {
		if (!(stack.getItem() instanceof WandItem wand)) {
			renderFloating(getSprite(stack.getItem()), stack, poseStack, buffer, packedLight, packedOverlay);
			return;
		}
		if (displayContext == ItemDisplayContext.GUI) {
			renderSprite(getSprite(wand), stack, poseStack, buffer, packedLight, packedOverlay);
			return;
		}
		renderSprite(getBodySprite(wand), stack, poseStack, buffer, packedLight, packedOverlay);
		poseStack.pushPose();
		poseStack.translate(WAND_CRYSTAL_X, WAND_CRYSTAL_Y, 0.5F);
		// Along the wand, which points to the top right of its texture
		poseStack.mulPose(Axis.ZP.rotationDegrees(-45.0F));
		poseStack.scale(WAND_CRYSTAL_SCALE, WAND_CRYSTAL_SCALE, WAND_CRYSTAL_SCALE);
		poseStack.translate(-0.5F, -0.5F, -0.5F);
		float charge = WandChargeEffects.getCharge(stack, displayContext);
		poseStack.pushPose();
		floatUpAndDown(poseStack);
		renderSprite(getSprite(getCrystal(wand)), stack, poseStack, buffer, packedLight, packedOverlay);
		if (charge > 0) {
			// A glowing white crystal over it, a bit thicker so that it is drawn in front
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.scale(1.0F, 1.0F, 1.2F);
			poseStack.translate(-0.5F, -0.5F, -0.5F);
			BakedModel glow = Minecraft.getInstance().getModelManager().getModel(CRYSTAL_GLOW);
			for (RenderType renderType : glow.getRenderTypes(stack, true))
				Minecraft.getInstance().getItemRenderer().renderModelLists(glow, stack, LightTexture.FULL_BRIGHT, packedOverlay, poseStack,
						new AlphaVertexConsumer(buffer.getBuffer(renderType), charge));
		}
		poseStack.popPose();
		poseStack.popPose();
	}

	private static void renderFloating(ModelResourceLocation sprite, ItemStack stack, PoseStack poseStack, MultiBufferSource buffer,
			int packedLight, int packedOverlay) {
		poseStack.pushPose();
		floatUpAndDown(poseStack);
		renderSprite(sprite, stack, poseStack, buffer, packedLight, packedOverlay);
		poseStack.popPose();
	}

	private static void floatUpAndDown(PoseStack poseStack) {
		float phase = (float) (Util.getMillis() % FLOAT_PERIOD) / FLOAT_PERIOD;
		poseStack.translate(0.0F, Mth.sin(phase * Mth.TWO_PI) * FLOAT_HEIGHT, 0.0F);
	}

	/**
	 * Makes everything drawn through it more transparent.
	 */
	private record AlphaVertexConsumer(VertexConsumer parent, float alpha) implements VertexConsumer {
		@Override
		public VertexConsumer addVertex(float x, float y, float z) {
			parent.addVertex(x, y, z);
			return this;
		}

		@Override
		public VertexConsumer setColor(int red, int green, int blue, int alpha) {
			parent.setColor(red, green, blue, Math.round(alpha * this.alpha));
			return this;
		}

		@Override
		public VertexConsumer setUv(float u, float v) {
			parent.setUv(u, v);
			return this;
		}

		@Override
		public VertexConsumer setUv1(int u, int v) {
			parent.setUv1(u, v);
			return this;
		}

		@Override
		public VertexConsumer setUv2(int u, int v) {
			parent.setUv2(u, v);
			return this;
		}

		@Override
		public VertexConsumer setNormal(float normalX, float normalY, float normalZ) {
			parent.setNormal(normalX, normalY, normalZ);
			return this;
		}
	}

	private static void renderSprite(ModelResourceLocation sprite, ItemStack stack, PoseStack poseStack, MultiBufferSource buffer,
			int packedLight, int packedOverlay) {
		Minecraft minecraft = Minecraft.getInstance();
		BakedModel model = minecraft.getModelManager().getModel(sprite);
		ItemRenderer itemRenderer = minecraft.getItemRenderer();
		for (RenderType renderType : model.getRenderTypes(stack, true))
			itemRenderer.renderModelLists(model, stack, packedLight, packedOverlay, poseStack,
					ItemRenderer.getFoilBufferDirect(buffer, renderType, true, stack.hasFoil()));
	}
}
