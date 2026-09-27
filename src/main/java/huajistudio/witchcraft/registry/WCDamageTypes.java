package huajistudio.witchcraft.registry;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

/**
 * Damage types are data-driven: they are defined in {@code data/witchcraft/damage_type}.
 */
public class WCDamageTypes {
	public static final ResourceKey<DamageType> LIGHT_BALL = ResourceKey.create(Registries.DAMAGE_TYPE, WitchCraft.id("light_ball"));

	public static DamageSource lightBall(Entity lightBall, @Nullable Entity shooter) {
		return lightBall.damageSources().source(LIGHT_BALL, lightBall, shooter);
	}
}
