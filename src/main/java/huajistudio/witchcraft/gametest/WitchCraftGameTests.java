package huajistudio.witchcraft.gametest;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.entity.LightBallEntity;
import huajistudio.witchcraft.magic.MagicStats;
import huajistudio.witchcraft.registry.WCAttachmentTypes;
import huajistudio.witchcraft.registry.WCEntityTypes;
import huajistudio.witchcraft.registry.WCItems;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

/**
 * Run with {@code ./gradlew runGameTestServer}, or {@code /test runall} in a development world.
 */
@GameTestHolder(WitchCraft.MODID)
@PrefixGameTestTemplate(false)
public class WitchCraftGameTests {
	@GameTest(template = "empty")
	public static void wandCostsMagic(GameTestHelper helper) {
		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		ItemStack wand = new ItemStack(WCItems.METAL_WAND.get());
		player.setData(WCAttachmentTypes.MAGIC_STATS, new MagicStats(20, 5));

		wand.releaseUsing(helper.getLevel(), player, 72000 - 20);
		helper.assertValueEqual(MagicStats.get(player).amount(), 3.0, "magic after one shot");
		wand.releaseUsing(helper.getLevel(), player, 72000 - 20);
		helper.assertValueEqual(MagicStats.get(player).amount(), 1.0, "magic after two shots");
		wand.releaseUsing(helper.getLevel(), player, 72000 - 20);
		helper.assertValueEqual(MagicStats.get(player).amount(), 1.0, "magic when it is not enough to shoot");
		helper.succeed();
	}

	@GameTest(template = "empty")
	public static void magicIsCappedByCapacity(GameTestHelper helper) {
		helper.assertValueEqual(new MagicStats(20, 25).amount(), 20.0, "magic amount");
		helper.succeed();
	}

	@GameTest(template = "empty")
	public static void lightBallKeepsEffectWhenSaved(GameTestHelper helper) {
		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		LightBallEntity lightBall = new LightBallEntity(helper.getLevel(), player, LightBallEntity.Effect.LIGHTNING);
		CompoundTag tag = lightBall.saveWithoutId(new CompoundTag());

		LightBallEntity loaded = new LightBallEntity(WCEntityTypes.LIGHT_BALL.get(), helper.getLevel());
		loaded.load(tag);
		helper.assertValueEqual(loaded.getEffect(), LightBallEntity.Effect.LIGHTNING, "light ball effect");
		helper.succeed();
	}
}
