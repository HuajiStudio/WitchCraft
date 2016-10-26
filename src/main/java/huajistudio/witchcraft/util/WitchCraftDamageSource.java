package huajistudio.witchcraft.util;

import net.minecraft.util.DamageSource;

public class WitchCraftDamageSource {
	public static DamageSource lightBall = new DamageSource("lightBall");
	public static DamageSource explodingLightBall = (new DamageSource("explodingLightBall")).setExplosion();
}
