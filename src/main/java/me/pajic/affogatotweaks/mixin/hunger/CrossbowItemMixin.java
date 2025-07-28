package me.pajic.affogatotweaks.mixin.hunger;

import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Inject(
            method = "method_59975",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"
            )
    )
    private static void crossbowExhaustion(Level level, LivingEntity livingEntity, Holder<SoundEvent> holder, CallbackInfo ci) {
        if (livingEntity instanceof Player player && !level.isClientSide) {
            player.causeFoodExhaustion(ExhaustionValues.DRAW_RANGED_WEAPON);
        }
    }
}
