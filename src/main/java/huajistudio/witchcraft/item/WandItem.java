package huajistudio.witchcraft.item;

import huajistudio.witchcraft.event.WCEventHooks;
import huajistudio.witchcraft.magic.MagicStats;
import huajistudio.witchcraft.registry.WCEnchantments;
import net.minecraft.network.chat.Component;
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
 * A wand is charged like a bow, and shoots the bullet made by {@link #newBullet} when released.
 * @see NormalWandItem
 */
public abstract class WandItem extends MagicToolItem {
	public WandItem() {
		this(new Properties().stacksTo(1));
	}

	public WandItem(Properties properties) {
		super(properties);
	}

	/**
	 * Create a new bullet entity to shoot.
	 * @param level The level where the bullet should be shoot in.
	 * @param shooter The entity who shoot the bullet.
	 * @param stack The wand.
	 * @return The bullet which be shoot.
	 */
	public abstract AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter, ItemStack stack);

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		InteractionResultHolder<ItemStack> result = WCEventHooks.onWandNock(stack, level, player, hand);
		if (result != null)
			return result;
		player.startUsingItem(hand);
		return InteractionResultHolder.consume(stack);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (level.isClientSide)
			return;
		int charge = getUseDuration(stack, entity) - timeLeft;
		if (entity instanceof Player player) {
			charge = WCEventHooks.onWandShoot(stack, level, player, charge);
			if (charge < 0)
				return;
			if (!MagicStats.consume(player, getMagicCost())) {
				player.displayClientMessage(Component.translatable("text.wand.recharge_needed"), true);
				return;
			}
		}
		AbstractHurtingProjectile bullet = newBullet(level, entity, stack);
		bullet.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F,
				0.5F + getChargeProgress(charge) + WCEnchantments.getLevel(level, Enchantments.POWER, stack),
				0.0F);

		stack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));
		level.addFreshEntity(bullet);
	}

	/**
	 * @param charge how many ticks the wand has been charged
	 * @return how far the charging has gone, from 0 to 1 after a second, like a bow
	 */
	public static float getChargeProgress(float charge) {
		float f = charge / 20.0F;
		f = f * (f + 2.0F) / 3.0F;
		return Math.min(f, 1.0F);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		// Not BOW, which raises both arms; the pose is set by WandClientExtensions instead.
		return UseAnim.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}
}
