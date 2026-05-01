package me.pajic.affogato_core.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.npc.CatSpawner;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CatSpawner.class)
public class CatSpawnerMixin {

    @ModifyExpressionValue(
            method = "spawnInVillage",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/ai/village/poi/PoiManager$Occupancy;IS_OCCUPIED:Lnet/minecraft/world/entity/ai/village/poi/PoiManager$Occupancy;",
                    opcode = Opcodes.GETSTATIC
            )
    )
    private PoiManager.Occupancy noOccupancyRequirement(PoiManager.Occupancy original) {
        return Main.CONFIG.features.villagerNuke.get() ? PoiManager.Occupancy.ANY : original;
    }
}
