package huajistudio.witchcraft.client;

import huajistudio.witchcraft.common.CommonProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;

public class ClientProxy extends CommonProxy {
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		try {
			getItemLoader().registerRenders();
		} catch (Exception e) {
			LogManager.getLogger("Minecraft").warn("Cannot register renderer", e);
		}
	}
}
