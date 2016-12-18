package huajistudio.witchcraft.client;

import huajistudio.witchcraft.common.CommonProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		try {
			getItemLoader().registerRenders();
			getEntityLoader().registerRenders();
		} catch (Exception e) {
			LogManager.getLogger("Minecraft").warn("Cannot register renderer", e);
		}
	}
}
