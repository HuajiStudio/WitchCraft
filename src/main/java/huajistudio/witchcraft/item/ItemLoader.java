package huajistudio.witchcraft.item;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.wand.GemMaterial;
import huajistudio.witchcraft.item.wand.ItemWand;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class ItemLoader {
	public static ArrayList<ItemWand> wands = new ArrayList<ItemWand>();

	public ItemLoader(FMLPreInitializationEvent event) {
		for (Item.ToolMaterial toolMaterial:
				new Item.ToolMaterial[]{
						Item.ToolMaterial.WOOD,
						Item.ToolMaterial.STONE,
						Item.ToolMaterial.IRON,
						Item.ToolMaterial.GOLD,
						Item.ToolMaterial.DIAMOND }) {
			for (GemMaterial gemMaterial:
					new GemMaterial[]{
							GemMaterial.DIAMOND,
							GemMaterial.EMERALD,
							GemMaterial.LAPIS }) {
				ItemWand wand = new ItemWand(gemMaterial, toolMaterial);
				wands.add(wand);
				register(wand, wand.getHandleMaterial().name().toLowerCase() + "_" +
						wand.getGemMaterial().name().toLowerCase() + "_wand");
			}
		}
	}

	private static void register(Item item, String name) {
		item.setRegistryName(WitchCraft.MODID, name);
	}
}
