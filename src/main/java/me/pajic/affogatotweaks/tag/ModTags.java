package me.pajic.affogatotweaks.tag;

import me.pajic.affogatotweaks.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModTags {
    public static final TagKey<Block> LIGHTNING_ROD_VARIANTS = TagKey.create(
            Registries.BLOCK,
            Main.withModNamespace("lightning_rod_variants")
    );
    public static final TagKey<Structure> OUTPOSTS = TagKey.create(
            Registries.STRUCTURE,
            Main.withModNamespace("outposts")
    );

    public static void init() {}
}
