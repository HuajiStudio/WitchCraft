package huajistudio.witchcraft.util.loader;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;

import javax.annotation.Nullable;

public interface ILoader<T> {
	void register();

	@Nullable
	RegistryNamespaced<ResourceLocation, T> getRegistry();

	default void registerImpl(int id, ResourceLocation key, T value) {
		getRegistry().register(id, key, value);
	}
}
