package huajistudio.witchcraft.client;

import huajistudio.witchcraft.attachment.AttachmentLoader;
import huajistudio.witchcraft.attachment.MagicStats;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public class ClientRenderFactory {
	public static void renderMagicStats(GuiGraphics graphics, DeltaTracker deltaTracker) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.options.hideGui || !(minecraft.getCameraEntity() instanceof Player player))
			return;
		MagicStats stats = player.getData(AttachmentLoader.MAGIC_STATS);
		String text = String.format("Current Magic: %.1f / %.1f", stats.getAmount(), stats.getCapacity());
		int x = 100;
		int y = 200;
		int color = 0xFFFFFF;
		graphics.drawString(minecraft.font, text, x, y, color, true);
	}
}
