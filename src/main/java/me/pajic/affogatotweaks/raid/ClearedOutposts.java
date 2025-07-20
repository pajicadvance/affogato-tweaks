package me.pajic.affogatotweaks.raid;

import me.pajic.affogatotweaks.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClearedOutposts extends SavedData {
    private final List<BlockPos> outposts = new ArrayList<>();

    public static SavedData.Factory<ClearedOutposts> factory() {
        return new SavedData.Factory<>(ClearedOutposts::new, ClearedOutposts::read, DataFixTypes.LEVEL);
    }

    public ClearedOutposts() {
        setDirty();
    }

    public void addClearedOutpost(BlockPos pos) {
        outposts.add(pos);
    }

    public boolean isOutpostCleared(BlockPos pos) {
        return outposts.contains(pos);
    }

    public static boolean isPillagerOutpost(ServerLevel level, BlockPos center) {
        BlockPos pos = level.findNearestMapStructure(Main.OUTPOSTS, center, 1, false);
        return pos != null && center.distToCenterSqr(pos.getX(), center.getY(), pos.getZ()) < 2304;
    }

    private static ClearedOutposts read(CompoundTag tag, HolderLookup.Provider provider) {
        ClearedOutposts clearedOutposts = new ClearedOutposts();
        ListTag listTag = tag.getList("ClearedOutposts", 3);
        for (int i = 0; i < listTag.size(); i = i + 3) {
            clearedOutposts.outposts.add(new BlockPos(listTag.getInt(i), listTag.getInt(i + 1), listTag.getInt(i + 2)));
        }
        return clearedOutposts;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        ListTag listTag = new ListTag();
        outposts.forEach(pos -> {
            listTag.add(IntTag.valueOf(pos.getX()));
            listTag.add(IntTag.valueOf(pos.getY()));
            listTag.add(IntTag.valueOf(pos.getZ()));
        });
        tag.put("ClearedOutposts", listTag);
        return tag;
    }

    public static String getFileId() {
        return "cleared_outposts";
    }
}
