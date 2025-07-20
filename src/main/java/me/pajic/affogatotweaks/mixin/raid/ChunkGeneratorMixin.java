package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import me.pajic.affogatotweaks.raid.ChunkGeneratorAccess;
import me.pajic.affogatotweaks.raid.ServerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
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
        Optional<ResourceKey<Structure>> opt = manager.registryAccess().registryOrThrow(Registries.STRUCTURE).getResourceKey(structure);
        if (opt.isPresent()) if (opt.get().location().equals(BuiltinStructures.PILLAGER_OUTPOST.location())) {
            HolderSet<Structure> outpost = HolderSet.direct(serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolderOrThrow(BuiltinStructures.PILLAGER_OUTPOST));
            Pair<BlockPos, Holder<Structure>> pair = serverLevel.getChunkSource().getGenerator().findNearestMapStructure(serverLevel, outpost, pos, 1, false);
            return pair != null && !((ServerLevelAccess) serverLevel).affogatotweaks$getClearedOutposts().isOutpostCleared(pair.getFirst());
        }
        return original;
    }

    @Override
    public void affogatotweaks$setServerLevel(ServerLevel serverLevel) {
        this.serverLevel = serverLevel;
    }
}
