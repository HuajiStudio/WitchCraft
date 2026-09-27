package huajistudio.witchcraft.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.UseOnContext;

public class ShieldMagicBookItem extends SummonerMagicBookItem {
	@Override
	public Entity summoned(UseOnContext context) {
		return null; // TODO Spawn the shield
	}

	@Override
	public double getMagicCost() {
		return 6.0D;
	}
}
