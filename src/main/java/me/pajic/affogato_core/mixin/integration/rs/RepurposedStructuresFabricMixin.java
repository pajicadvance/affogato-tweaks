package me.pajic.affogato_core.mixin.integration.rs;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import com.telepathicgrunt.repurposedstructures.RepurposedStructuresFabric;
import org.spongepowered.asm.mixin.Mixin;

@IfModLoaded("repurposed_structures")
@Mixin(RepurposedStructuresFabric.class)
public class RepurposedStructuresFabricMixin {

    @WrapMethod(method = "setupWanderingTrades")
    private static void dontMessWithTrades(Operation<Void> original) {}
}
