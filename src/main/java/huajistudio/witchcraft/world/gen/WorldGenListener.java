package huajistudio.witchcraft.world.gen;

import huajistudio.witchcraft.block.BlockLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGenListener {
	private static final WorldGenOre WORLD_GEN_CRYSTAL_ORE = new WorldGenOre(new int[]{0}, 20, 64, 0, 8, BlockLoader.CRYSTAL_ORE.getDefaultState());

	public WorldGenListener() {
		MinecraftForge.ORE_GEN_BUS.register(this);
	}

	@SubscribeEvent
	public void onOreGenPost(OreGenEvent.Post event) {
		WORLD_GEN_CRYSTAL_ORE.generate(event.getWorld(), event.getRand(), event.getPos());
	}
}
