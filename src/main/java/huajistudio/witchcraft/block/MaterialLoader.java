package huajistudio.witchcraft.block;

import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;

import javax.annotation.Nullable;

public class MaterialLoader implements ILoader<Material> {
	public static final Material CRYSTAL = new Material(MapColor.BLUE);

	@Override
	public void register() {
	}

	@Nullable
	@Override
	public RegistryNamespaced<ResourceLocation, Material> getRegistry() {
		return null;
	}
}
