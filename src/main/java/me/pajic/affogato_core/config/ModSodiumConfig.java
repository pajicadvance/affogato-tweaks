package me.pajic.affogato_core.config;

import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.ConfigState;
import net.caffeinemc.mods.sodium.api.config.option.OptionFlag;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

public class ModSodiumConfig implements ConfigEntryPoint {

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void registerConfigLate(ConfigBuilder builder) {
        builder.registerOwnModOptions()
                .setName("Affogato")
                .setVersion(FabricLoader.getInstance().getModContainer("affogato_core").get().getMetadata().getVersion().getFriendlyString().substring(0, 4))
                .setColorTheme(builder.createColorTheme().setBaseThemeRGB(0xdaad93))
                .setNonTintedIcon(Identifier.parse("affogato_core:textures/config_icon.png"))
                .addPage(builder.createOptionPage()
                        .setName(Component.translatable("config.affogato.settings"))
                        .addOption(builder.createIntegerOption(Identifier.parse("affogato_core:current_dimension_brightness"))
                                .setName(Component.translatable("config.affogato.currentDimensionBrightness"))
                                .setTooltip(Component.translatable("config.affogato.currentDimensionBrightness.tooltip"))
                                .setRange(-1, 100, 1)
                                .setDefaultValue(50)
                                .setValueFormatter(i -> {
                                    if (i == -1) return Component.translatable("config.affogato.brightnessNoOverride");
                                    if (i == 0) return Component.translatable("options.gamma.min");
                                    if (i == 50) return Component.translatable("options.gamma.default");
                                    if (i == 100) return Component.translatable("options.gamma.max");
                                    return Component.literal(i + "%");
                                })
                                .setEnabledProvider(_ -> getCurrentBrightness() != -2, ConfigState.UPDATE_ON_REBUILD)
                                .setBinding(ModSodiumConfig::saveCurrentBrightness, ModSodiumConfig::getCurrentBrightness)
                                .setStorageHandler(() -> ModClientConfigHolder.options().writeChanges())
                        )
                        .addOption(builder.createIntegerOption(Identifier.parse("affogato_core:raise_hotbar"))
                                .setName(Component.translatable("config.affogato.raiseHotbar"))
                                .setTooltip(Component.translatable("config.affogato.raiseHotbar.tooltip"))
                                .setRange(0, 16, 1)
                                .setDefaultValue(0)
                                .setValueFormatter(i -> {
                                    if (i == 0) return Component.translatable("config.affogato.noRaiseHotbar");
                                    return Component.translatable("config.affogato.raiseHotbar.value", i);
                                })
                                .setBinding(integer -> ModClientConfigHolder.options().raiseHotbarPixels = integer, () -> ModClientConfigHolder.options().raiseHotbarPixels)
                                .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                                .setStorageHandler(() -> ModClientConfigHolder.options().writeChanges())
                        )
                        .addOption(builder.createBooleanOption(Identifier.parse("affogato_core:disable_lbg_layers"))
                                .setName(Component.translatable("config.affogato.disableLBGLayers"))
                                .setTooltip(Component.translatable("config.affogato.disableLBGLayers.tooltip"))
                                .setDefaultValue(false)
                                .setBinding(bl -> ModClientConfigHolder.options().disableLBGLayers = bl, () -> ModClientConfigHolder.options().disableLBGLayers)
                                .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                                .setStorageHandler(() -> ModClientConfigHolder.options().writeChanges())
                        )
                );
    }

    private static int getCurrentBrightness() {
        return ModClientConfigHolder.options().getOrCreateDimensionBrightness(getCurrentDimensionId());
    }

    private static void saveCurrentBrightness(int brightness) {
        ModClientConfigHolder.options().saveDimensionBrightness(getCurrentDimensionId(), brightness);
    }

    private @Nullable static Identifier getCurrentDimensionId() {
        ClientLevel level = Minecraft.getInstance().level;
        return level != null ? level.dimension().identifier() : null;
    }
}
