package huajistudio.witchcraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.lang.reflect.Field;

public final class CrystalMaterial extends Material {
	public CrystalMaterial() {
		super(MapColor.PURPLE);
		try {
			Field isTranslucent = Material.class.getDeclaredField("isTranslucent");
			isTranslucent.setAccessible(true);
			isTranslucent.setBoolean(this, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
