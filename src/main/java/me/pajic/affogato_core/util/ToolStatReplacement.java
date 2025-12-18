package me.pajic.affogato_core.util;

import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedString;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

public class ToolStatReplacement {
    public ValidatedString material;
    public ValidatedEnum<ToolType> type;
    public ValidatedFloat attackDamage;
    public ValidatedFloat attackSpeed;
    public ValidatedInt durability;
    public ValidatedFloat miningSpeed;

    public ToolStatReplacement(String material, ToolType type, float attackDamage, float attackSpeed) {
        this.material = new ValidatedString(material);
        this.type = new ValidatedEnum<>(type);
        this.attackDamage = new ValidatedFloat(attackDamage);
        this.attackSpeed = new ValidatedFloat(attackSpeed);
        this.durability = new ValidatedInt(-1);
        this.miningSpeed = new ValidatedFloat(99);
    }

    public ToolStatReplacement(String material, int durability, float miningSpeed) {
        this.material = new ValidatedString(material);
        this.type = new ValidatedEnum<>(ToolType.ANY);
        this.attackDamage = new ValidatedFloat(99);
        this.attackSpeed = new ValidatedFloat(99);
        this.durability = new ValidatedInt(durability);
        this.miningSpeed = new ValidatedFloat(miningSpeed);
    }

    public ToolStatReplacement() {
        this.material = new ValidatedString("");
        this.type = new ValidatedEnum<>(ToolType.class);
        this.attackDamage = new ValidatedFloat(99);
        this.attackSpeed = new ValidatedFloat(99);
        this.durability = new ValidatedInt(-1);
        this.miningSpeed = new ValidatedFloat(99);
    }
}
