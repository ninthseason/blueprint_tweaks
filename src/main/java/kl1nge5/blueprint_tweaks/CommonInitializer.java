package kl1nge5.blueprint_tweaks;

import com.simibubi.create.foundation.data.CreateRegistrate;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonInitializer implements ModInitializer {
	public static final String ID = "blueprint_tweaks";
	public static final String NAME = "Blueprint Tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID).defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

	@Override
	public void onInitialize() {
		ModBlocks.init();
		REGISTRATE.register();
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
