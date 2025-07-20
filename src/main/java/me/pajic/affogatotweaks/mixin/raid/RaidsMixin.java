package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.raid.Raids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Raids.class)
public class RaidsMixin {

    @ModifyArg(
            method = "createOrExtendRaid",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/raid/Raids;getOrCreateRaid(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/entity/raid/Raid;"
            ),
            index = 1
    )
    private BlockPos raidStartsInPillagerOutpost(BlockPos original, @Local(argsOnly = true) BlockPos pos) {
        return pos;
    }
}
