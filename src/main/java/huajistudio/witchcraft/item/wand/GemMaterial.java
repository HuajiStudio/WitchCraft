package huajistudio.witchcraft.item.wand;

/**
 * The magic wand require a gem to spell.
 **/
public enum GemMaterial {
	DIAMOND(8.0F),
	EMERALD(6.0F),
	LAPIS(4.0F);

	public float getDamageVsEntity() {
		return damageVsEntity;
	}

	@Override
	public String toString() {
		switch (this) {
			case DIAMOND:
				return "Diamond";
			case EMERALD:
				return "Emerald";
			case LAPIS:
				return "Lapis";
			default:
				return "";
		}
	}

	private final float damageVsEntity;

	GemMaterial(float damageVsEntity) {
		this.damageVsEntity = damageVsEntity;
	}
}
