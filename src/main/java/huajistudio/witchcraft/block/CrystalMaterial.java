package huajistudio.witchcraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.lang.reflect.Field;

public final class CrystalMaterial extends Material {
	public CrystalMaterial() {
		super(MapColor.BLUE);
		try {
			Field isTranslucent = CrystalMaterial.class.getDeclaredField("field_76240_I");
			isTranslucent.setAccessible(true);
			isTranslucent.setBoolean(this, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
