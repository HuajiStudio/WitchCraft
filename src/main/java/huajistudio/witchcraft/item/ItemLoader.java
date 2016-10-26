package huajistudio.witchcraft.item;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	public static Item crystal = new ItemCrystal();

	public static void registerItems() {
		registerItem(crystal, "crystal");
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		registerRender(crystal);
	}

	private static void registerItem(Item item, String name) {
		item.setRegistryName(WitchCraft.MODID, name);
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item) {
		String name = item.getRegistryName().toString();
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name));
	}
}
