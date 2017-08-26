package huajistudio.witchcraft.common;

import huajistudio.witchcraft.capability.CapabilityMagic;
import huajistudio.witchcraft.capability.MagicStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class MagicTickable implements ITickable {
	@Override
	public void update() {
		for (WorldServer world : FMLCommonHandler.instance().getMinecraftServerInstance().worlds)
			world.getPlayers(EntityPlayer.class, (ignored) -> true).forEach(player -> {
				MagicStats stats = player.getCapability(CapabilityMagic.CAPABILITY_MAGIC_STATS, null);
				if (stats != null) stats.setAmount(stats.getAmount() + 0.05);
			});
	}
}
