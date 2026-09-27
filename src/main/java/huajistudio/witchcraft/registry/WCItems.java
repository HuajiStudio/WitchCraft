package huajistudio.witchcraft.registry;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.item.LightWandItem;
import huajistudio.witchcraft.item.MetalWandItem;
import huajistudio.witchcraft.item.NormalWandItem;
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

/**
 * Items are put into tags (the replacement of the ore dictionary) in {@code data/c/tags/item}.
 */
public class WCItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WitchCraft.MODID);

	public static final Tier REDSTONE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 7.0F, 2.5F, 17,
			() -> Ingredient.of(Items.REDSTONE));

	public static final DeferredItem<BlockItem> CRYSTAL_ORE = ITEMS.registerSimpleBlockItem(WCBlocks.CRYSTAL_ORE);
	public static final DeferredItem<BlockItem> CRYSTAL_BLOCK = ITEMS.registerSimpleBlockItem(WCBlocks.CRYSTAL_BLOCK);
	public static final DeferredItem<BlockItem> MAGIC_CRYSTAL_BLOCK = ITEMS.registerSimpleBlockItem(WCBlocks.MAGIC_CRYSTAL_BLOCK);

	public static final DeferredItem<Item> CRYSTAL = ITEMS.registerSimpleItem("crystal");
	public static final DeferredItem<Item> MAGIC_CRYSTAL = ITEMS.registerSimpleItem("magic_crystal");
	public static final DeferredItem<Item> METAL_CRYSTAL = ITEMS.registerSimpleItem("metal_crystal");
	public static final DeferredItem<Item> PLANT_CRYSTAL = ITEMS.registerSimpleItem("plant_crystal");
	public static final DeferredItem<Item> WATER_CRYSTAL = ITEMS.registerSimpleItem("water_crystal");
	public static final DeferredItem<Item> FLAME_CRYSTAL = ITEMS.registerSimpleItem("flame_crystal");
	public static final DeferredItem<Item> SOIL_CRYSTAL = ITEMS.registerSimpleItem("soil_crystal");
	public static final DeferredItem<Item> LIGHT_CRYSTAL = ITEMS.registerSimpleItem("light_crystal");
	public static final DeferredItem<Item> SHADOW_CRYSTAL = ITEMS.registerSimpleItem("shadow_crystal");
	public static final DeferredItem<MetalWandItem> METAL_WAND = ITEMS.register("metal_wand", MetalWandItem::new);
	public static final DeferredItem<LightWandItem> LIGHT_WAND = ITEMS.register("light_wand", LightWandItem::new);

	public static final DeferredItem<NormalWandItem> WOODEN_WAND = registerWand("wooden_wand", Tiers.WOOD);
	public static final DeferredItem<NormalWandItem> STONE_WAND = registerWand("stone_wand", Tiers.STONE);
	public static final DeferredItem<NormalWandItem> IRON_WAND = registerWand("iron_wand", Tiers.IRON);
	public static final DeferredItem<NormalWandItem> DIAMOND_WAND = registerWand("diamond_wand", Tiers.DIAMOND);
	public static final DeferredItem<NormalWandItem> GOLDEN_WAND = registerWand("golden_wand", Tiers.GOLD);
	public static final DeferredItem<NormalWandItem> REDSTONE_WAND = registerWand("redstone_wand", REDSTONE);

	private static DeferredItem<NormalWandItem> registerWand(String name, Tier material) {
		return ITEMS.register(name, () -> new NormalWandItem(material));
	}
}
