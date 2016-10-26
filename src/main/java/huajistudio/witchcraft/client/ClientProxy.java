package huajistudio.witchcraft.client;

import huajistudio.witchcraft.common.CommonProxy;
import huajistudio.witchcraft.item.ItemRenderLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		new ItemRenderLoader();
	}
}
