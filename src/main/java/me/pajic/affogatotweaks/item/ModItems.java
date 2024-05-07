package me.pajic.affogatotweaks.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import java.util.List;

public class ModItems {

    // Copper nugget
    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings());

    // Enchantment upgrade smithing template
    public static final Item ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE = new SmithingTemplateGlintItem(
            Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("affogatotweaks.smithing_template.enchantment_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE),
            Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("affogatotweaks.smithing_template.enchantment_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE),
            Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("affogatotweaks.enchantment_upgrade"))).withStyle(ChatFormatting.GRAY),
            Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("affogatotweaks.smithing_template.enchantment_upgrade.base_slot_description"))),
            Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("affogatotweaks.smithing_template.enchantment_upgrade.additions_slot_description"))),
            List.of(new ResourceLocation("affogatotweaks","item/empty_slot_enchanted_book")),
            List.of(new ResourceLocation("item/empty_slot_diamond"))
    );
}
