package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @WrapMethod(method = "dropCustomDeathLoot")
    private void dropEnchantedBookIfLeader(ServerLevel level, DamageSource damageSource, boolean recentlyHit, Operation<Void> original) {
        original.call(level, damageSource, recentlyHit);
        if (getMaxHealth() > 20.0) {
            List<Holder<Enchantment>> enchantments = new ArrayList<>();
            level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_RANDOM_LOOT).forEach(enchantments::add);
            Optional<Holder<Enchantment>> optional = Util.getRandomSafe(enchantments, level.random);
            if (optional.isPresent()) {
                int i = Mth.nextInt(random, optional.get().value().getMinLevel(), optional.get().value().getMaxLevel());
                ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                book.enchant(optional.get(), i);
                spawnAtLocation(book);
            }
        }
    }
}
