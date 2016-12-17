package huajistudio.witchcraft.item;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item IRON_WAND = (new ItemWand(Item.ToolMaterial.IRON)).setUnlocalizedName("wandIron");
	public static final Item WOODEN_WAND = (new ItemWand(Item.ToolMaterial.WOOD)).setUnlocalizedName("wandWood");
	public static final Item STONE_WAND = (new ItemWand(Item.ToolMaterial.STONE)).setUnlocalizedName("wandStone");
	public static final Item DIAMOND_WAND = (new ItemWand(Item.ToolMaterial.DIAMOND)).setUnlocalizedName("wandDiamond");
	public static final Item GOLDEN_WAND = (new ItemWand(Item.ToolMaterial.GOLD)).setUnlocalizedName("wandGold");

	@Load(LoaderState.PREINITIALIZATION)
	public void registerItems() {
		registerItem(CRYSTAL, "crystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal");
		registerItem(IRON_WAND, "iron_wand");
		registerItem(WOODEN_WAND, "wooden_wand");
		registerItem(STONE_WAND, "stone_wand");
		registerItem(DIAMOND_WAND, "diamond_wand");
		registerItem(GOLDEN_WAND, "golden_wand");
	}

	@SideOnly(Side.CLIENT)
	public void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(BlockLoader.CRYSTAL_BLOCK);
		registerRender(BlockLoader.MAGIC_CRYSTAL_BLOCK);

		registerRender(CRYSTAL);
		registerRender(MAGIC_CRYSTAL);
		registerRender(IRON_WAND);
		registerRender(WOODEN_WAND);
		registerRender(STONE_WAND);
		registerRender(DIAMOND_WAND);
		registerRender(GOLDEN_WAND);
	}

	private void registerItem(Item item, String name) {
		GameRegistry.register(item.setRegistryName(name));
	}

	@SideOnly(Side.CLIENT)
	private void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		if (item != null)
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
