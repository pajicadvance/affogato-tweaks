package me.pajic.affogato_core.config;

import me.pajic.affogato_core.ClientMain;
import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.ConfigState;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class SodiumConfig implements ConfigEntryPoint {

    @Override
    public void registerConfigLate(ConfigBuilder builder) {
        builder.registerOwnModOptions()
                .registerOptionOverlay(
                        Identifier.parse("sodium:general.gamma"),
                        builder.createIntegerOption(Identifier.parse("sodium:general.gamma"))
                                .setEnabledProvider(_ -> !isDimensionBrightnessControlledByMod(), ConfigState.UPDATE_ON_REBUILD)
                                .setTooltip(_ -> isDimensionBrightnessControlledByMod() ?
                                        Component.translatable("sodium.options.affogato.control.tooltip") :
                                        Component.translatable("sodium.options.brightness.tooltip"))
                );
    }

    private static boolean isDimensionBrightnessControlledByMod() {
        ClientLevel level = Minecraft.getInstance().level;
        LocalPlayer player = Minecraft.getInstance().player;
        return level != null && player != null && ClientMain.CONFIG.perDimensionBrightness.containsKey(level.dimension().identifier());
    }
}
