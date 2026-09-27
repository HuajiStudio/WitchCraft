package huajistudio.witchcraft.block;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Drops are defined by the loot tables in {@code data/witchcraft/loot_table/blocks}.
 */
public class BlockLoader {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WitchCraft.MODID);

	public static final DeferredBlock<Block> CRYSTAL_ORE = BLOCKS.register("crystal_ore",
			() -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of()
					.mapColor(MapColor.STONE)
					.instrument(NoteBlockInstrument.BASEDRUM)
					.requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> CRYSTAL_BLOCK = BLOCKS.register("crystal_block",
			() -> new BlockCrystalBase(BlockCrystalBase.crystalProperties()
					.strength(5.0F, 6.0F)));
	public static final DeferredBlock<Block> MAGIC_CRYSTAL_BLOCK = BLOCKS.register("magic_crystal_block",
			() -> new BlockCrystalBase(BlockCrystalBase.crystalProperties()
					.strength(5.0F, 6.0F)
					.lightLevel(state -> 15)));
	// The crystal cluster (BlockCrystalCluster) is not registered yet: it has no texture or loot table.
}
