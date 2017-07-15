package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class ItemMagicBook extends ItemMagicToolBase {
	public enum UsingWay {
		CLICK,
		RELEASE
	}

	protected abstract UsingWay getUsingWay();

	public abstract void onUse();

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (getUsingWay() != UsingWay.RELEASE)
			return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
		ActionResult<ItemStack> result = WCEventFactory.onMagicBookChant(itemStackIn, worldIn, playerIn, hand);
		if (result != null)
			return result;
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (getUsingWay() != UsingWay.CLICK)
			return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		EnumActionResult result = WCEventFactory.onMagicBookChant(stack, worldIn, playerIn, hand).getType();
		if (result == EnumActionResult.SUCCESS)
			onUse();
		return result;
	}
}
