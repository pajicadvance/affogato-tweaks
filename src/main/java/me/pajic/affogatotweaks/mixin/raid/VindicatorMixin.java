package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.monster.Vindicator;
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
        return false;
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
        return false;
    }
}
