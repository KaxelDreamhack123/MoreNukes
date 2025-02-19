package com.dreamhack.morenukes.qol;

import com.dreamhack.morenukes.blocks.RBMKFuelColumnPart;
import com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RBMKColumnHelper {

    private static final int MIN_HEIGHT = 3;
    private static final int MAX_HEIGHT = 10;

    public static void checkStructure(Level world, BlockPos pos) {
        int height = 1;
        BlockPos checkPos = pos.below();

        while (height < 3 && world.getBlockState(checkPos).getBlock() instanceof RBMKFuelColumnPart) {
            height++;
            checkPos = checkPos.below();
        }

        if (height == 3) {

            BlockPos topPos = pos;
            BlockEntity tile = new RBMKFuelCoreEntity(topPos, world.getBlockState(topPos));
            world.setBlockEntity(tile);
        }
    }

    public static void breakColumn(Level world, BlockPos pos) {
        BlockPos checkPos = pos.above();
        while (world.getBlockState(checkPos).getBlock() instanceof RBMKFuelColumnPart) {
            checkPos = checkPos.above();
        }

        // Удаляем ядро, если оно есть
        BlockEntity tile = world.getBlockEntity(checkPos.below());
        if (tile instanceof RBMKFuelCoreEntity) {
            world.removeBlockEntity(checkPos.below());
        }
    }
}
