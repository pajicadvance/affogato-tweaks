package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin {

    // Prevents the ender dragon from dropping experience on death
    @Redirect(method = "updatePostDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"))
    private void preventXpOrbSpawn(ServerWorld world, Vec3d pos, int amount) {
    }
}
