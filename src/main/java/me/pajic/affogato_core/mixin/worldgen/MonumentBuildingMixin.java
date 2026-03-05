package me.pajic.affogato_core.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.StructureGenUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.OceanMonumentPieces;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OceanMonumentPieces.MonumentBuilding.class)
public class MonumentBuildingMixin {

    @WrapMethod(method = "postProcess")
    private void addChestsToRandomLocations(
            WorldGenLevel worldGenLevel,
            StructureManager structureManager,
            ChunkGenerator chunkGenerator,
            RandomSource randomSource,
            BoundingBox boundingBox,
            ChunkPos chunkPos,
            BlockPos blockPos,
            Operation<Void> original
    ) {
        original.call(worldGenLevel, structureManager, chunkGenerator, randomSource, boundingBox, chunkPos, blockPos);
        if (Main.CONFIG.worldgen.addRandomChestsToMonuments.get()) {
            StructureGenUtil.placeChestsAtRandomLocations(
                    boundingBox,
                    worldGenLevel,
                    randomSource,
                    (state, pos) -> state.is(Blocks.PRISMARINE) || state.is(Blocks.PRISMARINE_BRICKS),
                    state -> state.is(BlockTags.AIR) || state.is(Blocks.WATER),
                    Main.CONFIG.worldgen.monumentRandomChestChance.get(),
                    ResourceKey.create(Registries.LOOT_TABLE, Main.id("chests/ocean_monument")),
                    null
            );
        }
    }
}