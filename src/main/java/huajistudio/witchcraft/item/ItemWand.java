package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

/**
 * @see ItemNormalWand
 */
public abstract class ItemWand extends ItemMagicToolBase {
	public ItemWand() {
		this(new Properties().stacksTo(1));
	}

	public ItemWand(Properties properties) {
		super(properties);
	}

	/**
	 * Create a new bullet entity to shoot.
	 * @param level The level where the bullet should be shoot in.
	 * @param shooter The entity who shoot the bullet.
	 * @return The bullet which be shoot.
	 */
	public abstract AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter);

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		InteractionResultHolder<ItemStack> result = WCEventFactory.onWandNock(stack, level, player, hand);
		if (result != null)
			return result;
		player.startUsingItem(hand);
		return InteractionResultHolder.consume(stack);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (level.isClientSide)
			return;
		int result = 0;
		if (entity instanceof Player player) {
			result = WCEventFactory.onWandShoot(stack, level, player, getUseDuration(stack, entity) - timeLeft);
			if (result < 0)
				return;
		}
		AbstractHurtingProjectile bullet = newBullet(level, entity);
		bullet.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F,
				0.5F + EntityLightBall.getLightBallVelocity(result) + EnchantmentLoader.getLevel(level, Enchantments.POWER, stack),
				0.0F);

		stack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));
		level.addFreshEntity(bullet);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}
}
