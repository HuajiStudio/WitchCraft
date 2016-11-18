package huajistudio.witchcraft.item;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.util.Namer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

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
}
