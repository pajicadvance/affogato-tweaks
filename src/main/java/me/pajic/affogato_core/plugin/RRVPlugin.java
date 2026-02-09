package me.pajic.affogato_core.plugin;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import me.pajic.affogato_core.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.List;

public class RRVPlugin implements ReliableRecipeViewerClientPlugin {

    public static final List<String> TOOLS = List.of(
            "minecraft:stone_pickaxe",
            "minecraft:stone_shovel",
            "minecraft:stone_sword",
            "minecraft:stone_axe",
            "minecraft:stone_hoe",
            "minecraft:wooden_pickaxe",
            "minecraft:wooden_shovel",
            "minecraft:wooden_sword",
            "minecraft:wooden_axe",
            "minecraft:wooden_hoe"
    );

    @Override
    public void onIntegrationInitialize() {
        Main.CONFIG.hiddenItems.get().forEach(s ->
                BuiltInRegistries.ITEM.getOptional(Identifier.tryParse(s)).ifPresent(ItemView::excludeItem)
        );
        if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) TOOLS.forEach(s ->
                BuiltInRegistries.ITEM.getOptional(Identifier.tryParse(s)).ifPresent(ItemView::excludeItem)
        );
    }
}
