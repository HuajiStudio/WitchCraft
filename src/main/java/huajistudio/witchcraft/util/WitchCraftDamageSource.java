package huajistudio.witchcraft.util;

import net.minecraft.util.DamageSource;

public class WitchCraftDamageSource {
	public static final DamageSource lightBall = new DamageSource("lightBall");
	public static final DamageSource explodingLightBall = (new DamageSource("explodingLightBall")).setExplosion();
}
