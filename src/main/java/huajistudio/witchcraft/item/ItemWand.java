package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWand extends Item {
	private final int maxUses;
	private final int enchantability;
	private final float efficiencyOnProperMaterial;

	private final ToolMaterial handleMaterial;

	public ItemWand(ToolMaterial handleMaterial) {
		this.handleMaterial = handleMaterial;

		maxUses = handleMaterial.getMaxUses();
		enchantability = handleMaterial.getEnchantability();
		efficiencyOnProperMaterial = handleMaterial.getEfficiencyOnProperMaterial();

		setUnlocalizedName(handleMaterial.name().toLowerCase() + "Wand");
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
