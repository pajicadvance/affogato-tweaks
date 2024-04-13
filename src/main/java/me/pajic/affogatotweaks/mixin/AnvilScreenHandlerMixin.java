package me.pajic.affogatotweaks.mixin;

import de.dafuqs.chalk.common.items.ChalkItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vectorwing.farmersdelight.common.item.KnifeItem;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    @Unique private ItemStack itemStack;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    // Gets item stack from the first slot
    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void getItemStack(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3) {
        this.itemStack = itemStack2;
    }

    // Makes anvil repair respect material unit costs
    // Repair is 33% cheaper than shapeless repair
    @ModifyArg(method = "updateResult", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), index = 1)
    private int modifyRequiredRepairUnitAmount(int a) {
        Item item = itemStack.getItem();
        int divideBy = 4;

        if (item instanceof ArmorItem) {
            if (((ArmorItem) item).getSlotType() == EquipmentSlot.CHEST) divideBy = 6;
            else if (((ArmorItem) item).getSlotType() == EquipmentSlot.LEGS) divideBy = 5;
            else if (((ArmorItem) item).getSlotType() == EquipmentSlot.FEET) divideBy = 3;
        }
        else if (item instanceof ShovelItem || item instanceof KnifeItem || item instanceof FlintAndSteelItem || item instanceof BrushItem) {
            divideBy = 1;
        }
        else if (item instanceof SwordItem || item instanceof HoeItem || item instanceof ShearsItem || item instanceof FishingRodItem ||
                item instanceof CrossbowItem || item instanceof ElytraItem || item instanceof ChalkItem) {
            divideBy = 2;
        }
        else if (item instanceof AxeItem || item instanceof PickaxeItem || item instanceof BowItem || item instanceof TridentItem) {
            divideBy = 2;
        }

        return itemStack.getMaxDamage() / divideBy;
    }

    // Trigger Enchanter advancement and increment player stat when an enchantment is applied to an item for the first time
    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void triggerEnchanterAdvancement(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (!output.getStack(0).isOf(Items.ENCHANTED_BOOK) && !input.getStack(0).hasEnchantments()) {
            player.incrementStat(Stats.ENCHANT_ITEM);
            if (player instanceof ServerPlayerEntity) {
                Criteria.ENCHANTED_ITEM.trigger((ServerPlayerEntity) player, output.getStack(0), 1);
            }
        }
    }
}
