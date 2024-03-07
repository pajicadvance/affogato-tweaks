package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowAttackGoal.class)
public class BowAttackGoalMixin<T extends HostileEntity & RangedAttackMob> {

    @Shadow
    @Final
    private T actor;

    // Plays a bow drawing sound when skeletons start drawing the bow
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    private void playBowDrawingSound(CallbackInfo ci) {
        actor.playSound(SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_1, 1.0F, 1.0F);
    }
}
