package me.pajic.affogatotweaks;

import me.pajic.affogatotweaks.util.LootTableModifier;
import net.fabricmc.api.ModInitializer;

public class AffogatoTweaks implements ModInitializer {
    @Override
    public void onInitialize() {
        LootTableModifier.updateLootTables();
    }
}
