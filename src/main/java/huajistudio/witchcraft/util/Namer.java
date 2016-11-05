package huajistudio.witchcraft.util;

public class Namer {
	public static String buildUnlocalizedName(String prefix, String... objects) {
		for (String str : objects) {
			prefix += (str.substring(0, 1).toUpperCase() + str.substring(1));
		}
		return prefix;
	}

	public static String buildRegistryName(String prefix, String... objects) {
		for (String str : objects) {
			prefix += ("_" + str);
		}
		return prefix;
	}
}
