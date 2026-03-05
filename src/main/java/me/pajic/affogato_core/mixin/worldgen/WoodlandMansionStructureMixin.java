package me.pajic.affogato_core.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.StructureGenUtil;
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
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.structures.WoodlandMansionStructure;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WoodlandMansionStructure.class)
public class WoodlandMansionStructureMixin {

    @WrapMethod(method = "afterPlace")
    private void addChestsToRandomLocations(
            WorldGenLevel worldGenLevel,
            StructureManager structureManager,
            ChunkGenerator chunkGenerator,
            RandomSource randomSource,
            BoundingBox boundingBox,
            ChunkPos chunkPos,
            PiecesContainer piecesContainer,
            Operation<Void> original
    ) {
        original.call(worldGenLevel, structureManager, chunkGenerator, randomSource, boundingBox, chunkPos, piecesContainer);
        if (Main.CONFIG.worldgen.addRandomChestsToMansions.get()) {
            StructureGenUtil.placeChestsAtRandomLocations(
                    boundingBox,
                    worldGenLevel,
                    randomSource,
                    (state, pos) -> state.is(Blocks.BIRCH_PLANKS) || state.is(Blocks.OAK_PLANKS) || state.is(Blocks.BOOKSHELF),
                    state -> state.is(BlockTags.AIR),
                    Main.CONFIG.worldgen.mansionRandomChestChance.get(),
                    ResourceKey.create(Registries.LOOT_TABLE, Main.id("chests/woodland_mansion")),
                    62
            );
        }
    }
}
