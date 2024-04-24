package me.pajic.affogatotweaks.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

    // Copper lanterns
    public static final Block COPPER_LANTERN = new CopperLanternBlock(
            WeatheringCopper.WeatherState.UNAFFECTED,
            FabricBlockSettings.create().mapColor(MapColor.COLOR_ORANGE).forceSolidOn().requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.LANTERN).lightLevel(state -> 14).noOcclusion().pushReaction(PushReaction.DESTROY)
    );
    public static final Block EXPOSED_COPPER_LANTERN = new CopperLanternBlock(
            WeatheringCopper.WeatherState.EXPOSED,
            FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).forceSolidOn().requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.LANTERN).lightLevel(state -> 12).noOcclusion().pushReaction(PushReaction.DESTROY)
    );

    public static final Block WEATHERED_COPPER_LANTERN = new CopperLanternBlock(
            WeatheringCopper.WeatherState.WEATHERED,
            FabricBlockSettings.create().mapColor(MapColor.WARPED_STEM).forceSolidOn().requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.LANTERN).lightLevel(state -> 10).noOcclusion().pushReaction(PushReaction.DESTROY)
    );
    public static final Block OXIDIZED_COPPER_LANTERN = new CopperLanternBlock(
            WeatheringCopper.WeatherState.OXIDIZED,
            FabricBlockSettings.create().mapColor(MapColor.WARPED_NYLIUM).forceSolidOn().requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.LANTERN).lightLevel(state -> 8).noOcclusion().pushReaction(PushReaction.DESTROY)
    );

    // Waxed variants
    public static final Block WAXED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(COPPER_LANTERN));
    public static final Block WAXED_EXPOSED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(EXPOSED_COPPER_LANTERN));
    public static final Block WAXED_WEATHERED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(WEATHERED_COPPER_LANTERN));
    public static final Block WAXED_OXIDIZED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(OXIDIZED_COPPER_LANTERN));
}
