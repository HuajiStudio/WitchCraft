package huajistudio.witchcraft.item;

public abstract class ItemMagicSword extends ItemMagicToolBase {
	protected abstract float getAttackDamage();

	public ItemMagicSword() {
		super(new Properties().stacksTo(1));
	}
}
