package huajistudio.witchcraft.item;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.util.Namer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Items are put into tags (the replacement of the ore dictionary) in {@code data/c/tags/item}.
 */
public class ItemLoader {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WitchCraft.MODID);

	public static final Tier REDSTONE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 7.0F, 2.5F, 17,
			() -> Ingredient.of(Items.REDSTONE));

	public static final DeferredItem<BlockItem> CRYSTAL_ORE = ITEMS.registerSimpleBlockItem(BlockLoader.CRYSTAL_ORE);
	public static final DeferredItem<BlockItem> CRYSTAL_BLOCK = ITEMS.registerSimpleBlockItem(BlockLoader.CRYSTAL_BLOCK);
	public static final DeferredItem<BlockItem> MAGIC_CRYSTAL_BLOCK =ITEMS.registerSimpleBlockItem(BlockLoader.MAGIC_CRYSTAL_BLOCK);

	public static final DeferredItem<Item> CRYSTAL = ITEMS.registerSimpleItem("crystal");
	public static final DeferredItem<Item> MAGIC_CRYSTAL = ITEMS.registerSimpleItem("magic_crystal");
	public static final DeferredItem<Item> METAL_CRYSTAL = ITEMS.registerSimpleItem("metal_crystal");
	public static final DeferredItem<Item> PLANT_CRYSTAL = ITEMS.registerSimpleItem("plant_crystal");
	public static final DeferredItem<Item> WATER_CRYSTAL = ITEMS.registerSimpleItem("water_crystal");
	public static final DeferredItem<Item> FLAME_CRYSTAL = ITEMS.registerSimpleItem("flame_crystal");
	public static final DeferredItem<Item> SOIL_CRYSTAL = ITEMS.registerSimpleItem("soil_crystal");
	public static final DeferredItem<Item> LIGHT_CRYSTAL = ITEMS.registerSimpleItem("light_crystal");
	public static final DeferredItem<Item> SHADOW_CRYSTAL = ITEMS.registerSimpleItem("shadow_crystal");
	public static final DeferredItem<ItemMetalWand> METAL_WAND = ITEMS.register("metal_wand", ItemMetalWand::new);
	public static final DeferredItem<ItemLightWand> LIGHT_WAND = ITEMS.register("light_wand", ItemLightWand::new);

	/**
	 * The materials of the normal wands, by the name used in their registry name.
	 */
	public static final Map<String, Tier> WAND_MATERIALS = new LinkedHashMap<>();
	public static final Map<Tier, DeferredItem<ItemNormalWand>> WAND_MAP = new LinkedHashMap<>();

	static {
		WAND_MATERIALS.put("wood", Tiers.WOOD);
		WAND_MATERIALS.put("stone", Tiers.STONE);
		WAND_MATERIALS.put("iron", Tiers.IRON);
		WAND_MATERIALS.put("diamond", Tiers.DIAMOND);
		WAND_MATERIALS.put("gold", Tiers.GOLD);
		WAND_MATERIALS.put("redstone", REDSTONE);
		WAND_MATERIALS.forEach((name, material) -> WAND_MAP.put(material, ITEMS.register(
				Namer.buildToolRegistryName(ItemNormalWand.PREFIX, name), () -> new ItemNormalWand(material))));
	}
}
