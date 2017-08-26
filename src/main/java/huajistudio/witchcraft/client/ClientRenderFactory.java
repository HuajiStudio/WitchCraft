package huajistudio.witchcraft.client;

import huajistudio.witchcraft.capability.CapabilityMagic;
import huajistudio.witchcraft.capability.MagicStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ClientRenderFactory {
	private Random rand = new Random();

	public ClientRenderFactory() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onRenderIngame(RenderGameOverlayEvent.Post event) {
		Minecraft minecraft = Minecraft.getMinecraft();
		EntityPlayer player = (EntityPlayer) minecraft.getRenderViewEntity();
		MagicStats stats = player.getCapability(CapabilityMagic.CAPABILITY_MAGIC_STATS, null);
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && stats != null) {
			rand.setSeed((long)(event.getPartialTicks() * 312871));
			ScaledResolution resolution = event.getResolution();
			FontRenderer fontRenderer = minecraft.fontRenderer;
			minecraft.entityRenderer.setupOverlayRendering();
			String text = "Current Magic: " + stats.getAmount() + " / " + stats.getCapacity();
			int x = 100;
			int y = 200;
			int color = 0xFFFFFF;
			fontRenderer.drawStringWithShadow(text, x, y, color);
		}
	}
}
