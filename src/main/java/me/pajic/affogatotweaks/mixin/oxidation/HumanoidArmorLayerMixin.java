package me.pajic.affogatotweaks.mixin.oxidation;

import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.ClientMain;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin {

    @ModifyArg(
            method = "renderArmorPiece",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/HumanoidModel;ILnet/minecraft/resources/ResourceLocation;)V"
            ),
            index = 5
    )
    private ResourceLocation applyOxidationStageTexture(ResourceLocation original, @Local ItemStack stack) {
        return ClientMain.getAssetId(stack, original);
    }
}
