package me.pajic.affogatotweaks.mixin.integration;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import draylar.inmis.config.BackpackInfo;
import draylar.inmis.config.InmisClothConfig;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.List;

@IfModLoaded("inmis")
@Mixin(InmisClothConfig.class)
public class InmisClothConfigMixin {

    @ModifyExpressionValue(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Arrays;asList([Ljava/lang/Object;)Ljava/util/List;"
            )
    )
    private List<BackpackInfo> modifyBackpackInfo(List<BackpackInfo> original) {
        return Arrays.asList(
                BackpackInfo.of("frayed", 7, 1, false, SoundEvents.ARMOR_EQUIP_LEATHER, true),
                BackpackInfo.of("plated", 7, 2, false, SoundEvents.ARMOR_EQUIP_IRON),
                BackpackInfo.of("gilded", 7, 3, false, SoundEvents.ARMOR_EQUIP_GOLD),
                BackpackInfo.of("bejeweled", 7, 4, false, SoundEvents.ARMOR_EQUIP_DIAMOND),
                BackpackInfo.of("blazing", 7, 5, true, SoundEvents.ARMOR_EQUIP_NETHERITE)
        );
    }
}
