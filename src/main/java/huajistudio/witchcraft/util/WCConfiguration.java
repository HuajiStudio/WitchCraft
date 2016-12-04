package huajistudio.witchcraft.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import huajistudio.witchcraft.util.wand.WandObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Configure the WitchCraft here.
 * You should call the function after
 * {@link net.minecraftforge.fml.common.event.FMLPreInitializationEvent FMLPreInitializationEvent}.
 */
@SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
public class WCConfiguration implements Flushable {
	private JsonObject coreConfigObj;
	private File coreConfigFile;

	/**
	 * Initialize the config.
	 * Note: the config is in JSON format.
	 * @param configDir directory of the config, contains all of the config file
     */
	public WCConfiguration(File configDir) {
		try {
			if (!configDir.isDirectory()) // If config directory is not a directory
				return; // Don't initialize
			Gson gson = new GsonBuilder().create(); // Create a gson object, so we can process the JSON file.
			coreConfigFile = new File(configDir, "core.json"); // Core config file
			if (!coreConfigFile.exists())
				coreConfigFile.createNewFile();
			// Read the JSON in UTF-8
			coreConfigObj = gson.fromJson(new InputStreamReader(new FileInputStream(coreConfigFile),
					StandardCharsets.UTF_8), new TypeToken<JsonObject>(){}.getType());
		} catch (IOException ignored) {}
	}

	/**
	 * Add a wand.
	 * @param wand a unique wandObject
     */
	public void addWand(WandObject wand) {
		JsonArray wandArray = coreConfigObj.getAsJsonArray("wands");
		for (JsonElement wandElement : wandArray) {
			// If this wand exists in the config
			if (wandElement.getAsJsonObject().get("uniqueId").getAsString().equals(wand.getUniqueIdStr()))
				return; // Don't do anything
		}
		JsonObject wandObj = new JsonObject();
		wandObj.addProperty("uniqueId", wand.getUniqueIdStr());
		wandObj.addProperty("unlocalizedName", wand.getUnlocalizedName());
		wandObj.addProperty("durability", wand.getDurability());
		wandObj.addProperty("magicCapability", wand.getMagicCapability());
		wandArray.add(wandObj);
		coreConfigObj.add("wands", wandArray);
		try {
			flush();
		} catch (IOException ignored) {}
	}

	public void deleteWand(UUID uniqueWand) {
		JsonArray wandArray = coreConfigObj.getAsJsonArray("wands");
		JsonArray result = new JsonArray();
		for (JsonElement wandElement : wandArray) {
			// If this wand not exists in the config
			if (!wandElement.getAsJsonObject().get("uniqueId").getAsString().equals(uniqueWand.toString()))
				result.add(wandElement); // Add to the result
		}
		coreConfigObj.add("wands", result);
		try {
			flush();
		} catch (IOException ignored) {}
	}

	/**
	 * Flush the data stored in.
	 * @throws IOException
     */
	@Override
	public void flush() throws IOException {
		Gson gson = new GsonBuilder().create();

		// Re-create the config file.
		coreConfigFile.delete();
		coreConfigFile.createNewFile();

		// Write the JSON.
		gson.toJson(coreConfigFile, new OutputStreamWriter(new FileOutputStream(coreConfigFile),
				StandardCharsets.UTF_8));
	}
}
