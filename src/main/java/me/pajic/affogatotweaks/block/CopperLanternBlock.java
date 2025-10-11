package me.pajic.affogatotweaks.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CopperLanternBlock extends LanternBlock implements WeatheringCopper {
    public static final MapCodec<CopperLanternBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(CopperLanternBlock.WeatherState.CODEC.fieldOf("weathering_state").forGetter(CopperLanternBlock::getAge), propertiesCodec())
                    .apply(instance, CopperLanternBlock::new)
    );
    private final WeatherState weatherState;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public @NotNull MapCodec<LanternBlock> codec() {
        return (MapCodec) CODEC;
    }

    public CopperLanternBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(@NotNull BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }

    @Override
    public @NotNull WeatherState getAge() {
        return weatherState;
    }
}
