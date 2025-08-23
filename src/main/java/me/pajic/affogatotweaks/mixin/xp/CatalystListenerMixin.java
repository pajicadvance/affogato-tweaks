package me.pajic.affogatotweaks.mixin.xp;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogatotweaks.util.MobBuffLevelAccess;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SculkCatalystBlockEntity.CatalystListener.class)
public class CatalystListenerMixin {

    @WrapMethod(method = "handleGameEvent")
    private boolean noSculkSpreadIfMobNotBuffed(ServerLevel level, Holder<GameEvent> gameEvent, GameEvent.Context context, Vec3 pos, Operation<Boolean> original) {
        Entity entity = context.sourceEntity();
        return entity instanceof Mob mob && ((MobBuffLevelAccess) mob).at$getBuffLevel() > 0 ?
                original.call(level, gameEvent, context, pos) : false;
    }
}
