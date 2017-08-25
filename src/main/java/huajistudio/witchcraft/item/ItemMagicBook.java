package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemMagicBook extends ItemMagicToolBase {
	public void onUse(ItemStack stack, World world, EntityLivingBase entity, int charge) {}

	public void onUse(EntityLivingBase entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (worldIn.isRemote || stack == null)
			return;
		boolean result = true;
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (WCEventFactory.onMagicBookChant(stack, worldIn, player).getType() != EnumActionResult.SUCCESS)
				result = false;
		}
		if (result)
			onUse(stack, worldIn, entityLiving, getMaxItemUseDuration(stack) - timeLeft);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		EnumActionResult result = WCEventFactory.onMagicBookChant(stack, worldIn, player).getType();
		if (result == EnumActionResult.SUCCESS)
			onUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		return result;
	}
}
