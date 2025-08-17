package me.pajic.affogatotweaks.ai;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SearchForFoodGoal<T extends Animal> extends Goal {
    private final T animal;
    private final double speedModifier;
    private final float within;
    @Nullable private ItemEntity target;

    public SearchForFoodGoal(T animal, double speedModifier, float within) {
        this.animal = animal;
        this.speedModifier = speedModifier;
        this.within = within;
    }

    @Override
    public boolean canUse() {
        if (target == null && !animal.isBaby() && animal.getAge() == 0 && animal.canFallInLove()) {
            if (animal instanceof AbstractHorse horse && (
                    horse.isVehicle() || horse.isPassenger() || !horse.isTamed() || horse.getHealth() < horse.getMaxHealth()
            )) return false;
            if (animal instanceof TamableAnimal tamableAnimal && !tamableAnimal.isTame()) return false;
            List<ItemEntity> nearbyFoods = animal.level().getEntitiesOfClass(
                    ItemEntity.class,
                    animal.getBoundingBox().inflate(16, 4, 16),
                    itemEntity -> animal.isFood(itemEntity.getItem())
            );
            if (!nearbyFoods.isEmpty()) {
                target = nearbyFoods.getFirst();
                if (nearbyFoods.size() == 1) return true;
                for (int i = 1; i < nearbyFoods.size(); i++) {
                    if (animal.distanceToSqr(nearbyFoods.get(i)) < animal.distanceToSqr(target)) {
                        target = nearbyFoods.get(i);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return !animal.getNavigation().isDone() && target.isAlive() && target.distanceToSqr(animal) < within * within;
    }

    @Override
    public void start() {
        animal.setCanPickUpLoot(true);
        animal.getNavigation().moveTo(target, speedModifier);
    }

    @Override
    public void stop() {
        target = null;
        animal.setCanPickUpLoot(false);
    }
}
