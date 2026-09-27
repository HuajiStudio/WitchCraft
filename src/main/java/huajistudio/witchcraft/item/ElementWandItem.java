package huajistudio.witchcraft.item;

import huajistudio.witchcraft.entity.LightBallEntity;
import huajistudio.witchcraft.magic.MagicElement;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * A wand made with an element crystal, shooting light balls of that element. It never breaks.
 */
public class ElementWandItem extends WandItem {
	private final MagicElement element;
	private final LightBallEntity.Effect effect;
	private final double magicCost;

	public ElementWandItem(MagicElement element, LightBallEntity.Effect effect, double magicCost) {
		this.element = element;
		this.effect = effect;
		this.magicCost = magicCost;
	}

	public MagicElement getElement() {
		return element;
	}

	@Override
	public double getMagicCost() {
		return magicCost;
	}

	@Override
	public AbstractHurtingProjectile newBullet(Level level, LivingEntity shooter, ItemStack stack) {
		LightBallEntity ball = new LightBallEntity(level, shooter, effect);
		ball.setElement(element);
		return ball;
	}
}
