package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ExperienceBottleEntity.class)
public class ExperienceBottleEntityMixin {

    // Increases the amount of experience dropped by bottles of enchanting
    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"), index = 2)
    private int setXpDropAmount(int amount) {
        ExperienceBottleEntity instance = (ExperienceBottleEntity) (Object) this;
        return 30 + instance.getWorld().random.nextInt(11) + instance.getWorld().random.nextInt(11);
    }
}
