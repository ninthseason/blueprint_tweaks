package kl1nge5.blueprint_tweaks.mixin;

import com.simibubi.create.content.schematics.packet.SchematicPlacePacket;

import com.simibubi.create.foundation.networking.SimplePacketBase;

import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SchematicPlacePacket.class)
abstract class CreateSchematicPlacePacketMixin {
	@Shadow
	public ItemStack stack;

	@Redirect(method = "lambda$handle$2", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;isCreative()Z"))
	private boolean isCreativeRedirect(ServerPlayer instance) {
		return true;
	}

	@Inject(method = "lambda$handle$2", at = @At("HEAD"), remap = false, cancellable = true)
	private void lambda$handle$2(SimplePacketBase.Context context, CallbackInfo ci) {
		// 检查玩家背包里是否真的有原理图，防止用神奇的手法作弊
		ServerPlayer player = context.getSender();
		if (player != null) {
			Inventory inventory = player.getInventory();
			if (!inventory.contains(stack)){
				ci.cancel();
				return;
			}
			if (!player.isCreative()) {  // 如果是生存模式则消耗一份原理图
				inventory.getItem(inventory.findSlotMatchingItem(stack)).shrink(1);
			}
		}
	}
}
