package me.pajic.affogato_core.mixin.stats;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
    @Definition(id = "BowItem", type = BowItem.class)
    @Definition(id = "Properties", type = Item.Properties.class)
    @Definition(id = "durability", method = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "enchantable", method = "Lnet/minecraft/world/item/Item$Properties;enchantable(I)Lnet/minecraft/world/item/Item$Properties;")
    @Expression("registerItem(?, BowItem::new, new Properties().durability(@(?)).enchantable(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setBowDurability(int original) {
        return Main.CONFIG.durabilities.bow.get();
    }

    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
    @Definition(id = "Properties", type = Item.Properties.class)
    @Definition(id = "durability", method = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "rarity", method = "Lnet/minecraft/world/item/Item$Properties;rarity(Lnet/minecraft/world/item/Rarity;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "component", method = "Lnet/minecraft/world/item/Item$Properties;component(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "repairable", method = "Lnet/minecraft/world/item/Item$Properties;repairable(Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/item/Item$Properties;")
    @Expression("registerItem(?, new Properties().durability(@(?)).rarity(?).component(?, ?).component(?, ?).repairable(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setElytraDurability(int original) {
        return Main.CONFIG.durabilities.elytra.get();
    }

    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
    @Definition(id = "ShearsItem", type = ShearsItem.class)
    @Definition(id = "Properties", type = Item.Properties.class)
    @Definition(id = "durability", method = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "component", method = "Lnet/minecraft/world/item/Item$Properties;component(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;")
    @Expression("registerItem(?, ShearsItem::new, new Properties().durability(@(?)).component(?, ?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setShearsDurability(int original) {
        return Main.CONFIG.durabilities.shears.get();
    }

    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
    @Definition(id = "ShieldItem", type = ShieldItem.class)
    @Definition(id = "Properties", type = Item.Properties.class)
    @Definition(id = "durability", method = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "component", method = "Lnet/minecraft/world/item/Item$Properties;component(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "repairable", method = "Lnet/minecraft/world/item/Item$Properties;repairable(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "equippableUnswappable", method = "Lnet/minecraft/world/item/Item$Properties;equippableUnswappable(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/Item$Properties;")
    @Expression("registerItem(?, ShieldItem::new, new Properties().durability(@(?)).component(?, ?).repairable(?).equippableUnswappable(?).component(?, ?).component(?, ?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setShieldDurability(int original) {
        return Main.CONFIG.durabilities.shield.get();
    }

    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
    @Definition(id = "CrossbowItem", type = CrossbowItem.class)
    @Definition(id = "Properties", type = Item.Properties.class)
    @Definition(id = "stacksTo", method = "Lnet/minecraft/world/item/Item$Properties;stacksTo(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "durability", method = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "component", method = "Lnet/minecraft/world/item/Item$Properties;component(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "enchantable", method = "Lnet/minecraft/world/item/Item$Properties;enchantable(I)Lnet/minecraft/world/item/Item$Properties;")
    @Expression("registerItem(?, CrossbowItem::new, new Properties().stacksTo(?).durability(@(?)).component(?, ?).enchantable(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setCrossbowDurability(int original) {
        return Main.CONFIG.durabilities.crossbow.get();
    }

    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;")
    @Definition(id = "TridentItem", type = TridentItem.class)
    @Definition(id = "Properties", type = Item.Properties.class)
    @Definition(id = "rarity", method = "Lnet/minecraft/world/item/Item$Properties;rarity(Lnet/minecraft/world/item/Rarity;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "durability", method = "Lnet/minecraft/world/item/Item$Properties;durability(I)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "attributes", method = "Lnet/minecraft/world/item/Item$Properties;attributes(Lnet/minecraft/world/item/component/ItemAttributeModifiers;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "component", method = "Lnet/minecraft/world/item/Item$Properties;component(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;")
    @Definition(id = "enchantable", method = "Lnet/minecraft/world/item/Item$Properties;enchantable(I)Lnet/minecraft/world/item/Item$Properties;")
    @Expression("registerItem(?, TridentItem::new, new Properties().rarity(?).durability(@(?)).attributes(?).component(?, ?).enchantable(?).component(?, ?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setTridentDurability(int original) {
        return Main.CONFIG.durabilities.trident.get();
    }
}