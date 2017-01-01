package huajistudio.witchcraft.util;

import com.google.common.collect.Lists;import java.util.Objects;import java.util.List;
public class Namer {
	public static String buildUnlocalizedName(String prefix, String... objects) {
		for (String str : objects) {
			prefix += (str.substring(0, 1).toUpperCase() + str.substring(1));
		}
		return prefix;
	}

	public static String buildUnlocalizedName(String... objects) {
		List<String> stringList = Lists.newArrayList(objects);
		stringList.remove(objects[0]);
		return buildUnlocalizedName(objects[0], stringList.toArray(new String[0]));
	}

	public static String buildRegistryName(String prefix, String... objects) {
		for (String str : objects) {
			prefix = str + "_" + prefix;
		}
		return prefix;
	}

	public static String buildToolRegistryName(String prefix, String... objects) {
		for (String str : objects) {
			prefix = Objects.equals(str, "wood") || Objects.equals(str, "gold") ? str + "en_" + prefix : str + "_" + prefix;
		}
		return prefix;
	}
}
