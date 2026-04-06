package me.pajic.affogato_core.config;

import me.fzzyhmstrs.fzzy_config.annotations.Version;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import me.pajic.affogato_core.ClientMain;

@Version(version = 1)
public class ModClientConfig extends Config {
    public ModClientConfig() {
        super(ClientMain.CONFIG_RL);
    }

    public ValidatedBoolean raiseHotbar = new ValidatedBoolean();
    public ValidatedInt hotbarRaisePixels = new ValidatedInt(2, Integer.MAX_VALUE, 1);
    public ValidatedBoolean disableBetterGrassLayerFeature = new ValidatedBoolean();
}
