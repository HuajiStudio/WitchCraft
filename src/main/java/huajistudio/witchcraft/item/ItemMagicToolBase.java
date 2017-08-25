package huajistudio.witchcraft.item;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 *
 */
public abstract class ItemMagicToolBase extends Item {

	public abstract double getMagicCost();

	public ItemMagicToolBase() {
		setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Magic cost: " + getMagicCost());
	}
}
