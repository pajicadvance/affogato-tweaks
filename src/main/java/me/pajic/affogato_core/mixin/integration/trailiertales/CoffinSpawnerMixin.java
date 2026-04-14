package me.pajic.affogato_core.mixin.integration.trailiertales;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.pajic.affogato_core.Main;
import net.frozenblock.trailiertales.block.entity.coffin.CoffinSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@IfModLoaded("trailiertales")
@Mixin(CoffinSpawner.class)
public class CoffinSpawnerMixin {

    @ModifyReturnValue(
            method = "getRequiredPlayerRange",
            at = @At("RETURN")
    )
    private int modifyActivationRange(int original) {
        return Main.CONFIG.misc.coffinActivationRange.get();
    }
}
