package huajistudio.witchcraft.client;

import com.mojang.blaze3d.systems.RenderSystem;
import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.MagicToolItem;
import huajistudio.witchcraft.magic.MagicStats;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

/**
 * Shows the magic points of the player as a row of crystals above the hunger bar, like the health and hunger bars:
 * every crystal is 2 points, filled from the right. It is hidden in creative and spectator mode, where magic is infinite.
 * <p>
 * Like the vanilla bars, the crystals blink when magic is consumed, bounce one after another while it regenerates,
 * and shake while the held magic tool costs more than what is left. Otherwise the magic the held tool would cost
 * slowly pulses white.
 */
public class MagicHudLayer implements LayeredDraw.Layer {
	private static final ResourceLocation CONTAINER_SPRITE = WitchCraft.id("hud/magic_container");
	private static final ResourceLocation CONTAINER_BLINKING_SPRITE = WitchCraft.id("hud/magic_container_blinking");
	private static final ResourceLocation FULL_SPRITE = WitchCraft.id("hud/magic_full");
	private static final ResourceLocation HALF_SPRITE = WitchCraft.id("hud/magic_half");
	private static final ResourceLocation HIGHLIGHT_SPRITE = WitchCraft.id("hud/magic_highlight");
	private static final int ICONS_PER_ROW = 10;
	/** The first point of a crystal is its right half, starting from this column; the second point is the rest. */
	private static final int HALF_X = 4;
	private static final int BLINK_TICKS = 20;
	private static final float PREVIEW_PERIOD = 40;
	private static final float PREVIEW_ALPHA = 0.6F;

	private final RandomSource random = RandomSource.create();
	private double lastAmount;
	/** The amount right before the last consumption, whose lost part blinks until {@link #blinkUntil}. */
	private double consumedFrom;
	private int blinkUntil;

	@Override
	public void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.options.hideGui || minecraft.gameMode == null || !minecraft.gameMode.canHurtPlayer()
				|| !(minecraft.getCameraEntity() instanceof Player player))
			return;
		MagicStats stats = MagicStats.get(player);
		int icons = Mth.ceil(stats.capacity() / 2);
		if (icons <= 0)
			return;
		Gui gui = minecraft.gui;
		int ticks = gui.getGuiTicks();
		double amount = stats.amount();
		if (amount < lastAmount) {
			if (blinkUntil <= ticks)
				consumedFrom = lastAmount;
			blinkUntil = ticks + BLINK_TICKS;
		}
		lastAmount = amount;

		int points = Mth.floor(amount);
		// Points in [points, consumed) were just consumed, and those in [castFrom, points) would be by the held tool.
		int consumed = blinkUntil > ticks ? Mth.floor(consumedFrom) : points;
		boolean blinking = blinkUntil > ticks && (blinkUntil - ticks) / 3 % 2 == 1;
		double cost = heldMagicCost(player);
		boolean shaking = cost > amount;
		int castFrom = shaking ? points : Mth.floor(amount - cost);
		float time = ticks + deltaTracker.getGameTimeDeltaPartialTick(false);
		float previewAlpha = PREVIEW_ALPHA * (0.5F - 0.5F * Mth.cos(time * Mth.TWO_PI / PREVIEW_PERIOD));
		int bouncing = stats.isFull() ? -1 : ticks % Mth.ceil(stats.capacity() + 5);
		random.setSeed(ticks * 312871L);

		int right = graphics.guiWidth() / 2 + 91;
		int bottom = graphics.guiHeight() - gui.rightHeight;
		RenderSystem.enableBlend();
		for (int i = 0; i < icons; i++) {
			int x = right - i % ICONS_PER_ROW * 8 - 9;
			int y = bottom - i / ICONS_PER_ROW * 10;
			if (shaking)
				y += random.nextInt(3) - 1;
			if (i == bouncing)
				y -= 2;
			graphics.blitSprite(blinking ? CONTAINER_BLINKING_SPRITE : CONTAINER_SPRITE, x, y, 9, 9);
			if (blinking)
				blitPoints(graphics, HIGHLIGHT_SPRITE, i, x, y, points, consumed);
			if (i * 2 + 1 < points)
				graphics.blitSprite(FULL_SPRITE, x, y, 9, 9);
			else if (i * 2 + 1 == points)
				graphics.blitSprite(HALF_SPRITE, x, y, 9, 9);
			if (castFrom < points) {
				graphics.setColor(1, 1, 1, previewAlpha);
				blitPoints(graphics, HIGHLIGHT_SPRITE, i, x, y, castFrom, points);
				graphics.setColor(1, 1, 1, 1);
			}
		}
		RenderSystem.disableBlend();
		gui.rightHeight += Mth.positiveCeilDiv(icons, ICONS_PER_ROW) * 10;
	}

	/**
	 * Draws the part of {@code sprite} over crystal {@code i} that covers the points in [{@code from}, {@code to}).
	 */
	private static void blitPoints(GuiGraphics graphics, ResourceLocation sprite, int i, int x, int y, int from, int to) {
		boolean first = from <= i * 2 && i * 2 < to;
		boolean second = from <= i * 2 + 1 && i * 2 + 1 < to;
		int left = second ? 0 : HALF_X;
		int right = first ? 9 : HALF_X;
		if (left < right)
			graphics.blitSprite(sprite, 9, 9, left, 0, x + left, y, right - left, 9);
	}

	/**
	 * @return the magic cost of the magic tool in the player's hands, preferring the main hand, or 0 if there is none
	 */
	private static double heldMagicCost(Player player) {
		for (InteractionHand hand : InteractionHand.values())
			if (player.getItemInHand(hand).getItem() instanceof MagicToolItem tool)
				return tool.getMagicCost();
		return 0;
	}
}
