package huajistudio.witchcraft.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.creativetab.CreativeTabsLoader;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.util.Namer;
import huajistudio.witchcraft.util.loader.GenItem;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class ItemLoader {
	@GenItem(value = {"smelted", "crystal"}, oreDict = {"gemSmeltedCrystal"})
	public static Item SMELTED_CRYSTAL;
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Item MAGIC_CRYSTAL = (new Item()).setUnlocalizedName("magicCrystal").setCreativeTab(CreativeTabsLoader.WITCHCRAFT);
	public static final Map<ToolMaterial, ItemWand> WAND_MAP = Maps.newHashMap();
	public static final Collection<ItemEnchantedBook> ENCHANTED_BOOKS = Lists.newArrayList();

	public ItemLoader() {
		if (!Lists.newArrayList(ToolMaterial.values()).contains(ToolMaterial.valueOf("REDSTONE")))
			EnumHelper.addToolMaterial("REDSTONE", 2, 512, 7.0F, 2.5F, 17);
		Lists.newArrayList(ToolMaterial.values()).forEach(toolMaterial -> WAND_MAP.put(toolMaterial, new ItemWand(toolMaterial)));

		WAND_MAP.entrySet().forEach(entry -> {
			entry.getValue().setUnlocalizedName(Namer.buildUnlocalizedName(ItemWand.PREFIX, entry.getKey().name().toLowerCase()));
			entry.getValue().setRegistryName(Namer.buildToolRegistryName(ItemWand.PREFIX, entry.getKey().name().toLowerCase()));
		});

		EnchantmentLoader.ENCHANTMENTS.forEach(enchantment -> {
			ItemEnchantedBook book = new ItemEnchantedBook();
			book.addEnchantment(new ItemStack(book), new EnchantmentData(enchantment, enchantment.getMaxLevel()));
			ENCHANTED_BOOKS.add(book);
		});
	}

	@Load(LoaderState.PREINITIALIZATION)
	public void registerItems() {
		registerItem(CRYSTAL, "crystal", "gemCrystal");
		registerItem(MAGIC_CRYSTAL, "magic_crystal", "gemMagicCrystal");
		WAND_MAP.entrySet().forEach(entry -> GameRegistry.register(entry.getValue()));
		ENCHANTED_BOOKS.forEach(book -> GameRegistry.register(book));
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

		registerRender(CRYSTAL);
		registerRender(MAGIC_CRYSTAL);
		WAND_MAP.entrySet().forEach(entry -> registerRender(entry.getValue()));
	}

	private void registerItem(Item item, String registryName) {
		GameRegistry.register(item.setRegistryName(registryName));
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
