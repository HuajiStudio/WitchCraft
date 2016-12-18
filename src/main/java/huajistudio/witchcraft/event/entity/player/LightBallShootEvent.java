package huajistudio.witchcraft.event.entity.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class LightBallShootEvent extends PlayerEvent {
	private final ItemStack wand;
	private final World world;
	private int charge;

	public LightBallShootEvent(EntityPlayer player, ItemStack wand, World world, int charge) {
		super(player);
		this.wand = wand;
		this.world = world;
		this.charge = charge;
	}

	public ItemStack getWand() {
		return wand;
	}

	public World getWorld() {
		return world;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}
}
