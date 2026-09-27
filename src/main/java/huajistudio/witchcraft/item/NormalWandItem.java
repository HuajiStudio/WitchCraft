package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.LightBallEntity;
import huajistudio.witchcraft.registry.WCEnchantments;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A wand made of a tool material, shooting enchantable light balls.
 */
public class NormalWandItem extends WandItem {
	private final Tier material;

	public NormalWandItem(Tier material) {
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
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter, ItemStack stack) {
		LightBallEntity lightBall = new LightBallEntity(level, shooter, LightBallEntity.Effect.DAMAGE);
		lightBall.setKnockbackStrength(WCEnchantments.getLevel(level, Enchantments.PUNCH, stack));
		lightBall.setExplosionStrength(WCEnchantments.getLevel(level, WCEnchantments.EXPLOSION, stack));
		lightBall.setLife((WCEnchantments.getLevel(level, WCEnchantments.STABLE_LIGHTBALL, stack) + 1) * 100);
		return lightBall;
	}
}
