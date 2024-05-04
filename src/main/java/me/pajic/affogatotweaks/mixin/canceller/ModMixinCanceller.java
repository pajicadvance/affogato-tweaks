package me.pajic.affogatotweaks.mixin.canceller;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class ModMixinCanceller implements MixinCanceller {

    private static final List<String> CANCELLED_MIXINS = List.of(
            "svenhjol.charm.mixin.extractable_enchantments.GrindstoneScreenMixin",
            "svenhjol.charmony.mixin.event.grindstone.GrindstoneMenuMixin",
            "svenhjol.charmony.mixin.event.grindstone.GrindstoneMenuInputMixin",
            "svenhjol.charmony.mixin.event.grindstone.GrindstoneMenuOutputMixin"
    );

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return CANCELLED_MIXINS.contains(mixinClassName);
    }
}
