package me.pajic.affogatotweaks.mixin;

import de.dafuqs.chalk.common.items.ChalkItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vectorwing.farmersdelight.common.item.KnifeItem;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {

    @Unique private ItemStack itemStack;

    public AnvilMenuMixin(@Nullable MenuType<?> type, int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(type, syncId, playerInventory, context);
    }

    // Gets item stack from the first slot
    @Inject(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantments(Lnet/minecraft/world/item/ItemStack;)Ljava/util/Map;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void getItemStack(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3) {
        this.itemStack = itemStack2;
    }

    // Makes anvil repair respect material unit costs
    // Repair is 33% cheaper than shapeless repair
    @ModifyArg(method = "createResult", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), index = 1)
    private int modifyRequiredRepairUnitAmount(int a) {
        Item item = itemStack.getItem();
        int divideBy = 4;

        if (item instanceof ArmorItem) {
            if (((ArmorItem) item).getEquipmentSlot() == EquipmentSlot.CHEST) divideBy = 6;
            else if (((ArmorItem) item).getEquipmentSlot() == EquipmentSlot.LEGS) divideBy = 5;
            else if (((ArmorItem) item).getEquipmentSlot() == EquipmentSlot.FEET) divideBy = 3;
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
    @Inject(method = "onTake", at = @At("HEAD"))
    private void triggerEnchanterAdvancement(Player player, ItemStack stack, CallbackInfo ci) {
        if (!resultSlots.getItem(0).is(Items.ENCHANTED_BOOK) && !inputSlots.getItem(0).isEnchanted()) {
            player.awardStat(Stats.ENCHANT_ITEM);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ENCHANTED_ITEM.trigger((ServerPlayer) player, resultSlots.getItem(0), 1);
            }
        }
    }
}
