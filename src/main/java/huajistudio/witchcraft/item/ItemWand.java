package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import huajistudio.witchcraft.entity.EntityLightBall;
import huajistudio.witchcraft.util.wand.WandHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * @see ItemNormalWand
 */
public abstract class ItemWand extends ItemMagicToolBase {
	/**
	 * Create a new bullet entity to shoot.
	 * @param world The world where the bullet should be shoot in.
	 * @param shooter The entity who shoot the bullet.
	 * @return The bullet which be shoot.
	 */
	public abstract EntityFireball newBullet(World world, EntityLivingBase shooter);

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ActionResult<ItemStack> result = WCEventFactory.onWandNock(itemStackIn, worldIn, playerIn, hand);
		if (result != null)
			return result;
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (worldIn.isRemote || stack == null)
			return;
		int result = 0;
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			result = WCEventFactory.onWandShoot(stack, worldIn, player, getMaxItemUseDuration(stack) - timeLeft);
			if (result < 0)
				return;
		}
		EntityFireball bullet = newBullet(worldIn, entityLiving);
		WandHelper.initBulletHeading(bullet, entityLiving, 0.0F, 0.5F + EntityLightBall.getLightBallVelocity(result) + EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack),
				1.0F);

		stack.damageItem(1, entityLiving);
		worldIn.spawnEntityInWorld(bullet);
	}

	@Override
	@Nonnull
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}
}
