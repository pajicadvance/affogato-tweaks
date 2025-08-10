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
    public static final Block COPPER_CHAIN = registerModBlock("copper_chain",
            new CopperChainBlock(
                    WeatheringCopper.WeatherState.UNAFFECTED,
                    BlockBehaviour.Properties.of()
                            .mapColor(Blocks.COPPER_BLOCK.defaultMapColor())
                            .forceSolidOn()
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.CHAIN)
                            .noOcclusion()
            )
    );
    public static final Block EXPOSED_COPPER_CHAIN = registerModBlock("exposed_copper_chain",
            new CopperChainBlock(
                    WeatheringCopper.WeatherState.EXPOSED,
                    BlockBehaviour.Properties.ofFullCopy(COPPER_CHAIN)
            )
    );
    public static final Block WEATHERED_COPPER_CHAIN = registerModBlock("weathered_copper_chain",
            new CopperChainBlock(
                    WeatheringCopper.WeatherState.WEATHERED,
                    BlockBehaviour.Properties.ofFullCopy(COPPER_CHAIN)
            )
    );
    public static final Block OXIDIZED_COPPER_CHAIN = registerModBlock("oxidized_copper_chain",
            new CopperChainBlock(
                    WeatheringCopper.WeatherState.OXIDIZED,
                    BlockBehaviour.Properties.ofFullCopy(COPPER_CHAIN)
            )
    );
    public static final Block WAXED_COPPER_CHAIN = registerModBlock("waxed_copper_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_CHAIN))
    );
    public static final Block WAXED_EXPOSED_COPPER_CHAIN = registerModBlock("waxed_exposed_copper_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(EXPOSED_COPPER_CHAIN))
    );
    public static final Block WAXED_WEATHERED_COPPER_CHAIN = registerModBlock("waxed_weathered_copper_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(WEATHERED_COPPER_CHAIN))
    );
    public static final Block WAXED_OXIDIZED_COPPER_CHAIN = registerModBlock("waxed_oxidized_copper_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(OXIDIZED_COPPER_CHAIN))
    );

    private static Block registerModBlock(String name, Block block) {
        return Registry.register(
                BuiltInRegistries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, name),
                block
        );
    }

    private static void addOxidizableBlockSet(
            Block base, Block exposed, Block weathered, Block oxidized,
            Block waxedBase, Block waxedExposed, Block waxedWeathered, Block waxedOxidized
    ) {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(base, exposed);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(exposed, weathered);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(weathered, oxidized);
        OxidizableBlocksRegistry.registerWaxableBlockPair(base, waxedBase);
        OxidizableBlocksRegistry.registerWaxableBlockPair(exposed, waxedExposed);
        OxidizableBlocksRegistry.registerWaxableBlockPair(weathered, waxedWeathered);
        OxidizableBlocksRegistry.registerWaxableBlockPair(oxidized, waxedOxidized);
    }

    public static void init() {
        addOxidizableBlockSet(
                COPPER_BARS, EXPOSED_COPPER_BARS, WEATHERED_COPPER_BARS, OXIDIZED_COPPER_BARS,
                WAXED_COPPER_BARS, WAXED_EXPOSED_COPPER_BARS, WAXED_WEATHERED_COPPER_BARS, WAXED_OXIDIZED_COPPER_BARS
        );
        addOxidizableBlockSet(
                COPPER_CHAIN, EXPOSED_COPPER_CHAIN, WEATHERED_COPPER_CHAIN, OXIDIZED_COPPER_CHAIN,
                WAXED_COPPER_CHAIN, WAXED_EXPOSED_COPPER_CHAIN, WAXED_WEATHERED_COPPER_CHAIN, WAXED_OXIDIZED_COPPER_CHAIN
        );
    }
}
