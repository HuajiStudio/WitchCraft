package huajistudio.witchcraft.common;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.block.MaterialLoader;
import huajistudio.witchcraft.crafting.CraftingLoader;
import huajistudio.witchcraft.enchantment.EnchantmentLoader;
import huajistudio.witchcraft.entity.EntityLoader;
import huajistudio.witchcraft.item.ItemLoader;
import huajistudio.witchcraft.util.loader.Load;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.*;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommonProxy {
	public Class[] loaders = new Class[]{MaterialLoader.class, BlockLoader.class, ItemLoader.class,
			EnchantmentLoader.class, CraftingLoader.class, EntityLoader.class};

	public Collection<Object> loaderObjects = new ArrayList<>();

	public Map<LoaderState, Map<Object, Collection<Method>>> stateCollectionMap = new HashMap<>();

	public CommonProxy() {
		stateCollectionMap.put(LoaderState.CONSTRUCTING, new HashMap<>());
		stateCollectionMap.put(LoaderState.PREINITIALIZATION, new HashMap<>());
		stateCollectionMap.put(LoaderState.INITIALIZATION, new HashMap<>());
		stateCollectionMap.put(LoaderState.POSTINITIALIZATION, new HashMap<>());
		stateCollectionMap.put(LoaderState.AVAILABLE, new HashMap<>());
		for (Class clz : loaders) {
			try {
				Object loader = clz.newInstance();
				loaderObjects.add(loader);
				for (Method m : clz.getMethods()) {
					for (Annotation annotation : m.getDeclaredAnnotations()) {
						if (annotation.annotationType().equals(Load.class)) {
							Load load = (Load) annotation;
							if (!stateCollectionMap.containsKey(load.value()))
								stateCollectionMap.put(load.value(), new HashMap<>());
							Map<Object, Collection<Method>> objectMap = stateCollectionMap.get(load.value());
							if (!objectMap.containsKey(loader))
								objectMap.put(loader, new ArrayList<>());
							Collection<Method> methods = objectMap.get(loader);
							methods.add(m);
							objectMap.put(loader, methods);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Nullable
	public ItemLoader getItemLoader() {
		for (Object o : loaderObjects)
			if (o instanceof ItemLoader)
				return (ItemLoader) o;
		return null;
	}

	@Nullable
	public BlockLoader getBlockLoader() {
		for (Object o : loaderObjects)
			if (o instanceof BlockLoader)
				return (BlockLoader) o;
		return null;
	}

	public void construction(FMLConstructionEvent event) {
		invokeForEvent(LoaderState.CONSTRUCTING, event);
	}

	public void preInit(FMLPreInitializationEvent event) {
		invokeForEvent(LoaderState.PREINITIALIZATION, event);
	}

	public void init(FMLInitializationEvent event) {
		invokeForEvent(LoaderState.INITIALIZATION, event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		invokeForEvent(LoaderState.POSTINITIALIZATION, event);
	}

	public void loadComplete(FMLLoadCompleteEvent event) {
		invokeForEvent(LoaderState.AVAILABLE, event);
	}

	private <T extends FMLStateEvent> void invokeForEvent(LoaderState state, T event) {
		if (stateCollectionMap.containsKey(state)) {
			for (Map.Entry<Object, Collection<Method>> entry : stateCollectionMap.get(state).entrySet()) {
				for (Method m : entry.getValue()) {
					try {
						if (m.getParameterCount() == 0) {
							m.invoke(entry.getKey());
						} else if (m.getParameterCount() == 1) {
							m.invoke(entry.getKey(), event);
						} else {
							throw new IllegalArgumentException("Given argument is illegal");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
