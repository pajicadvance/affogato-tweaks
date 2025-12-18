package me.pajic.affogato_core.raid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.pajic.affogato_core.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.List;

public class ClearedOutposts extends SavedData {
    private final List<BlockPos> outposts = new ArrayList<>();
    public static final Codec<ClearedOutposts> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BlockPos.CODEC.listOf().fieldOf("outposts").forGetter(o -> o.outposts)
            ).apply(instance, ClearedOutposts::new)
    );
    public static final SavedDataType<ClearedOutposts> TYPE = new SavedDataType<>(
            "cleared_outposts",
            ClearedOutposts::new,
            CODEC,
            DataFixTypes.LEVEL
    );

    public static SavedDataType<ClearedOutposts> getType() {
        return TYPE;
    }

    public ClearedOutposts() {
        setDirty();
    }

    private ClearedOutposts(List<BlockPos> outposts) {
        this.outposts.addAll(outposts);
        Main.debugLog("Cleared outposts:\n{}", outposts);
        setDirty();
    }

    public void addClearedOutpost(BlockPos pos) {
        outposts.add(pos);
        Main.debugLog("Added outpost at {} {} {}", pos.getX(), pos.getY(), pos.getZ());
        Main.debugLog("Cleared outposts:\n{}", outposts);
        setDirty();
    }

    public boolean isOutpostCleared(BlockPos pos) {
        return outposts.contains(pos);
    }

    public static boolean isPillagerOutpost(ServerLevel level, BlockPos center) {
        BlockPos pos = level.findNearestMapStructure(Main.OUTPOSTS, center, 1, false);
        return pos != null && center.distToCenterSqr(pos.getX(), center.getY(), pos.getZ()) < 2304;
    }
}
