package huajistudio.witchcraft.item.wand;

import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import net.minecraft.item.Item;

public class ItemWand extends Item {
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

		setUnlocalizedName(handleMaterial.name().toLowerCase() + "-" + gemMaterial.name().toLowerCase() + "Wand");
		setCreativeTab(CreativeTabsLoader.tabWand);
	}

	public GemMaterial getGemMaterial() {
		return gemMaterial;
	}

	public ToolMaterial getHandleMaterial() {
		return handleMaterial;
	}
}
