package kl1nge5.blueprint_tweaks.mixin;

import com.simibubi.create.content.schematics.client.tools.ToolType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ToolType.class)
public class CreateToolTypeMixin {
	@ModifyVariable(method = "getTools", at = @At("HEAD"), remap = false, argsOnly = true)
	private static boolean keepAlwaysCreateMode(boolean value) {
		return true;
	}
}
