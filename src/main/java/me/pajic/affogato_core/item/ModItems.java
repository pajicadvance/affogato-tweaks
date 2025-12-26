package me.pajic.affogato_core.item;

import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.tag.ModTags;
import me.pajic.affogato_core.util.ToolMaterialId;
import me.pajic.affogato_core.util.ToolStatReplacement;
import me.pajic.affogato_core.util.ToolType;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;

public class ModItems {

    private static final ToolStatReplacement WOOD = Main.CONFIG.toolStats.stream().filter(tsr ->
            ToolMaterialId.equal(tsr.material.get(), ToolMaterial.WOOD) && tsr.type.get() == ToolType.ANY
    ).toList().getFirst();

    private static final ToolMaterial FLINT = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, WOOD.durability.get(), WOOD.miningSpeed.get(),
            0, 15, ModTags.FLINT_TOOL_MATERIALS
    );

    public static final Item FLINT_PICKAXE = registerFlintToolItem(
            "flint_pickaxe", new Item.Properties().pickaxe(FLINT, 1.0F, -2.8F)
    );
    public static final Item FLINT_AXE = registerFlintToolItem(
            "flint_axe", new Item.Properties().axe(FLINT, 6.0F, -3.2F)
    );
    public static final Item FLINT_SWORD = registerFlintToolItem(
            "flint_sword", new Item.Properties().sword(FLINT, 3.0F, -2.4F)
    );
    public static final Item FLINT_SHOVEL = registerFlintToolItem(
            "flint_shovel", new Item.Properties().shovel(FLINT, 1.5F, -3.0F)
    );
    public static final Item FLINT_HOE = registerFlintToolItem(
            "flint_hoe", new Item.Properties().hoe(FLINT, 0.0F, -3.0F)
    );

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
                entries.addBefore(Items.COPPER_SHOVEL, FLINT_SHOVEL, FLINT_PICKAXE, FLINT_AXE, FLINT_HOE)
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.addBefore(Items.COPPER_AXE, FLINT_AXE);
            entries.addBefore(Items.COPPER_SWORD, FLINT_SWORD);
        });
    }

    private static Item registerFlintToolItem(String name, Item.Properties properties) {
        return Registry.register(BuiltInRegistries.ITEM, Main.id(name), new FlintToolItem(properties, name));
    }
}
