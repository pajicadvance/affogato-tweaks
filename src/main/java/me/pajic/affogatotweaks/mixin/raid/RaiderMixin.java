package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Raider.class)
public class RaiderMixin {

    @WrapWithCondition(
            method = "die",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/raid/Raid;addHeroOfTheVillage(Lnet/minecraft/world/entity/Entity;)V"
            )
    )
    private boolean noHeroes(Raid instance, Entity player) {
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
    private boolean disableMoveThroughVillageGoal(GoalSelector instance, int priority, Goal goal) {
        return false;
    }

    @WrapWithCondition(
            method = "registerGoals",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
                    ordinal = 3
            )
    )
    private boolean disableCelebrateGoal(GoalSelector instance, int priority, Goal goal) {
        return false;
    }
}
