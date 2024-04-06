package me.pajic.affogatotweaks.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {

    // Copper lantern variants
    public static final CopperLanternBlock COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.UNAFFECTED,
            FabricBlockSettings.create().mapColor(MapColor.ORANGE).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 14).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final CopperLanternBlock EXPOSED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.EXPOSED,
            FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 11).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final CopperLanternBlock WEATHERED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.WEATHERED,
            FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 7).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final CopperLanternBlock OXIDIZED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.OXIDIZED,
            FabricBlockSettings.create().mapColor(MapColor.TEAL).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 3).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
}
