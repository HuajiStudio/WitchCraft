package huajistudio.witchcraft.util.loader;

import java.lang.reflect.ParameterizedType;
public interface ILoader<T> {
	void register();

	default Class<T> getLoaderType() {
		return (Class<T>)(((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
}
