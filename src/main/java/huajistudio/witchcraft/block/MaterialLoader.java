package huajistudio.witchcraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.lang.reflect.Field;

public class MaterialLoader {
	public static final Material CRYSTAL = new Material(MapColor.PURPLE);

	static {
		try {
			Field isTranslucent = Material.class.getDeclaredField("isTranslucent");
			isTranslucent.setAccessible(true);
			isTranslucent.setBoolean(CRYSTAL, true);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
