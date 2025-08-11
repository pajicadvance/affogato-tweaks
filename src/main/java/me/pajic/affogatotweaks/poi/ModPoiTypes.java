package me.pajic.affogatotweaks.poi;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public class ModPoiTypes {
    public static final PoiType LIGHTNING_ROD_VARIANTS = PointOfInterestHelper.register(
            Main.withModNamespace("lightning_rod_variants"),
            0, 1,
            ModBlocks.EXPOSED_LIGHTNING_ROD,
            ModBlocks.WEATHERED_LIGHTNING_ROD,
            ModBlocks.OXIDIZED_LIGHTNING_ROD,
            ModBlocks.WAXED_LIGHTNING_ROD,
            ModBlocks.WAXED_EXPOSED_LIGHTNING_ROD,
            ModBlocks.WAXED_WEATHERED_LIGHTNING_ROD,
            ModBlocks.WAXED_OXIDIZED_LIGHTNING_ROD
    );

    public static void init() {}
}
