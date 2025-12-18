package me.pajic.affogato_core;

import net.fabricmc.loader.api.FabricLoader;

public class CompatFlags {
    public static final boolean FD_LOADED = FabricLoader.getInstance().isModLoaded("farmersdelight");
    public static final boolean ITEMSWAPPER_LOADED = FabricLoader.getInstance().isModLoaded("itemswapper");
}
