package me.pajic.affogato_core.mixin.integration.reliable_gliders;

import com.evandev.reliable_gliders.registry.ModItems;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.pajic.affogato_core.Main;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@IfModLoaded("reliable_gliders")
@Mixin(ModItems.class)
public class ModItemsMixin {

    @ModifyExpressionValue(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private static Item.Properties modifyGliderComponents(Item.Properties original) {
        Item.Properties props = original.durability(Main.CONFIG.misc.reliableGliderDurability.get());
        return Main.CONFIG.misc.repairableReliableGlider.get() ? props.repairable(Items.PHANTOM_MEMBRANE) : props;
    }
}
