package huajistudio.witchcraft.item.wand;

public enum GemMaterial {
	DIAMOND(8.0F),
	EMERALD(6.0F),
	LAPIS(4.0F);

	public float getDamageVsEntity() {
		return damageVsEntity;
	}

	private final float damageVsEntity;

	private GemMaterial(float damageVsEntity) {
		this.damageVsEntity = damageVsEntity;
	}
}
