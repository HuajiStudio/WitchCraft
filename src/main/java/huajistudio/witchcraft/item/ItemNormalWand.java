package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemNormalWand extends ItemWand {
	@SuppressWarnings("all")
	public static final String PREFIX = "wand";

	@SuppressWarnings("all")
	private final float ATTACK_DAMAGE;

	@SuppressWarnings("all")
	private final ToolMaterial MATERIAL;

	@SuppressWarnings("all")
	public ItemNormalWand(ToolMaterial material) {
		MATERIAL = material;
		setMaxStackSize(1);
		setMaxDamage(MathHelper.ceil(material.getMaxUses() * 4.6125f));
		ATTACK_DAMAGE = material.getDamageVsEntity();
		//GL11.glBegin(GL11.GL_TRIANGLES);
		//GL11.glVertex3i(0,0,0);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Block block = state.getBlock();
		if (block == Blocks.WEB)
			return 15.0F;
		else {
			Material material = state.getMaterial();
			return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (!(entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entityLiving;
		int result = WCEventFactory.onWandShoot(stack, worldIn, player, getMaxItemUseDuration(stack) - timeLeft);
		if (result < 0 || stack == null || worldIn.isRemote)
			return;
		EntityLightBall lightBall = new EntityLightBall(worldIn, player);
		lightBall.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F,
				0.5F + EntityLightBall.getLightBallVelocity(result) + EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack),
				1.0F);

		// knockback
		int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
		if (k > 0)
			lightBall.setKnockbackStrength(k);
		k = EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.EXPLOSION, stack);
		if (k > 0)
			lightBall.setExplosionStrength(k);
		k = EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.STABLE_LIGHTBALL, stack);
		lightBall.setLife((k + 1) * 100);
		// TODO add enchantment effects

		stack.damageItem(1, player);
		worldIn.spawnEntity(lightBall);
		if (stack.getTagCompound() != null) {
			NBTTagCompound compound = stack.getTagCompound();
			int currentMagicAmount = stack.getTagCompound().getInteger("magicAmount") - (getMaxItemUseDuration(stack) - timeLeft);
			if (currentMagicAmount > 0)
				compound.setInteger("magicAmount", currentMagicAmount);
			else {
				compound.setInteger("magicAmount", 0);
				player.sendMessage(new TextComponentTranslation("text.wand.recharge_needed"));
			}
			stack.setTagCompound(compound);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return MathHelper.ceil(ToolMaterial.DIAMOND.getMaxUses() * 15.061f);
	}

	public ToolMaterial getMaterial() {
		return MATERIAL;
	}

	@Override
	public double getMagicCost() {
		return 1.0D;
	}

	@Override
	public EntityFireball newBullet(World world, EntityLivingBase shooter) {
		return new EntityLightBall(world, shooter);
	}
}
