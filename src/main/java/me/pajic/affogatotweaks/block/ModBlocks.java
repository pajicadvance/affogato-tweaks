package me.pajic.affogatotweaks.block;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.particle.ModParticles;
import me.pajic.affogatotweaks.values.MiscValues;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    public static final Block COPPER_TORCH = registerModBlock("copper_torch",
            new TorchBlock(ModParticles.COPPER_FLAME, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(blockState -> MiscValues.COPPER_TORCH_LIGHT_LEVEL)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final Block COPPER_WALL_TORCH = registerModBlock("copper_wall_torch",
            new WallTorchBlock(ModParticles.COPPER_FLAME, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(blockState -> MiscValues.COPPER_TORCH_LIGHT_LEVEL)
                    .sound(SoundType.WOOD)
                    .dropsLike(COPPER_TORCH)
                    .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final Block COPPER_BARS = registerModBlock("copper_bars",
            new CopperBarsBlock(
                    WeatheringCopper.WeatherState.UNAFFECTED,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.COPPER_BLOCK.defaultMapColor())
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.COPPER)
                            .noOcclusion()
            )
    );
    public static final Block EXPOSED_COPPER_BARS = registerModBlock("exposed_copper_bars",
            new CopperBarsBlock(
                    WeatheringCopper.WeatherState.EXPOSED,
                    BlockBehaviour.Properties.ofFullCopy(COPPER_BARS)
            )
    );
    public static final Block WEATHERED_COPPER_BARS = registerModBlock("weathered_copper_bars",
            new CopperBarsBlock(
                    WeatheringCopper.WeatherState.WEATHERED,
                    BlockBehaviour.Properties.ofFullCopy(COPPER_BARS)
            )
    );
    public static final Block OXIDIZED_COPPER_BARS = registerModBlock("oxidized_copper_bars",
            new CopperBarsBlock(
                    WeatheringCopper.WeatherState.OXIDIZED,
                    BlockBehaviour.Properties.ofFullCopy(COPPER_BARS)
            )
    );
    public static final Block WAXED_COPPER_BARS = registerModBlock("waxed_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_BARS))
    );
    public static final Block WAXED_EXPOSED_COPPER_BARS = registerModBlock("waxed_exposed_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(EXPOSED_COPPER_BARS))
    );
    public static final Block WAXED_WEATHERED_COPPER_BARS = registerModBlock("waxed_weathered_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(WEATHERED_COPPER_BARS))
    );
    public static final Block WAXED_OXIDIZED_COPPER_BARS = registerModBlock("waxed_oxidized_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(OXIDIZED_COPPER_BARS))
    );

    private static Block registerModBlock(String name, Block block) {
        return Registry.register(
                BuiltInRegistries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, name),
                block
        );
    }

    public static void init() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_BARS, EXPOSED_COPPER_BARS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_BARS, WEATHERED_COPPER_BARS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_BARS, OXIDIZED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_BARS, WAXED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_BARS, WAXED_EXPOSED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_BARS, WAXED_WEATHERED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_BARS, WAXED_OXIDIZED_COPPER_BARS);
    }
}
