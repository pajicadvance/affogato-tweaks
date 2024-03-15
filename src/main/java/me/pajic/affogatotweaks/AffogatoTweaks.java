package me.pajic.affogatotweaks;

import me.pajic.affogatotweaks.loot.LootTableModifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class AffogatoTweaks implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer("affogatotweaks").ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("affogatotweaks","affogatotweaks"),
                        modContainer, ResourcePackActivationType.ALWAYS_ENABLED));
        LootTableModifier.modifyLootTables();
    }
}
