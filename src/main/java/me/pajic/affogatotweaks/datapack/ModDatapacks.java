package me.pajic.affogatotweaks.datapack;

import me.pajic.affogatotweaks.Main;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;

public class ModDatapacks {
    public static void init() {
        FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(
                        Main.withModNamespace("itemswap"),
                        modContainer,
                        Component.literal("ItemSwapper addon for Affogato"),
                        ResourcePackActivationType.ALWAYS_ENABLED
                )
        );
    }
}
