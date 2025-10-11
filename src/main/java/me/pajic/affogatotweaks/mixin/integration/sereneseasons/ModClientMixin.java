package me.pajic.affogatotweaks.mixin.integration.sereneseasons;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import glitchcore.event.client.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import sereneseasons.init.ModClient;

@IfModLoaded("sereneseasons")
@Mixin(value = ModClient.class, remap = false)
public class ModClientMixin {

    @WrapMethod(method = "onItemTooltip")
    private static void noCalendarTooltip(ItemTooltipEvent event, Operation<Void> original) {}
}
