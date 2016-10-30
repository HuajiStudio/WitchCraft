package huajistudio.witchcraft.block;

import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialLoader implements ILoader<Material> {
	public static final Material CRYSTAL = new Material(MapColor.BLUE);

	@Override
	public void register() {
	}
}
