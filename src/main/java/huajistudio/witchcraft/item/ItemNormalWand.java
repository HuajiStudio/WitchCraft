package huajistudio.witchcraft.item;

import huajistudio.witchcraft.attachment.AttachmentLoader;
import huajistudio.witchcraft.attachment.MagicStats;
import huajistudio.witchcraft.common.WCEventFactory;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.entity.EntityLightBall;
import huajistudio.witchcraft.network.MagicStatsPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ItemNormalWand extends ItemWand {
	public static final String PREFIX = "wand";

	private final Tier material;

	public ItemNormalWand(Tier material) {
		super(new Properties().stacksTo(1).durability(Mth.ceil(material.getUses() * 4.6125f)));
		this.material = material;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.is(Blocks.COBWEB))
			return 15.0F;
		return state.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (!(entity instanceof Player player) || level.isClientSide)
			return;

		MagicStats stats = player.getData(AttachmentLoader.MAGIC_STATS);
		if (stats.getAmount() >= getMagicCost()) stats.setAmount(stats.getAmount() - getMagicCost());
		else if (!player.isCreative()) return;
		if (player instanceof ServerPlayer serverPlayer)
			MagicStatsPayload.sync(serverPlayer);

		int result = WCEventFactory.onWandShoot(stack, level, player, getUseDuration(stack, entity) - timeLeft);
		if (result < 0)
			return;
		EntityLightBall lightBall = new EntityLightBall(level, player);
		lightBall.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F,
				0.5F + EntityLightBall.getLightBallVelocity(result) + EnchantmentLoader.getLevel(level, Enchantments.POWER, stack),
				0.0F);

		// knockback
		int k = EnchantmentLoader.getLevel(level, Enchantments.PUNCH, stack);
		if (k > 0)
			lightBall.setKnockbackStrength(k);
		k = EnchantmentLoader.getLevel(level, EnchantmentLoader.EXPLOSION, stack);
		if (k > 0)
			lightBall.setExplosionStrength(k);
		k = EnchantmentLoader.getLevel(level, EnchantmentLoader.STABLE_LIGHTBALL, stack);
		lightBall.setLife((k + 1) * 100);
		// TODO add enchantment effects

		stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
		level.addFreshEntity(lightBall);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return Mth.ceil(Tiers.DIAMOND.getUses() * 15.061f);
	}

	public Tier getMaterial() {
		return material;
	}

	@Override
	public double getMagicCost() {
		return 1.0D;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter) {
		return new EntityLightBall(level, shooter);
	}
}
