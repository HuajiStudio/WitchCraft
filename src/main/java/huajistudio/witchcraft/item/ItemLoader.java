package huajistudio.witchcraft.item;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

public class ItemLoader {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);

	public void registerItems() {
		registerItem(CRYSTAL, "crystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal");
	}

	@Load(LoaderState.PREINITIALIZATION)
	public void registerItemBlocks() {
		Class<BlockLoader> blockLoaderClass = BlockLoader.class;
		for (Field field : blockLoaderClass.getFields()) {
			try {
				Block block = (Block) field.get(WitchCraft.proxy.getBlockLoader());
				GameRegistry.register(new ItemBlock(block));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Load(LoaderState.POSTINITIALIZATION)
	public static void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(BlockLoader.CRYSTAL_BLOCK);
		registerRender(BlockLoader.MAGIC_CRYSTAL_BLOCK);

		registerRender(CRYSTAL);
		registerRender(MAGIC_CRYSTAL);
	}

	private void registerItem(Item item, String name) {
		GameRegistry.register(item.setRegistryName(name));
	}

	private void registerItemBlock(Block block, String name) {
		registerItem(new ItemBlock(block), name);
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block) throws NullPointerException {
		Item item = Item.getItemFromBlock(block);
		if (item != null){
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		} else {
			throw new NullPointerException(block.getUnlocalizedName() + " not found");
		}
	}
}
