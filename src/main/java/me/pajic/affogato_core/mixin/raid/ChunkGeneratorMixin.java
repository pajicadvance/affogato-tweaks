package me.pajic.affogato_core.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.raid.ChunkGeneratorAccess;
import me.pajic.affogato_core.raid.ServerLevelAccess;
import me.pajic.affogato_core.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
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
    private boolean noPillagersIfOutpostCleared(boolean original, @Local(name = "structure") Structure structure, @Local(argsOnly = true) StructureManager manager, @Local(argsOnly = true) BlockPos pos) {
        if (Main.CONFIG.features.raidRework.get()) {
            Registry<Structure> lookup = manager.registryAccess().lookupOrThrow(Registries.STRUCTURE);
            Optional<HolderSet.Named<Structure>> opt = lookup.get(ModTags.OUTPOSTS);
            Identifier id = lookup.getKey(structure);
            if (opt.isPresent() && id != null && opt.get().stream().anyMatch(holder -> holder.is(id))) {
                BlockPos outpostPos = serverLevel.findNearestMapStructure(ModTags.OUTPOSTS, pos, 1, false);
                return outpostPos != null && !((ServerLevelAccess) serverLevel).affogatotweaks$getClearedOutposts().isOutpostCleared(outpostPos);
            }
        }
        return original;
    }

    @Override
    public void affogatotweaks$setServerLevel(ServerLevel serverLevel) {
        this.serverLevel = serverLevel;
    }
}
