package huajistudio.witchcraft.item;

import huajistudio.witchcraft.block.BlockLoader;
import huajistudio.witchcraft.util.loader.ILoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader implements ILoader<Item> {
	public static final Item CRYSTAL = (new Item()).setUnlocalizedName("crystal").setCreativeTab(CreativeTabs.MATERIALS);

	@Override
	public void register() {
		registerItemBlock(BlockLoader.CRYSTAL_ORE, "crystal_ore", 1);
		registerItem(CRYSTAL, "crystal", 201);
	}

	@Override
	public RegistryNamespaced<ResourceLocation, Item> getRegistry() {
		return Item.REGISTRY;
	}

	@SideOnly(Side.CLIENT)
	public void registerRenders() {
		registerRender(BlockLoader.CRYSTAL_ORE);
		registerRender(CRYSTAL);
	}

	private void registerItem(Item item, String name, int id) {
		item.setRegistryName(name);
		registerImpl(id, item.getRegistryName(), item);
	}

	private void registerItemBlock(Block block, String name, int id) {
		registerItem(new ItemBlock(block), name, id);
	}

	@SideOnly(Side.CLIENT)
	private void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
	}

	@SideOnly(Side.CLIENT)
	private void registerRender(Block block) {
		ModelLoader.setCustomModelResourceLocation(new ItemBlock(block), 0, new ModelResourceLocation(block.getRegistryName().toString(), "inventory"));
	}

}
