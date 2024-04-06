package me.pajic.affogatotweaks.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class ModItems {

    // Copper nugget
    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings());

    // Enchantment upgrade smithing template
    public static final Item ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE = new SmithingTemplateGlintItem(
            Text.translatable(Util.createTranslationKey("item", new Identifier("affogatotweaks.smithing_template.enchantment_upgrade.applies_to"))).formatted(Formatting.BLUE),
            Text.translatable(Util.createTranslationKey("item", new Identifier("affogatotweaks.smithing_template.enchantment_upgrade.ingredients"))).formatted(Formatting.BLUE),
            Text.translatable(Util.createTranslationKey("upgrade", new Identifier("affogatotweaks.enchantment_upgrade"))).formatted(Formatting.GRAY),
            Text.translatable(Util.createTranslationKey("item", new Identifier("affogatotweaks.smithing_template.enchantment_upgrade.base_slot_description"))),
            Text.translatable(Util.createTranslationKey("item", new Identifier("affogatotweaks.smithing_template.enchantment_upgrade.additions_slot_description"))),
            List.of(new Identifier("affogatotweaks","item/empty_slot_enchanted_book")),
            List.of(new Identifier("item/empty_slot_lapis_lazuli"))
    );
}
