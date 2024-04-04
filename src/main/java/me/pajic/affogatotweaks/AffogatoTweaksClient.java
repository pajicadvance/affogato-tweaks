package me.pajic.affogatotweaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class AffogatoTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        FabricLoader.getInstance().getModContainer("affogatotweaks").ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("affogatotweaks","affogatotweaks_renames"),
                        modContainer, ResourcePackActivationType.ALWAYS_ENABLED));

        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
    }
}
