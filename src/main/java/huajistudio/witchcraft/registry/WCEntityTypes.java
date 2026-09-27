package huajistudio.witchcraft.registry;

import huajistudio.witchcraft.WitchCraft;
import huajistudio.witchcraft.entity.LightBallEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WCEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, WitchCraft.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<LightBallEntity>> LIGHT_BALL = ENTITY_TYPES.register("light_ball",
			() -> EntityType.Builder.<LightBallEntity>of(LightBallEntity::new, MobCategory.MISC)
					.sized(0.3125F, 0.3125F)
					.clientTrackingRange(4)
					.updateInterval(10)
					.build("light_ball"));
}
