package me.pajic.affogatotweaks.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RangedBowAttackGoal.class)
public class RangedBowAttackGoalMixin<T extends Monster & RangedAttackMob> {

    @Shadow
    @Final
    private T mob;

    // Plays a bow drawing sound when skeletons start drawing the bow
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Monster;startUsingItem(Lnet/minecraft/world/InteractionHand;)V"))
    private void playBowDrawingSound(CallbackInfo ci) {
        mob.playSound(SoundEvents.CROSSBOW_QUICK_CHARGE_1, 1.0F, 1.0F);
    }
}
