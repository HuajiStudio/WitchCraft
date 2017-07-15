package huajistudio.witchcraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public abstract class ItemMagicBookSummoner extends ItemMagicBook {
	abstract Entity summoned(World world, EntityLivingBase chanter);

	@Override
	public void onUse() {

	}
}
