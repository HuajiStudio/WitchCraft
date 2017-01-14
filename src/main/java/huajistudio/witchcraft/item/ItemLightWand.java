package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.EntityLightBall;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * @author sunday
 **/
public class ItemLightWand extends ItemWand {
	@Override
	public int getMagicCost() {
		return 4;
	}

	@Override
	public EntityFireball newBullet(World world, EntityLivingBase shooter) {
		return new EntityLightBall(world, shooter) {
			@Override
			protected void onImpact(@Nonnull RayTraceResult result) {
				if (worldObj.isRemote)
					return;
				EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(worldObj, posX, posY, posZ);
				entityareaeffectcloud.setOwner(shootingEntity);
				entityareaeffectcloud.setParticle(EnumParticleTypes.ENCHANTMENT_TABLE);
				entityareaeffectcloud.setRadius(75.0F);
				entityareaeffectcloud.setDuration(2400);
				entityareaeffectcloud.setRadiusPerTick((7.0F - entityareaeffectcloud.getRadius()) / (float)entityareaeffectcloud.getDuration());
				entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 2400, 1));
				this.worldObj.spawnEntityInWorld(entityareaeffectcloud);
				this.setDead();
			}
		};
	}
}
