package me.pajic.affogatotweaks.block;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.particle.ModParticles;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    public static final Block COPPER_TORCH = Registry.register(
            BuiltInRegistries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "copper_torch"),
            new TorchBlock(ModParticles.COPPER_FLAME, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(blockState -> MiscValues.COPPER_TORCH_LIGHT_LEVEL)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final Block COPPER_WALL_TORCH = Registry.register(
            BuiltInRegistries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "copper_wall_torch"),
            new WallTorchBlock(ModParticles.COPPER_FLAME, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(blockState -> MiscValues.COPPER_TORCH_LIGHT_LEVEL)
                    .sound(SoundType.WOOD)
                    .dropsLike(COPPER_TORCH)
                    .pushReaction(PushReaction.DESTROY)
            )
    );

    public static void init() {}
}
