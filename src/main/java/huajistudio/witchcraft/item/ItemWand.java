package huajistudio.witchcraft.item;

import huajistudio.witchcraft.common.WCEventFactory;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWand extends Item {
	private final float ATTACK_DAMAGE;
	private final ToolMaterial MATERIAL;
	private final String PREFIX = "wand";

	public ItemWand(ToolMaterial material) {
		this.MATERIAL = material;
		setMaxStackSize(1);
		setMaxDamage(material.getMaxUses());
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
		ATTACK_DAMAGE = 3.0F;
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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ActionResult<ItemStack> result = WCEventFactory.onLightBallNock(itemStackIn, worldIn, playerIn, hand);
		if (result != null)
			return result;
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (!(entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entityLiving;
		int result = WCEventFactory.onLightBallShoot(stack, worldIn, player, getMaxItemUseDuration(stack) - timeLeft);
		if (result < 0 || stack == null || worldIn.isRemote)
			return;
		EntityLightBall lightBall = new EntityLightBall(worldIn, player);
		lightBall.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F * EntityLightBall.getLightBallVelocity(result), 1.0F);
		// TODO add enchantment effects
		worldIn.spawnEntityInWorld(lightBall);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

/*
@Override
@Nonnull
public String getHighlightTip(ItemStack item, @Nonnull String displayName) {
return super.getHighlightTip(item, displayName);
}
@Override
@Nonnull
public String getItemStackDisplayName(@Nonnull ItemStack stack) {
try {
String wandType = stack.getTagCompound().getString("wandType");
return ("" + I18n.translateToLocal(Namer.buildUnlocalizedName("item." + PREFIX, wandType + ".name"))).trim();
} catch (NullPointerException e) {
e.printStackTrace();
}
return super.getItemStackDisplayName(stack);
}
*/

}
