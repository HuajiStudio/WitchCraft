package huajistudio.witchcraft.hud;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

/**
 * Replace the original HUD system provided by Minecraft using event system.
 */
public class RenderingEventFactory {
	public static final EventBus EVENT_BUS = new EventBus();

	public RenderingEventFactory() {
		MinecraftForge.EVENT_BUS.register(this);
		EVENT_BUS.register(this);
	}

}
