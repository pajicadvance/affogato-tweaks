package me.pajic.affogato_core.util;

import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedString;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

public class LootEntryReplacement {
    public ValidatedString replacementItem;
    public ValidatedInt replacementCount;

    public LootEntryReplacement(String replacementItem, int replacementCount) {
        this.replacementItem = new ValidatedString(replacementItem);
        this.replacementCount = new ValidatedInt(replacementCount);
    }

    public LootEntryReplacement() {
        this.replacementItem = new ValidatedString("");
        this.replacementCount = new ValidatedInt(1);
    }
}
