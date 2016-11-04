package huajistudio.witchcraft.item;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader implements ILoader<Item> {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item WOODEN_WAND = (new ItemWand(Item.ToolMaterial.WOOD)).setUnlocalizedName("wandWood");
	public static final Item STONE_WAND = (new ItemWand(Item.ToolMaterial.STONE)).setUnlocalizedName("wandStone");
	public static final Item IRON_WAND = (new ItemWand(Item.ToolMaterial.IRON)).setUnlocalizedName("wandIron");
	public static final Item DIAMOND_WAND = (new ItemWand(Item.ToolMaterial.DIAMOND)).setUnlocalizedName("wandDiamond");
	public static final Item GOLDEN_WAND = (new ItemWand(Item.ToolMaterial.GOLD)).setUnlocalizedName("wandGold");

	@Override
	public void register() {
		registerItemBlock(BlockLoader.CRYSTAL_ORE, "crystal_ore");
		registerItemBlock(BlockLoader.CRYSTAL_BLOCK, "crystal_block");
		registerItemBlock(BlockLoader.MAGIC_CRYSTAL_BLOCK, "magic_crystal_block");

		registerItem(CRYSTAL, "crystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal");
		registerItem(WOODEN_WAND, "wooden_wand");
		registerItem(STONE_WAND, "stone_wand");
		registerItem(IRON_WAND, "iron_wand");
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
		registerRender(WOODEN_WAND);
		registerRender(STONE_WAND);
		registerRender(IRON_WAND);
		registerRender(DIAMOND_WAND);
		registerRender(GOLDEN_WAND);
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
