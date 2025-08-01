package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import me.pajic.affogatotweaks.effect.StatBoostEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

import java.util.Map;
import java.util.Set;

public class MobValues {
    public static final int BLAZE_FOLLOW_RANGE = 32;
    public static final int ILLUSIONER_FOLLOW_RANGE = 16;
    public static final int PILLAGER_FOLLOW_RANGE = 24;
    public static final int RAVAGER_FOLLOW_RANGE = 16;
    public static final int ZOMBIE_FOLLOW_RANGE = 12;
    public static final int ZOMBIE_ALERT_OTHER_RANGE = 35;
    public static final int DROWNED_TRIDENT_THROW_DELAY = 80;
    public static final int ENDERMAN_ATTACK_DELAY = 40;

    public static final Set<Holder<MobEffect>> standardMeleeEffects = Set.of(
            MobEffects.HEALTH_BOOST,
            MobEffects.DAMAGE_RESISTANCE,
            MobEffects.DAMAGE_BOOST,
            StatBoostEffects.ATTACK_KNOCKBACK,
            StatBoostEffects.KNOCKBACK_RESIST
    );
    public static final Set<Holder<MobEffect>> standardRangedEffects = Set.of(
            MobEffects.HEALTH_BOOST,
            MobEffects.MOVEMENT_SPEED,
            MobEffects.INVISIBILITY,
            StatBoostEffects.FOLLOW_RANGE
    );

    public static final Map<EntityType<? extends Mob>, Set<Holder<MobEffect>>> MOB_EFFECTS = Map.ofEntries(
            Map.entry(EntityType.SKELETON, standardRangedEffects),
            Map.entry(EntityType.BOGGED, standardRangedEffects),
            Map.entry(EntityType.STRAY, standardRangedEffects),
            Map.entry(EntityType.PILLAGER, standardRangedEffects),

            Map.entry(EntityType.WITHER_SKELETON, standardMeleeEffects),
            Map.entry(EntityType.ZOMBIE, standardMeleeEffects),
            Map.entry(EntityType.ZOMBIE_VILLAGER, standardMeleeEffects),
            Map.entry(EntityType.DROWNED, standardMeleeEffects),
            Map.entry(EntityType.ZOMBIFIED_PIGLIN, standardMeleeEffects),
            Map.entry(EntityType.HUSK, standardMeleeEffects),
            Map.entry(EntityType.MAGMA_CUBE, standardMeleeEffects),
            Map.entry(EntityType.SLIME, standardMeleeEffects),
            Map.entry(EntityType.PHANTOM, standardMeleeEffects),
            Map.entry(EntityType.PIGLIN, standardMeleeEffects),
            Map.entry(EntityType.PIGLIN_BRUTE, standardMeleeEffects),
            Map.entry(EntityType.VINDICATOR, standardMeleeEffects),

            Map.entry(EntityType.SPIDER, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.DAMAGE_BOOST,
                    MobEffects.REGENERATION,
                    MobEffects.INVISIBILITY,
                    MobEffects.WEAVING
            )),
            Map.entry(EntityType.CREEPER, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.REGENERATION,
                    MobEffects.INFESTED
            )),
            Map.entry(EntityType.BLAZE, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.REGENERATION,
                    MobEffects.MOVEMENT_SPEED,
                    StatBoostEffects.FOLLOW_RANGE
            )),
            Map.entry(EntityType.GHAST, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.REGENERATION
            )),
            Map.entry(EntityType.GUARDIAN, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.REGENERATION
            )),
            Map.entry(EntityType.WITCH, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.INVISIBILITY
            )),
            Map.entry(EntityType.EVOKER, Set.of(
                    MobEffects.HEALTH_BOOST,
                    MobEffects.MOVEMENT_SPEED
            ))
    );

    public static final Object2IntMap<Holder<MobEffect>> MAX_EFFECT_AMPLIFIERS = new Object2IntArrayMap<>(Map.ofEntries(
            Map.entry(MobEffects.HEALTH_BOOST, 4),
            Map.entry(MobEffects.DAMAGE_BOOST, 2),
            Map.entry(MobEffects.MOVEMENT_SPEED, 1),
            Map.entry(MobEffects.REGENERATION, 1),
            Map.entry(MobEffects.DAMAGE_RESISTANCE, 1),
            Map.entry(MobEffects.INVISIBILITY, 0),
            Map.entry(MobEffects.WEAVING, 0),
            Map.entry(MobEffects.INFESTED, 0),
            Map.entry(StatBoostEffects.FOLLOW_RANGE, 3),
            Map.entry(StatBoostEffects.KNOCKBACK_RESIST, 1),
            Map.entry(StatBoostEffects.ATTACK_KNOCKBACK, 1)
    ));
}
