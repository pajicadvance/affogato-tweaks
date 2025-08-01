package me.pajic.affogatotweaks.effect;

import me.pajic.affogatotweaks.Main;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class StatBoostEffects {
    public static final Holder<MobEffect> KNOCKBACK_RESIST = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "knockback_resist"),
            new MobEffect(MobEffectCategory.BENEFICIAL, 0x8a8a8a).addAttributeModifier(
                    Attributes.KNOCKBACK_RESISTANCE,
                    ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "effect.knockback_resist"),
                    0.2, AttributeModifier.Operation.ADD_VALUE
            )
    );
    public static final Holder<MobEffect> ATTACK_KNOCKBACK = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "attack_knockback"),
            new MobEffect(MobEffectCategory.BENEFICIAL, 0x8a8a8a).addAttributeModifier(
                    Attributes.ATTACK_KNOCKBACK,
                    ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "effect.attack_knockback"),
                    1, AttributeModifier.Operation.ADD_VALUE
            )
    );
    public static final Holder<MobEffect> FOLLOW_RANGE = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "follow_range"),
            new MobEffect(MobEffectCategory.BENEFICIAL, 0x8a8a8a).addAttributeModifier(
                    Attributes.FOLLOW_RANGE,
                    ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "effect.follow_range"),
                    4, AttributeModifier.Operation.ADD_VALUE
            )
    );

    public static void init() {}
}
