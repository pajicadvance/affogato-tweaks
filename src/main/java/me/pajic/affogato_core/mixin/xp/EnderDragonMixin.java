package me.pajic.affogato_core.mixin.xp;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.pajic.affogato_core.Main;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderDragon.class)
public class EnderDragonMixin {

    @Shadow
    private @Nullable EndDragonFight dragonFight;

    @WrapWithCondition(
            method = "tickDeath",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"
            )
    )
    private boolean noXpDropOnSubsequentKills(ServerLevel level, Vec3 pos, int amount) {
        return Main.CONFIG.experience.noEnderDragonXpAfterFirst.get() && dragonFight != null && !dragonFight.hasPreviouslyKilledDragon();
    }
}
