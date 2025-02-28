package kl1nge5.blueprint_tweaks.mixin;

import kl1nge5.blueprint_tweaks.ModBlocks;
import com.simibubi.create.foundation.utility.BlockHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockHelper.class)
public class CreateBlockHelperMixin {
	@ModifyVariable(method = "placeSchematicBlock", at = @At("HEAD"), remap = false)
	private static BlockState convertPlaceholderToAir(BlockState state) {
		if (state.is(ModBlocks.AIR_PLACEHOLDER)) {
			return Blocks.AIR.defaultBlockState();
		}
		return state;
	}
}
