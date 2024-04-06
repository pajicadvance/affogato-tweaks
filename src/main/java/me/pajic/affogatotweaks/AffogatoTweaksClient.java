package me.pajic.affogatotweaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static me.pajic.affogatotweaks.block.ModBlocks.*;

public class AffogatoTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // Set copper lantern render layer to cutout (prevents transparency issues)
        BlockRenderLayerMap.INSTANCE.putBlock(COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
    }
}
