package me.pajic.affogatotweaks.mixin.oxidation;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.ClientMain;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HorseArmorLayer.class)
public class HorseArmorLayerMixin {

    @ModifyExpressionValue(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/horse/Horse;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/AnimalArmorItem;getTexture()Lnet/minecraft/resources/ResourceLocation;"
            )
    )
    private ResourceLocation applyOxidationStageTexture(ResourceLocation original, @Local ItemStack stack) {
        return ClientMain.getAssetId(stack, original);
    }
}
