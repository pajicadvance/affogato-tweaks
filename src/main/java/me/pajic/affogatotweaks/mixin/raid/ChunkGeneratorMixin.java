package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.raid.ChunkGeneratorAccess;
import me.pajic.affogatotweaks.raid.ServerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin implements ChunkGeneratorAccess {
    @Unique private ServerLevel serverLevel;

    @ModifyExpressionValue(
            method = "getMobsAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/apache/commons/lang3/mutable/MutableBoolean;isTrue()Z"
            )
    )
    private boolean noPillagersIfOutpostCleared(boolean original, @Local Structure structure, @Local(argsOnly = true) StructureManager manager, @Local(argsOnly = true) BlockPos pos) {
        Optional<HolderSet.Named<Structure>> opt = manager.registryAccess().lookupOrThrow(Registries.STRUCTURE).get(Main.OUTPOSTS);
        if (opt.isPresent()) if (opt.get().contains(Holder.direct(structure))) {
            BlockPos outpostPos = serverLevel.findNearestMapStructure(Main.OUTPOSTS, pos, 1, false);
            return outpostPos != null && !((ServerLevelAccess) serverLevel).affogatotweaks$getClearedOutposts().isOutpostCleared(outpostPos);
        }
        return original;
    }

    @Override
    public void affogatotweaks$setServerLevel(ServerLevel serverLevel) {
        this.serverLevel = serverLevel;
    }
}
