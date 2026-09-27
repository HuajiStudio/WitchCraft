package huajistudio.witchcraft.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.UseOnContext;

import javax.annotation.Nullable;

public abstract class ItemMagicBookSummoner extends ItemMagicBook {
	@Nullable
	public abstract Entity summoned(UseOnContext context);

	@Override
	public void onUse(UseOnContext context) {
		Entity entity = summoned(context);
		if (entity != null)
			context.getLevel().addFreshEntity(entity);
	}
}
