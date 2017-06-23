package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class ItemMagicBook extends ItemMagicToolBase {
	public abstract void onUse();

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ActionResult<ItemStack> result = WCEventFactory.onMagicBookChant(itemStackIn, worldIn, playerIn, hand);
		if (result != null)
			return result;
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
