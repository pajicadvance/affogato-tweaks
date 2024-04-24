package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrownExperienceBottle.class)
public class ThrownExperienceBottleMixin {

    // Increases the amount of experience dropped by bottles of enchanting
    @ModifyArg(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"), index = 2)
    private int setXpDropAmount(int amount) {
        ThrownExperienceBottle instance = (ThrownExperienceBottle) (Object) this;
        return 30 + instance.level().random.nextInt(11) + instance.level().random.nextInt(11);
    }
}
