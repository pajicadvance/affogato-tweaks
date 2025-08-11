package me.pajic.affogatotweaks;

import me.pajic.affogatotweaks.block.ModBlocks;
import me.pajic.affogatotweaks.mixson.MixsonClientInitializer;
import me.pajic.affogatotweaks.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.RenderType;

public class ClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MixsonClientInitializer.init();
        ParticleFactoryRegistry.getInstance().register(ModParticles.COPPER_FLAME, FlameParticle.Provider::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH,
                ModBlocks.EXPOSED_LIGHTNING_ROD, ModBlocks.WEATHERED_LIGHTNING_ROD, ModBlocks.OXIDIZED_LIGHTNING_ROD,
                ModBlocks.WAXED_LIGHTNING_ROD, ModBlocks.WAXED_EXPOSED_LIGHTNING_ROD, ModBlocks.WAXED_WEATHERED_LIGHTNING_ROD, ModBlocks.WAXED_OXIDIZED_LIGHTNING_ROD
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(),
                ModBlocks.COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS, ModBlocks.OXIDIZED_COPPER_BARS,
                ModBlocks.WAXED_COPPER_BARS, ModBlocks.WAXED_EXPOSED_COPPER_BARS, ModBlocks.WAXED_WEATHERED_COPPER_BARS, ModBlocks.WAXED_OXIDIZED_COPPER_BARS,
                ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN,
                ModBlocks.WAXED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN
        );
    }
}
