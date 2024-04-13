package me.pajic.affogatotweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmony.feature.variant_wood.block.VariantLadderBlock;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    // Prevents living entities from dropping experience
    @Inject(method = "shouldDropXp", at = @At("RETURN"), cancellable = true)
    private void preventEntityXpDrops(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    // Allows ladder variants from Charm to connect to trapdoors
    @ModifyExpressionValue(method = "canEnterTrapdoor", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean modifyAllowedLadders(boolean original, @Local(ordinal = 1) BlockState blockState) {
        return original || blockState.getBlock() instanceof VariantLadderBlock;
    }
}
