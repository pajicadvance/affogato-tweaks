package me.pajic.affogatotweaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AffogatoTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // Set copper lantern render layer to cutout (prevents transparency issues)
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AffogatoTweaks.OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
    }
}
