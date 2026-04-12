package me.pajic.affogato_core.mixin.tutorial;

import me.pajic.affogato_core.Main;
import net.minecraft.client.gui.components.toasts.TutorialToast;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TutorialToast.Icons.class)
public enum TutorialToastIconsMixin {
    AFFOGATO_CORE_LEAVES(Main.id("toast/leaves")),
    AFFOGATO_CORE_GRAVEL(Main.id("toast/gravel")),
    AFFOGATO_CORE_FLINT_AXE(Main.id("toast/flint_axe"));

    @Shadow TutorialToastIconsMixin(Identifier sprite) {}
}
