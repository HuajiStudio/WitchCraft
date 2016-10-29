package huajistudio.witchcraft.item;

import huajistudio.witchcraft.block.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabs.MATERIALS);

	public static void registerItems() {
		registerItemBlock(BlockLoader.CRYSTAL_ORE, "crystal_ore");
		registerItemBlock(BlockLoader.CRYSTAL_BLOCK, "crystal_block");
		registerItemBlock(BlockLoader.MAGIC_CRYSTAL_BLOCK, "magic_crystal_block");

		registerItem(CRYSTAL, "crystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal");
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(BlockLoader.CRYSTAL_BLOCK);
		registerRender(BlockLoader.MAGIC_CRYSTAL_BLOCK);

		registerRender(CRYSTAL);
		registerRender(MAGIC_CRYSTAL);
	}

	private static void registerItem(Item item, String name) {
		GameRegistry.register(item.setRegistryName(name));
	}

	private static void registerItemBlock(Block block, String name) {
		GameRegistry.register(block.setRegistryName(name));
		registerItem(new ItemBlock(block), name);
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		if (item != null){
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName().toString(), "inventory"));
		}
	}
}
