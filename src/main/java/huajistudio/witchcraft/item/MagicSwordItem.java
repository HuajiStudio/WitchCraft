package huajistudio.witchcraft.item;

public abstract class MagicSwordItem extends MagicToolItem {
	protected abstract float getAttackDamage();

	public MagicSwordItem() {
		super(new Properties().stacksTo(1));
	}
}
