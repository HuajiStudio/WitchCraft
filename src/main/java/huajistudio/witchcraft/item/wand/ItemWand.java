package huajistudio.witchcraft.item.wand;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWand extends ItemBow {
	private final int maxUses;
	private final int enchantability;
	private final float efficiencyOnProperMaterial;
	private final float damageVsEntity;

	private final GemMaterial gemMaterial;
	private final ToolMaterial handleMaterial;

	public ItemWand(GemMaterial gemMaterial, ToolMaterial handleMaterial) {
		this.gemMaterial = gemMaterial;
		this.handleMaterial = handleMaterial;

		maxUses = handleMaterial.getMaxUses();
		enchantability = handleMaterial.getEnchantability();
		efficiencyOnProperMaterial = handleMaterial.getEfficiencyOnProperMaterial();
		damageVsEntity = handleMaterial.getDamageVsEntity() + gemMaterial.getDamageVsEntity();

		setUnlocalizedName(handleMaterial.name().toLowerCase() + gemMaterial.toString() + "Wand");
		setCreativeTab(CreativeTabsLoader.tabWand);
	}

	public GemMaterial getGemMaterial() {
		return gemMaterial;
	}

	public ToolMaterial getHandleMaterial() {
		return handleMaterial;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
		worldIn.spawnEntityInWorld(new EntityLightBall(worldIn, playerIn));
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
}
