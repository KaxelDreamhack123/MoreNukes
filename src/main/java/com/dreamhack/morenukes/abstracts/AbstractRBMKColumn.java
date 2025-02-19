package com.dreamhack.morenukes.abstracts;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;

public abstract class AbstractRBMKColumn extends Block implements EntityBlock {


    public AbstractRBMKColumn(Properties properties) {
        super(properties);
    }

    private int columnHeight = 3;

    public int getHeight() {
        return this.columnHeight;
    }


    public abstract String getColumnType();
}
