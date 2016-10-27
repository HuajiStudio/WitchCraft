package huajistudio.witchcraft.item;

import huajistudio.witchcraft.block.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabs.MATERIALS);

	public static void registerItems() {
		registerItemBlock(BlockLoader.CRYSTAL_ORE, "crystal_ore", 1);
		registerItem(CRYSTAL, "crystal", 201);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(CRYSTAL);
	}

	private static void registerItem(Item item, String name, int id) {
		item.setRegistryName(name);
		Item.REGISTRY.register(id, item.getRegistryName(), item);
	}

	private static void registerItemBlock(Block block, String name, int id) {
		block.setRegistryName(name);
		Block.REGISTRY.register(id, block.getRegistryName(), block);
		registerItem(new ItemBlock(block), name, id);
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block) {
		ModelLoader.setCustomModelResourceLocation(new ItemBlock(block), 0, new ModelResourceLocation(block.getRegistryName().toString(), "inventory"));
	}
}
