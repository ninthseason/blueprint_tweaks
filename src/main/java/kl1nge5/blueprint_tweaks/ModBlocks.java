package kl1nge5.blueprint_tweaks;

import kl1nge5.blueprint_tweaks.blocks.AirPlaceholderBlock;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class ModBlocks {
	public static AirPlaceholderBlock AIR_PLACEHOLDER;

	static {
		BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noParticlesOnBreak().noCollission();
		properties = properties.forceSolidOn();
		AIR_PLACEHOLDER = Registry.register(BuiltInRegistries.BLOCK, CommonInitializer.id("air_placeholder"), new AirPlaceholderBlock(properties));
	}

	public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, CommonInitializer.id("item_group"));
	public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(AIR_PLACEHOLDER))
			.title(Component.translatable("itemGroup.blueprint_tweaks"))
			.build();
	public static void init() {
		BlockRenderLayerMap.INSTANCE.putBlock(AIR_PLACEHOLDER, RenderType.translucent());
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY.location(), CUSTOM_ITEM_GROUP);
		Registry.register(BuiltInRegistries.ITEM, CommonInitializer.id("air_placeholder"), new BlockItem(AIR_PLACEHOLDER, new Item.Properties()));
		ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(group -> {
			group.accept(new ItemStack(AIR_PLACEHOLDER));
		});
	}
}
