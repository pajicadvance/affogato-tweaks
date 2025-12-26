package me.pajic.affogato_core.tag;

import me.pajic.affogato_core.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModTags {

    public static final TagKey<Structure> OUTPOSTS = TagKey.create(
            Registries.STRUCTURE,
            Main.id("outposts")
    );
    public static final TagKey<DamageType> NO_EAT_CANCEL = TagKey.create(
            Registries.DAMAGE_TYPE,
            Main.id("no_eat_cancel")
    );
    public static final TagKey<Item> FLINT_TOOL_MATERIALS = TagKey.create(
            Registries.ITEM,
            Main.id("flint_tool_materials")
    );
}
