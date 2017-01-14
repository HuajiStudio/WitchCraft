package huajistudio.witchcraft.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
public class Namer {
	public static String buildUnlocalizedName(String... objects) {
		List<String> stringList = Lists.newArrayList(objects);
		String result = stringList.remove(0);
		for (String str: stringList) {
			result += str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return result;
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
