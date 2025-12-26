package me.pajic.affogato_core.item;

import me.pajic.affogato_core.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

public class FlintToolItem extends Item {

    public FlintToolItem(Properties properties, String name) {
        super(properties.setId(ResourceKey.create(Registries.ITEM, Main.id(name))));
    }

    @Override
    public boolean isEnabled(@NonNull FeatureFlagSet featureFlagSet) {
        return Main.CONFIG.features.affogatoEarlyGameChanges.get();
    }
}
