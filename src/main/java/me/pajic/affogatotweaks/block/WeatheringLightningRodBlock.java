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

public class WeatheringLightningRodBlock extends LightningRodBlock implements WeatheringCopper {
    public static final MapCodec<WeatheringLightningRodBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringLightningRodBlock::getAge), propertiesCodec())
                    .apply(instance, WeatheringLightningRodBlock::new)
    );
    private final WeatherState weatherState;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public @NotNull MapCodec<LightningRodBlock> codec() {
        return (MapCodec) CODEC;
    }

    public WeatheringLightningRodBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, false).setValue(POWERED, false));
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
