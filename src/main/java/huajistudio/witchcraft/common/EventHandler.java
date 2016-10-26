package huajistudio.witchcraft.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class EventHandler {
	public static final EventBus EVENT_BUS = new EventBus();

	public EventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		EventHandler.EVENT_BUS.register(this);
	}
}
