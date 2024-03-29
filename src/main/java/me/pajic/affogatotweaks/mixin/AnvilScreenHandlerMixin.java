package me.pajic.affogatotweaks.mixin;

import com.nhoryzon.mc.farmersdelight.item.KnifeItem;
import de.dafuqs.chalk.items.ChalkItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Unique private ItemStack itemStack;

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void getItemStack(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3) {
        this.itemStack = itemStack2;
    }

    @ModifyArg(method = "updateResult", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), index = 1)
    private int modifyRequiredRepairUnitAmount(int a) {
        Item item = itemStack.getItem();
        int divideBy = 4;

        if (item instanceof ArmorItem) {
            if (((ArmorItem) item).getSlotType() == EquipmentSlot.HEAD) divideBy = 5;
            else if (((ArmorItem) item).getSlotType() == EquipmentSlot.CHEST) divideBy = 8;
            else if (((ArmorItem) item).getSlotType() == EquipmentSlot.LEGS) divideBy = 7;
        }
        else if (item instanceof ShovelItem || item instanceof KnifeItem || item instanceof FlintAndSteelItem || item instanceof BrushItem) {
            divideBy = 1;
        }
        else if (item instanceof SwordItem || item instanceof HoeItem || item instanceof ShearsItem || item instanceof FishingRodItem || item instanceof CrossbowItem || item instanceof ElytraItem || item instanceof ChalkItem) {
            divideBy = 2;
        }
        else if (item instanceof AxeItem || item instanceof PickaxeItem || item instanceof BowItem || item instanceof TridentItem) {
            divideBy = 3;
        }
        else if (item instanceof ShieldItem) {
            divideBy = 6;
        }

        return itemStack.getMaxDamage() / divideBy;
    }
}
