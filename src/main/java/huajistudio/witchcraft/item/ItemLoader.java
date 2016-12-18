package huajistudio.witchcraft.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.util.Namer;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

public class ItemLoader {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Map<ToolMaterial, ItemWand> WAND_MAP = Maps.newHashMap();

	public ItemLoader() {
		EnumHelper.addToolMaterial("REDSTONE", 2, 512, 7.0F, 2.5F, 17);
		WAND_MAP.put(ToolMaterial.valueOf("REDSTONE"), new ItemWand(ToolMaterial.valueOf("REDSTONE")));
		Lists.newArrayList(ToolMaterial.values()).forEach(toolMaterial -> WAND_MAP.put(toolMaterial, new ItemWand(toolMaterial)));

		WAND_MAP.entrySet().forEach(entry -> {
			entry.getValue().setUnlocalizedName(Namer.buildUnlocalizedName(ItemWand.PREFIX, entry.getKey().name().toLowerCase()));
			entry.getValue().setRegistryName(Namer.buildRegistryName(ItemWand.PREFIX, entry.getKey().name().toLowerCase()));
		});
	}

	@Load(LoaderState.PREINITIALIZATION)
	public void registerItems() {
		registerItem(CRYSTAL, "crystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal");
		WAND_MAP.entrySet().forEach(entry -> GameRegistry.register(entry.getValue()));
	}

	@SideOnly(Side.CLIENT)
	public void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(BlockLoader.CRYSTAL_BLOCK);
		registerRender(BlockLoader.MAGIC_CRYSTAL_BLOCK);

		registerRender(CRYSTAL);
		registerRender(MAGIC_CRYSTAL);
		WAND_MAP.entrySet().forEach(entry -> registerRender(entry.getValue()));
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
