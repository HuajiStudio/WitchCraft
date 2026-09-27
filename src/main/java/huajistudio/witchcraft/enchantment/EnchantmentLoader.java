package huajistudio.witchcraft.enchantment;

import huajistudio.witchcraft.WitchCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

/**
 * Enchantments are data-driven: they are defined in {@code data/witchcraft/enchantment}.
 */
public class EnchantmentLoader {
	public static final ResourceKey<Enchantment> EXPLOSION = key("explosion");
	public static final ResourceKey<Enchantment> STABLE_LIGHTBALL = key("stable_lightball");

	public static int getLevel(Level level, ResourceKey<Enchantment> enchantment, ItemStack stack) {
		return level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolder(enchantment)
				.map(stack::getEnchantmentLevel)
				.orElse(0);
	}

	private static ResourceKey<Enchantment> key(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, WitchCraft.id(name));
	}
}
