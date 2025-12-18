package me.pajic.affogato_core;

import me.pajic.affogato_core.mixson.MixsonClientInitializer;
import net.fabricmc.api.ClientModInitializer;

public class ClientMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MixsonClientInitializer.init();
    }
}
