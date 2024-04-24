package me.pajic.affogatotweaks.mixin.charm;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.feature.improved_mineshafts.ImprovedMineshafts;

import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

@Mixin(value = ImprovedMineshafts.class, remap = false)
public class ImprovedMineshaftsMixin {

    @Shadow @Final private static List<ResourceLocation> MINECART_LOOT;

    // Removes the village chest loot tables from Charm's Improved Mineshafts feature
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private void modifyMinecartLoot(CallbackInfo ci) {
        ci.cancel();
        MINECART_LOOT.addAll(List.of(BuiltInLootTables.SIMPLE_DUNGEON, BuiltInLootTables.ABANDONED_MINESHAFT));
    }
}
