package me.pajic.affogatotweaks.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VillagerTrades.class)
public abstract class VillagerTradesMixin {

    // Removes the bottle of enchanting trade from the cleric villager
    @ModifyArg(method = "method_16929", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/VillagerTrades;toIntMap(Lcom/google/common/collect/ImmutableMap;)Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", ordinal = 6))
    private static ImmutableMap<Integer, VillagerTrades.ItemListing[]> modifyClericTrades(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
        return ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.ROTTEN_FLESH, 32, 16, 2),
                        new VillagerTrades.ItemsForEmeralds(Items.REDSTONE, 1, 2, 1)},
                2, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 3, 12, 10),
                        new VillagerTrades.ItemsForEmeralds(Items.LAPIS_LAZULI, 1, 1, 5)},
                3, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.RABBIT_FOOT, 2, 12, 20),
                        new VillagerTrades.ItemsForEmeralds(Blocks.GLOWSTONE, 4, 1, 12, 10)},
                4, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.SCUTE, 4, 12, 30),
                        new VillagerTrades.EmeraldForItems(Items.GLASS_BOTTLE, 9, 12, 30),
                        new VillagerTrades.ItemsForEmeralds(Items.ENDER_PEARL, 5, 1, 15)},
                5, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.NETHER_WART, 22, 12, 30)}
        );
    }

    // Changes the enchanted diamond armor armorer trades to enchanted iron armor trades
    @ModifyArg(method = "method_16929", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/VillagerTrades;toIntMap(Lcom/google/common/collect/ImmutableMap;)Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", ordinal = 7))
    private static ImmutableMap<Integer, VillagerTrades.ItemListing[]> modifyArmorerTrades(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
        return ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_LEGGINGS), 7, 1, 12, 1, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2F)},
                2, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 12, 10),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2F)},
                3, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.LAVA_BUCKET, 1, 12, 20),
                        new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 12, 20),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2F)},
                4, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_LEGGINGS, 7, 3, 15, 0.2F),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_BOOTS, 4, 3, 15, 0.2F)},
                5, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_HELMET, 5, 3, 15, 0.2F),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_CHESTPLATE, 9, 3, 15, 0.2F)}
        );
    }

    // Changes the enchanted diamond weapon weaponsmith trades to enchanted iron weapon trades
    @ModifyArg(method = "method_16929", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/VillagerTrades;toIntMap(Lcom/google/common/collect/ImmutableMap;)Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", ordinal = 8))
    private static ImmutableMap<Integer, VillagerTrades.ItemListing[]> modifyWeaponsmithTrades(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
        return ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2F),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SWORD, 2, 3, 10)},
                2, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 12, 10),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)},
                3, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.FLINT, 24, 12, 20)},
                4, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 12, 30),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_AXE, 2, 3, 10, 0.2F)},
                5, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SWORD, 2, 3, 10, 0.2F)}
        );
    }

    // Changes the enchanted diamond tool toolsmith trades to enchanted iron tool trades
    @ModifyArg(method = "method_16929", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/VillagerTrades;toIntMap(Lcom/google/common/collect/ImmutableMap;)Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", ordinal = 9))
    private static ImmutableMap<Integer, VillagerTrades.ItemListing[]> modifyToolsmithTrades(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
        return ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2F)},
                2, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 12, 10),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)},
                3, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.FLINT, 30, 12, 20),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_AXE, 1, 3, 10, 0.2F),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SHOVEL, 2, 3, 10, 0.2F),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_PICKAXE, 3, 3, 10, 0.2F),
                        new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2F)},
                4, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 12, 30),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_AXE, 1, 3, 10, 0.2F),
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SHOVEL, 2, 3, 10, 0.2F)},
                5, new VillagerTrades.ItemListing[]{
                        new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_PICKAXE, 3, 3, 10, 0.2F)}
        );
    }
}
