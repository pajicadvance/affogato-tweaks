package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityType.class)
public class EntityTypeMixin {

    @SuppressWarnings("unchecked")
    @ModifyArg(
            method = "create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType$EntityFactory;create(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            ),
            index = 0
    )
    private<T extends Entity> EntityType<T> replace(EntityType<T> entityType, @Local(argsOnly = true) Level level) {
        if (entityType == EntityType.SKELETON && level.dimension() == Level.NETHER) {
            return (EntityType<T>) EntityType.WITHER_SKELETON;
        }
        return entityType;
    }
}
