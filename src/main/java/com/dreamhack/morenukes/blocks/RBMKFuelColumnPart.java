package com.dreamhack.morenukes.blocks;

import com.dreamhack.morenukes.qol.RBMKColumnHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class RBMKFuelColumnPart extends Block {

    public RBMKFuelColumnPart() {
        super(Properties.of()
                .strength(3.0f, 3.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL));
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);
        RBMKColumnHelper.checkStructure(world, pos);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, world, pos, newState, isMoving);
        RBMKColumnHelper.breakColumn(world, pos);
    }

}
