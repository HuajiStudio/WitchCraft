package huajistudio.witchcraft.magic;

import net.minecraft.util.StringRepresentable;

/**
 * The elements of the element crystals, and of the wands and light balls made with them.
 */
public enum MagicElement implements StringRepresentable {
	METAL("metal"),
	PLANT("plant"),
	WATER("water"),
	FLAME("flame"),
	SOIL("soil"),
	LIGHT("light"),
	SHADOW("shadow");

	public static final StringRepresentable.EnumCodec<MagicElement> CODEC = StringRepresentable.fromEnum(MagicElement::values);

	private final String name;

	MagicElement(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
