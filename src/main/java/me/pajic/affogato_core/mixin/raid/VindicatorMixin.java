package me.pajic.affogato_core.mixin.raid;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.pajic.affogato_core.Main;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.monster.illager.Vindicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Vindicator.class)
public class VindicatorMixin {

    @WrapWithCondition(
            method = "registerGoals",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
                    ordinal = 1
            )
    )
    private boolean disableBreakDoorGoal(GoalSelector instance, int priority, Goal goal) {
        return !Main.CONFIG.features.raidRework.get();
    }

    @WrapWithCondition(
            method = "registerGoals",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
                    ordinal = 2
            )
    )
    private boolean disableOpenDoorGoal(GoalSelector instance, int priority, Goal goal) {
        return !Main.CONFIG.features.raidRework.get();
    }
}
