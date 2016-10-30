package huajistudio.witchcraft.client;

import huajistudio.witchcraft.common.CommonProxy;
import huajistudio.witchcraft.item.ItemRenderLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	public ItemRenderLoader renderLoader;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		renderLoader = new ItemRenderLoader();
	}
}
