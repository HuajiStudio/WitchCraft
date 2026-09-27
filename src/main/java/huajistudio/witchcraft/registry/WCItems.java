package huajistudio.witchcraft.registry;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.entity.LightBallEntity;
import huajistudio.witchcraft.item.ElementWandItem;
import huajistudio.witchcraft.item.NormalWandItem;
import huajistudio.witchcraft.magic.MagicElement;
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

import java.util.ArrayList;
import java.util.List;

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
	public static final DeferredItem<ElementWandItem> METAL_WAND = registerElementWand(MagicElement.METAL, LightBallEntity.Effect.LIGHTNING, 2);
	public static final DeferredItem<ElementWandItem> PLANT_WAND = registerElementWand(MagicElement.PLANT, LightBallEntity.Effect.NONE, 2);
	public static final DeferredItem<ElementWandItem> WATER_WAND = registerElementWand(MagicElement.WATER, LightBallEntity.Effect.NONE, 2);
	public static final DeferredItem<ElementWandItem> FLAME_WAND = registerElementWand(MagicElement.FLAME, LightBallEntity.Effect.NONE, 2);
	public static final DeferredItem<ElementWandItem> SOIL_WAND = registerElementWand(MagicElement.SOIL, LightBallEntity.Effect.NONE, 2);
	public static final DeferredItem<ElementWandItem> LIGHT_WAND = registerElementWand(MagicElement.LIGHT, LightBallEntity.Effect.HARMING_CLOUD, 4);
	public static final DeferredItem<ElementWandItem> SHADOW_WAND = registerElementWand(MagicElement.SHADOW, LightBallEntity.Effect.NONE, 2);

	public static final DeferredItem<NormalWandItem> WOODEN_WAND = registerWand("wooden_wand", Tiers.WOOD);
	public static final DeferredItem<NormalWandItem> STONE_WAND = registerWand("stone_wand", Tiers.STONE);
	public static final DeferredItem<NormalWandItem> IRON_WAND = registerWand("iron_wand", Tiers.IRON);
	public static final DeferredItem<NormalWandItem> DIAMOND_WAND = registerWand("diamond_wand", Tiers.DIAMOND);
	public static final DeferredItem<NormalWandItem> GOLDEN_WAND = registerWand("golden_wand", Tiers.GOLD);
	public static final DeferredItem<NormalWandItem> REDSTONE_WAND = registerWand("redstone_wand", REDSTONE);

	public static Item getCrystal(MagicElement element) {
		return switch (element) {
			case METAL -> METAL_CRYSTAL.get();
			case PLANT -> PLANT_CRYSTAL.get();
			case WATER -> WATER_CRYSTAL.get();
			case FLAME -> FLAME_CRYSTAL.get();
			case SOIL -> SOIL_CRYSTAL.get();
			case LIGHT -> LIGHT_CRYSTAL.get();
			case SHADOW -> SHADOW_CRYSTAL.get();
		};
	}

	/**
	 * @return the crystals with magic: the magic crystal and the element crystals
	 */
	public static List<Item> getMagicCrystals() {
		List<Item> crystals = new ArrayList<>();
		crystals.add(MAGIC_CRYSTAL.get());
		for (MagicElement element : MagicElement.values())
			crystals.add(getCrystal(element));
		return crystals;
	}

	private static DeferredItem<ElementWandItem> registerElementWand(MagicElement element, LightBallEntity.Effect effect, double magicCost) {
		return ITEMS.register(element.getSerializedName() + "_wand", () -> new ElementWandItem(element, effect, magicCost));
	}

	private static DeferredItem<NormalWandItem> registerWand(String name, Tier material) {
		return ITEMS.register(name, () -> new NormalWandItem(material));
	}
}
