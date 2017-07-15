package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityMagicShield;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ItemMagicBookShield extends ItemMagicBookSummoner {
	@Override
	protected UsingWay getUsingWay() {
		return UsingWay.CLICK;
	}

	@Override
	Entity summoned(World world, EntityLivingBase chanter) {
		return new EntityMagicShield(world, chanter.posX, chanter.posY, chanter.posZ);
	}

	@Override
	public double getMagicCost() {
		return 6.0D;
	}
}
