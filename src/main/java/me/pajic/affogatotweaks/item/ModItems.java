package me.pajic.affogatotweaks.item;

import me.emafire003.dev.custombrewrecipes.CustomBrewRecipeRegister;
import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.block.ModBlocks;
import me.pajic.affogatotweaks.values.ArmorBonusValues;
import me.pajic.affogatotweaks.values.ArmorDefenseValues;
import me.pajic.affogatotweaks.values.DurabilityValues;
import me.pajic.affogatotweaks.values.StatValues;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;

public class ModItems {
    public static final Item COPPER_NUGGET = registerModItem("copper_nugget",
            new Item(new Item.Properties())
    );
    public static final Item COPPER_TORCH = registerModItem("copper_torch",
            new StandingAndWallBlockItem(ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH, new Item.Properties(), Direction.DOWN)
    );
    public static final Item COPPER_BARS = registerModItem("copper_bars",
            new BlockItem(ModBlocks.COPPER_BARS, new Item.Properties())
    );
    public static final Item EXPOSED_COPPER_BARS = registerModItem("exposed_copper_bars",
            new BlockItem(ModBlocks.EXPOSED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WEATHERED_COPPER_BARS = registerModItem("weathered_copper_bars",
            new BlockItem(ModBlocks.WEATHERED_COPPER_BARS, new Item.Properties())
    );
    public static final Item OXIDIZED_COPPER_BARS = registerModItem("oxidized_copper_bars",
            new BlockItem(ModBlocks.OXIDIZED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_COPPER_BARS = registerModItem("waxed_copper_bars",
            new BlockItem(ModBlocks.WAXED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_COPPER_BARS = registerModItem("waxed_exposed_copper_bars",
            new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_COPPER_BARS = registerModItem("waxed_weathered_copper_bars",
            new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_COPPER_BARS = registerModItem("waxed_oxidized_copper_bars",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_BARS, new Item.Properties())
    );
    public static final Item COPPER_CHAIN = registerModItem("copper_chain",
            new BlockItem(ModBlocks.COPPER_CHAIN, new Item.Properties())
    );
    public static final Item EXPOSED_COPPER_CHAIN = registerModItem("exposed_copper_chain",
            new BlockItem(ModBlocks.EXPOSED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WEATHERED_COPPER_CHAIN = registerModItem("weathered_copper_chain",
            new BlockItem(ModBlocks.WEATHERED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item OXIDIZED_COPPER_CHAIN = registerModItem("oxidized_copper_chain",
            new BlockItem(ModBlocks.OXIDIZED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_COPPER_CHAIN = registerModItem("waxed_copper_chain",
            new BlockItem(ModBlocks.WAXED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_COPPER_CHAIN = registerModItem("waxed_exposed_copper_chain",
            new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_COPPER_CHAIN = registerModItem("waxed_weathered_copper_chain",
            new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_COPPER_CHAIN = registerModItem("waxed_oxidized_copper_chain",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item EXPOSED_LIGHTNING_ROD = registerModItem("exposed_lightning_rod",
            new BlockItem(ModBlocks.EXPOSED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WEATHERED_LIGHTNING_ROD = registerModItem("weathered_lightning_rod",
            new BlockItem(ModBlocks.WEATHERED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item OXIDIZED_LIGHTNING_ROD = registerModItem("oxidized_lightning_rod",
            new BlockItem(ModBlocks.OXIDIZED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_LIGHTNING_ROD = registerModItem("waxed_lightning_rod",
            new BlockItem(ModBlocks.WAXED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_LIGHTNING_ROD = registerModItem("waxed_exposed_lightning_rod",
            new BlockItem(ModBlocks.WAXED_EXPOSED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_LIGHTNING_ROD = registerModItem("waxed_weathered_lightning_rod",
            new BlockItem(ModBlocks.WAXED_WEATHERED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_LIGHTNING_ROD = registerModItem("waxed_oxidized_lightning_rod",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item COPPER_LANTERN = registerModItem("copper_lantern",
            new BlockItem(ModBlocks.COPPER_LANTERN, new Item.Properties())
    );
    public static final Item EXPOSED_COPPER_LANTERN = registerModItem("exposed_copper_lantern",
            new BlockItem(ModBlocks.EXPOSED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WEATHERED_COPPER_LANTERN = registerModItem("weathered_copper_lantern",
            new BlockItem(ModBlocks.WEATHERED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item OXIDIZED_COPPER_LANTERN = registerModItem("oxidized_copper_lantern",
            new BlockItem(ModBlocks.OXIDIZED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_COPPER_LANTERN = registerModItem("waxed_copper_lantern",
            new BlockItem(ModBlocks.WAXED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_COPPER_LANTERN = registerModItem("waxed_exposed_copper_lantern",
            new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_COPPER_LANTERN = registerModItem("waxed_weathered_copper_lantern",
            new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_COPPER_LANTERN = registerModItem("waxed_oxidized_copper_lantern",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN, new Item.Properties())
    );

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static final Holder<ArmorMaterial> COPPER = Registry.registerForHolder(
            BuiltInRegistries.ARMOR_MATERIAL,
            Main.withModNamespace("copper"),
            new ArmorMaterial(
                    Util.make(new EnumMap(ArmorItem.Type.class), enumMap -> {
                        enumMap.put(ArmorItem.Type.BOOTS, ArmorDefenseValues.COPPER_ARMOR.getOrDefault(ArmorItem.Type.BOOTS, 1));
                        enumMap.put(ArmorItem.Type.LEGGINGS, ArmorDefenseValues.COPPER_ARMOR.getOrDefault(ArmorItem.Type.LEGGINGS, 1));
                        enumMap.put(ArmorItem.Type.CHESTPLATE, ArmorDefenseValues.COPPER_ARMOR.getOrDefault(ArmorItem.Type.CHESTPLATE, 1));
                        enumMap.put(ArmorItem.Type.HELMET, ArmorDefenseValues.COPPER_ARMOR.getOrDefault(ArmorItem.Type.HELMET, 1));
                        enumMap.put(ArmorItem.Type.BODY, ArmorDefenseValues.COPPER_ARMOR.getOrDefault(ArmorItem.Type.BODY, 1));}
                    ),
                    8, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Items.COPPER_INGOT),
                    List.of(new ArmorMaterial.Layer(Main.withModNamespace("copper"))),
                    0, ArmorBonusValues.KNOCKBACK_RESIST.getOrDefault(ArmorMaterials.GOLD, 0)
            )
    );
    public static final Tier COPPER_TIER = new Tier() {
        @Override
        public int getUses() {
            return DurabilityValues.COPPER_TOOL;
        }

        @Override
        public float getSpeed() {
            return StatValues.COPPER_MINING_SPEED;
        }

        @Override
        public float getAttackDamageBonus() {
            return 1.0F;
        }

        @Override
        public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_STONE_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 13;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.COPPER_INGOT);
        }
    };
    public static final Item COPPER_PICKAXE = registerModItem("copper_pickaxe",
            new PickaxeItem(COPPER_TIER, new Item.Properties().attributes(
                    PickaxeItem.createAttributes(COPPER_TIER, 1.0F, -2.8F))
            )
    );
    public static final Item COPPER_AXE = registerModItem("copper_axe",
            new AxeItem(COPPER_TIER, new Item.Properties().attributes(
                    AxeItem.createAttributes(COPPER_TIER, 7.0F, StatValues.AXE_ATTACK_SPEED))
            )
    );
    public static final Item COPPER_SWORD = registerModItem("copper_sword",
            new SwordItem(COPPER_TIER, new Item.Properties().attributes(
                    SwordItem.createAttributes(COPPER_TIER, 3, -2.4F))
            )
    );
    public static final Item COPPER_SHOVEL = registerModItem("copper_shovel",
            new ShovelItem(COPPER_TIER, new Item.Properties().attributes(
                    ShovelItem.createAttributes(COPPER_TIER, 1.5F, -3.0F))
            )
    );
    public static final Item COPPER_HOE = registerModItem("copper_hoe",
            new HoeItem(COPPER_TIER, new Item.Properties().attributes(
                    HoeItem.createAttributes(COPPER_TIER, -1.0F, StatValues.HOE_ATTACK_SPEED))
            )
    );
    public static final Item COPPER_HELMET = registerModItem("copper_helmet",
            new ArmorItem(COPPER, ArmorItem.Type.HELMET, new Item.Properties().durability(
                    ArmorItem.Type.HELMET.getDurability(DurabilityValues.COPPER_ARMOR_MULT))
            )
    );
    public static final Item COPPER_CHESTPLATE = registerModItem("copper_chestplate",
            new ArmorItem(COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(
                    ArmorItem.Type.CHESTPLATE.getDurability(DurabilityValues.COPPER_ARMOR_MULT))
            )
    );
    public static final Item COPPER_LEGGINGS = registerModItem("copper_leggings",
            new ArmorItem(COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(
                    ArmorItem.Type.LEGGINGS.getDurability(DurabilityValues.COPPER_ARMOR_MULT))
            )
    );
    public static final Item COPPER_BOOTS = registerModItem("copper_boots",
            new ArmorItem(COPPER, ArmorItem.Type.BOOTS, new Item.Properties().durability(
                    ArmorItem.Type.BOOTS.getDurability(DurabilityValues.COPPER_ARMOR_MULT))
            )
    );
    public static final Item COPPER_HORSE_ARMOR = registerModItem("copper_horse_armor",
            new AnimalArmorItem(COPPER, AnimalArmorItem.BodyType.EQUESTRIAN,false, FabricLoader.getInstance().isModLoaded("vshorses") ?
                    new Item.Properties().durability(ArmorItem.Type.BODY.getDurability(DurabilityValues.COPPER_ARMOR_MULT)).stacksTo(1) :
                    new Item.Properties().stacksTo(1)
            )
    );

    private static Item registerModItem(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                Main.withModNamespace(name),
                item
        );
    }

    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("vshorses")) DefaultItemComponentEvents.MODIFY.register(context -> context.modify(
                item -> item instanceof AnimalArmorItem aai && aai.getBodyType() == AnimalArmorItem.BodyType.EQUESTRIAN && item.components().has(DataComponents.MAX_DAMAGE),
                (builder, item) -> {
                    int multiplier = DurabilityValues.ARMOR_DURABILITY_MULTS.getOrDefault(((AnimalArmorItem) item).getMaterial(), 0);
                    if (multiplier > 0) builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.BODY.getDurability(multiplier));
                }
        ));
        CustomBrewRecipeRegister.registerCustomRecipeWithComponents(
                Items.SPLASH_POTION,
                Items.EMERALD,
                Items.EXPERIENCE_BOTTLE,
                DataComponentMap.builder().set(
                        DataComponents.POTION_CONTENTS,
                        new PotionContents(Potions.AWKWARD)
                ).build(),
                null,
                null
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.TORCH, COPPER_TORCH);
            entries.addAfter(Items.SOUL_LANTERN,
                    COPPER_LANTERN, EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN,
                    WAXED_COPPER_LANTERN, WAXED_EXPOSED_COPPER_LANTERN, WAXED_WEATHERED_COPPER_LANTERN, WAXED_OXIDIZED_COPPER_LANTERN
            );
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries ->
                entries.addBefore(Items.IRON_NUGGET, COPPER_NUGGET)
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addBefore(Items.COPPER_DOOR,
                    COPPER_BARS, EXPOSED_COPPER_BARS, WEATHERED_COPPER_BARS, OXIDIZED_COPPER_BARS,
                    WAXED_COPPER_BARS, WAXED_EXPOSED_COPPER_BARS, WAXED_WEATHERED_COPPER_BARS, WAXED_OXIDIZED_COPPER_BARS
            );
            entries.addAfter(Items.WAXED_OXIDIZED_COPPER_BULB,
                    COPPER_CHAIN, EXPOSED_COPPER_CHAIN, WEATHERED_COPPER_CHAIN, OXIDIZED_COPPER_CHAIN,
                    WAXED_COPPER_CHAIN, WAXED_EXPOSED_COPPER_CHAIN, WAXED_WEATHERED_COPPER_CHAIN, WAXED_OXIDIZED_COPPER_CHAIN
            );
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries ->
                entries.addAfter(Items.LIGHTNING_ROD,
                        EXPOSED_LIGHTNING_ROD, WEATHERED_LIGHTNING_ROD, OXIDIZED_LIGHTNING_ROD,
                        WAXED_LIGHTNING_ROD, WAXED_EXPOSED_LIGHTNING_ROD, WAXED_WEATHERED_LIGHTNING_ROD, WAXED_OXIDIZED_LIGHTNING_ROD
                )
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
                entries.addAfter(Items.WOODEN_HOE, COPPER_SHOVEL, COPPER_PICKAXE, COPPER_AXE, COPPER_HOE)
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.addAfter(Items.WOODEN_AXE, COPPER_AXE);
            entries.addAfter(Items.WOODEN_SWORD, COPPER_SWORD);
            entries.addAfter(Items.LEATHER_HORSE_ARMOR, COPPER_HORSE_ARMOR);
            entries.addAfter(Items.LEATHER_BOOTS, COPPER_HELMET, COPPER_CHESTPLATE, COPPER_LEGGINGS, COPPER_BOOTS);
        });
    }
}
