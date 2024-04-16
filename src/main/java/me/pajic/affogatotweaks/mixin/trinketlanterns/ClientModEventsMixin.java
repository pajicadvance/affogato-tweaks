package me.pajic.affogatotweaks.mixin.trinketlanterns;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.akashiikun.trinketlanterns.client.ClientModEvents;
import io.github.akashiikun.trinketlanterns.trinket.renderer.LanternRenderer;
import me.pajic.affogatotweaks.block.ModBlocks;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientModEvents.class, remap = false)
public class ClientModEventsMixin {

    @Unique private static final String[] modLanterns = {
            "affogatotweaks:copper_lantern",
            "affogatotweaks:exposed_copper_lantern",
            "affogatotweaks:weathered_copper_lantern",
            "affogatotweaks:oxidized_copper_lantern",
            "affogatotweaks:waxed_copper_lantern",
            "affogatotweaks:waxed_exposed_copper_lantern",
            "affogatotweaks:waxed_weathered_copper_lantern",
            "affogatotweaks:waxed_oxidized_copper_lantern"
    };

    @Inject(method = "ClientSetup", at = @At("TAIL"))
    private static void registerModTrinketRenderers(CallbackInfo ci) {
        TrinketRendererRegistry.registerRenderer(ModBlocks.COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.EXPOSED_COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.WEATHERED_COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.OXIDIZED_COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.WAXED_COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN.asItem(), LanternRenderer::new);
        TrinketRendererRegistry.registerRenderer(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN.asItem(), LanternRenderer::new);
    }

    @SuppressWarnings("deprecation")
    @Inject(method = "modelRegistry", at = @At("TAIL"))
    private static void registerModModels(CallbackInfo ci) {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(resourceManager -> ((resourceId, context) -> {
            for (String var : modLanterns) {
                if (resourceId.toString().equals(var)) {
                    return context.loadModel(resourceId);
                }
            }
            return null;
        }));
    }
}
