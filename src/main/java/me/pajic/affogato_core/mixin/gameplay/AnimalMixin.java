package me.pajic.affogato_core.mixin.gameplay;

import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.ai.SearchForFoodGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public abstract class AnimalMixin extends Mob {
    protected AnimalMixin(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void addSearchForFoodGoal(EntityType<? extends Animal> entityType, Level level, CallbackInfo ci) {
        if (Main.CONFIG.features.animalsLookForFood.get()) {
            goalSelector.addGoal(3, new SearchForFoodGoal<>((Animal) (Object) this, 1.25, 16));
        }
    }
}
