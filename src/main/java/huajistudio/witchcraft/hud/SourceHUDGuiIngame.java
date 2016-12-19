package huajistudio.witchcraft.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.GuiIngameForge;

public class SourceHUDGuiIngame extends GuiIngameForge {
	private final float SOURCE_SCALE = 0.01953125f;

	public SourceHUDGuiIngame(Minecraft mcIn) {
		super(mcIn);
	}

	@Override
	public void renderHealth(int width, int height) {
		super.renderHealth(width, height);
		mc.mcProfiler.startSection("health");
		GlStateManager.enableBlend();

		EntityPlayer player = (EntityPlayer)this.mc.getRenderViewEntity();
		int health = MathHelper.ceiling_float_int(player.getHealth());

		int distanceToLeft = MathHelper.ceiling_float_int(width * SOURCE_SCALE);
		int distanceToDown = MathHelper.ceiling_float_int(height * SOURCE_SCALE);
	}

	@Override
	protected void renderAir(int width, int height) {
		super.renderAir(width, height);
	}

	@Override
	protected void renderArmor(int width, int height) {
		super.renderArmor(width, height);
	}

	@Override
	protected void renderSubtitles(ScaledResolution resolution) {
		super.renderSubtitles(resolution);
	}
}
