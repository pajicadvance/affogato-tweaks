package me.pajic.affogato_core.config;

import me.fzzyhmstrs.fzzy_config.annotations.NonSync;
import me.fzzyhmstrs.fzzy_config.annotations.Version;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedMap;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import me.pajic.affogato_core.ClientMain;
import net.minecraft.resources.Identifier;

import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
@Version(version = 1)
public class ModClientConfig extends Config {
    public ModClientConfig() {
        super(ClientMain.CONFIG_RL);
    }

    public ValidatedBoolean raiseHotbar = new ValidatedBoolean();
    public ValidatedInt hotbarRaisePixels = new ValidatedInt(2, Integer.MAX_VALUE, 1);
    public ValidatedBoolean disableBetterGrassLayerFeature = new ValidatedBoolean();
    @NonSync public ValidatedMap<Identifier, Integer> perDimensionBrightness = (new ValidatedMap.Builder())
            .keyHandler(new ValidatedIdentifier(Identifier.withDefaultNamespace("overworld")))
            .valueHandler(new ValidatedInt(25, 100, 0))
            .defaults(Map.of(
                    Identifier.withDefaultNamespace("overworld"), 25,
                    Identifier.withDefaultNamespace("the_nether"), 100,
                    Identifier.withDefaultNamespace("the_end"), 0
            ))
            .build();
}
