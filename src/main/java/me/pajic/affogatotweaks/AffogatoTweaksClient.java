package me.pajic.affogatotweaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

import static me.pajic.affogatotweaks.block.ModBlocks.*;

public class AffogatoTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // Set copper lantern render layer to cutout (prevents transparency issues)
        BlockRenderLayerMap.INSTANCE.putBlock(COPPER_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EXPOSED_COPPER_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WEATHERED_COPPER_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OXIDIZED_COPPER_LANTERN, RenderType.cutout());
    }
}
