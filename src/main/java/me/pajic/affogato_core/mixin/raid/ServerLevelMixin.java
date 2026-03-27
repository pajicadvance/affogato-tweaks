package me.pajic.affogato_core.mixin.raid;

import me.pajic.affogato_core.raid.ClearedOutposts;
import me.pajic.affogato_core.raid.ServerLevelAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.SavedDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin implements ServerLevelAccess {

    @Shadow
    public abstract SavedDataStorage getDataStorage();

    @Unique private ClearedOutposts clearedOutposts;

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void initClearedOutposts(CallbackInfo ci) {
        clearedOutposts = getDataStorage().computeIfAbsent(ClearedOutposts.getType());
    }

    @Override
    public ClearedOutposts affogatotweaks$getClearedOutposts() {
        return clearedOutposts;
    }
}
