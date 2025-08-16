package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetherFortressStructure.class)
public class NetherFortressStructureMixin {

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/EntityType;SKELETON:Lnet/minecraft/world/entity/EntityType;"
            )
    )
    private static EntityType<?> replaceType(EntityType<?> original) {
        return EntityType.WITHER_SKELETON;
    }
}
