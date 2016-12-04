package huajistudio.witchcraft.util.wand;

import java.util.UUID;

public class WandObject {
	private String unlocalizedName;
	private int durability;
	private int magicCapability;
	private UUID uniqueId;

	public String getUniqueIdStr() {
		return uniqueId.toString();
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		try {
			this.uniqueId = UUID.fromString(uniqueId);
		} catch (IllegalArgumentException ignored) {
			this.uniqueId = UUID.randomUUID();
		}
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getMagicCapability() {
		return magicCapability;
	}

	public void setMagicCapability(int magicCapability) {
		this.magicCapability = magicCapability;
	}

	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public void setUnlocalizedName(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
	}
}
