package huajistudio.witchcraft;

import huajistudio.witchcraft.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WitchCraft.MODID, name = WitchCraft.NAME, version = WitchCraft.VERSION,
		acceptedMinecraftVersions = WitchCraft.MINECRAFT_VERSION)
public class WitchCraft {
	public static final String MODID   = "witchcraft";
	public static final String NAME    = "WitchCraft";
	public static final String VERSION = "0.1.0-dev";
	public static final String MINECRAFT_VERSION = "1.10.2";

	@Instance(WitchCraft.MODID)
	public static WitchCraft instance;

	@SidedProxy(
			serverSide = "huijistudio.witchcraft.common.CommonProxy",
			clientSide = "huajistudio.witchcraft.client.ClientProxy",
			modId = WitchCraft.MODID
	)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{ proxy.preInit(event); }

	@EventHandler
	public void init(FMLInitializationEvent event)
	{ proxy.init(event); }

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{ proxy.postInit(event); }
}
