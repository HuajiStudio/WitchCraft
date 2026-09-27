package huajistudio.witchcraft.client;

import huajistudio.witchcraft.magic.MagicStats;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Shows the magic points of the player in the bottom left corner of the screen.
 */
public class MagicHudLayer implements LayeredDraw.Layer {
	@Override
	public void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.options.hideGui || !(minecraft.getCameraEntity() instanceof Player player) || player.isSpectator())
			return;
		MagicStats stats = MagicStats.get(player);
		Component text = Component.translatable("hud.witchcraft.magic",
				String.format("%.1f", stats.amount()), String.format("%.1f", stats.capacity()));
		int y = graphics.guiHeight() - minecraft.font.lineHeight - 4;
		graphics.drawString(minecraft.font, text, 4, y, 0xFFFFFF, true);
	}
}
