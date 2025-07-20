package me.pajic.affogatotweaks.mixin.raid;

import me.pajic.affogatotweaks.raid.ClearedOutposts;
import me.pajic.affogatotweaks.raid.ServerLevelAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin implements ServerLevelAccess {
    @Unique private ClearedOutposts clearedOutposts;
    @Shadow public abstract DimensionDataStorage getDataStorage();

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void initClearedOutposts(CallbackInfo ci) {
        clearedOutposts = getDataStorage().computeIfAbsent(ClearedOutposts.factory(), ClearedOutposts.getFileId());
    }

    @Override
    public ClearedOutposts affogatotweaks$getClearedOutposts() {
        return clearedOutposts;
    }
}
