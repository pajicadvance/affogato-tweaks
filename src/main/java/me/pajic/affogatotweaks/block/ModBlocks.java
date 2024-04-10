package me.pajic.affogatotweaks.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {

    // Copper lanterns
    public static final Block COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.UNAFFECTED,
            FabricBlockSettings.create().mapColor(MapColor.ORANGE).solid().requiresTool().strength(3.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 14).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block EXPOSED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.EXPOSED,
            FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).solid().requiresTool().strength(3.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 12).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block WEATHERED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.WEATHERED,
            FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).solid().requiresTool().strength(3.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 10).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block OXIDIZED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.OXIDIZED,
            FabricBlockSettings.create().mapColor(MapColor.TEAL).solid().requiresTool().strength(3.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 8).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );

    // Waxed variants
    public static final Block WAXED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(COPPER_LANTERN));
    public static final Block WAXED_EXPOSED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(EXPOSED_COPPER_LANTERN));
    public static final Block WAXED_WEATHERED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(WEATHERED_COPPER_LANTERN));
    public static final Block WAXED_OXIDIZED_COPPER_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(OXIDIZED_COPPER_LANTERN));
}
