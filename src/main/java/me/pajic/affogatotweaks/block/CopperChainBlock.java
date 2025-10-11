package me.pajic.affogatotweaks.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CopperChainBlock extends ChainBlock implements WeatheringCopper {
    public static final MapCodec<CopperChainBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(CopperChainBlock::getAge), propertiesCodec())
                    .apply(instance, CopperChainBlock::new)
    );
    private final WeatherState weatherState;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public @NotNull MapCodec<ChainBlock> codec() {
        return (MapCodec) CODEC;
    }

    public CopperChainBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(AXIS, Direction.Axis.Y));
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
