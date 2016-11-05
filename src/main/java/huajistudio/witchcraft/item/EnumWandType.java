package huajistudio.witchcraft.item;

import net.minecraft.item.Item;

public enum EnumWandType {
	WOOD(Item.ToolMaterial.WOOD),
	STONE(Item.ToolMaterial.STONE),
	IRON(Item.ToolMaterial.IRON),
	DIAMOND(Item.ToolMaterial.DIAMOND),
	GOLD(Item.ToolMaterial.GOLD);

	private Item.ToolMaterial toolMaterial;

	EnumWandType(Item.ToolMaterial toolMaterial) {
		this.toolMaterial = toolMaterial;
	}

	public Item.ToolMaterial getToolMaterial() {
		return toolMaterial;
	}
}
