package huajistudio.witchcraft.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.util.Namer;
import huajistudio.witchcraft.util.loader.GenItem;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class ItemLoader {
	@GenItem(value = {"smelted", "crystal"}, oreDict = {"gemSmeltedCrystal"})
	public static Item SMELTED_CRYSTAL;
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item METAL_CRYSTAL = (new Item()).setUnlocalizedName("metalCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item PLANT_CRYSTAL = (new Item()).setUnlocalizedName("plantCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item WATER_CRYSTAL = (new Item()).setUnlocalizedName("waterCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item FLAME_CRYSTAL = (new Item()).setUnlocalizedName("flameCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item SOIL_CRYSTAL = (new Item()).setUnlocalizedName("soilCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item LIGHT_CRYSTAL = (new Item()).setUnlocalizedName("lightCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item SHADOW_CRYSTAL = (new Item()).setUnlocalizedName("shadowCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item METAL_WAND = (new ItemMetalWand()).setUnlocalizedName("metalWand");
	public static final Item LIGHT_WAND = (new ItemLightWand()).setUnlocalizedName("lightWand");
	public static final Map<ToolMaterial, ItemNormalWand> WAND_MAP = Maps.newHashMap();

	public ItemLoader() {
		boolean hasRedstoneTool = false;
		for (ToolMaterial toolMaterial : ToolMaterial.values())
			if (toolMaterial.name().equals("REDSTONE")) {
				hasRedstoneTool = true;
				break;
			}
		if (!hasRedstoneTool) {
			EnumHelper.addToolMaterial("REDSTONE", 2, 512, 7.0F, 2.5F, 17);
			ToolMaterial.valueOf("REDSTONE").setRepairItem(new ItemStack(Items.REDSTONE));
		}
		Lists.newArrayList(ToolMaterial.values()).forEach(toolMaterial -> WAND_MAP.put(toolMaterial, new ItemNormalWand(toolMaterial)));

		WAND_MAP.forEach((key, value) -> {
			value.setUnlocalizedName(Namer.buildUnlocalizedName(ItemNormalWand.PREFIX, key.name().toLowerCase()));
			value.setRegistryName(Namer.buildToolRegistryName(ItemNormalWand.PREFIX, key.name().toLowerCase()));
		});
	}

	@Load(LoaderState.PREINITIALIZATION)
	public void registerItems() {
		registerItem(CRYSTAL, "crystal", "gemCrystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal", "gemMagicCrystal");
		registerItem(METAL_CRYSTAL, "metal_crystal", "gemMagicCrystal");
		registerItem(PLANT_CRYSTAL, "plant_crystal", "gemMagicCrystal");
		registerItem(WATER_CRYSTAL, "water_crystal", "gemMagicCrystal");
		registerItem(FLAME_CRYSTAL, "flame_crystal", "gemMagicCrystal");
		registerItem(SOIL_CRYSTAL, "soil_crystal", "gemMagicCrystal");
		registerItem(LIGHT_CRYSTAL, "light_crystal", "gemMagicCrystal");
		registerItem(SHADOW_CRYSTAL, "shadow_crystal", "gemMagicCrystal");
		registerItem(METAL_WAND, "metal_wand");
		registerItem(LIGHT_WAND, "light_wand");
		WAND_MAP.values().forEach(ForgeRegistries.ITEMS::register);
		for (Field field : ItemLoader.class.getDeclaredFields()) {
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation.annotationType().equals(GenItem.class)) {
					try {
						field.setAccessible(true);
						Item item = new Item().setUnlocalizedName(Namer.buildUnlocalizedName(((GenItem) annotation).value()));
						field.set(this, item);
					} catch (IllegalAccessException ignored) {}
				}
			}
			tag1:;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(BlockLoader.CRYSTAL_BLOCK);
		registerRender(BlockLoader.MAGIC_CRYSTAL_BLOCK);
		//registerRender(BlockLoader.CRYSTAL_CLUSTER_BLOCK);

		registerRender(CRYSTAL);
		registerRender(MAGIC_CRYSTAL);
		registerRender(METAL_WAND);
		registerRender(LIGHT_WAND);
		WAND_MAP.forEach((key, value) -> registerRender(value));
	}

	private void registerItem(Item item, String registryName) {
		ForgeRegistries.ITEMS.register(item.setRegistryName(registryName));
	}

	private void registerItem(Item item, String registryName, String oreDictName) {
		registerItem(item, registryName);
		OreDictionary.registerOre(oreDictName, item);
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
