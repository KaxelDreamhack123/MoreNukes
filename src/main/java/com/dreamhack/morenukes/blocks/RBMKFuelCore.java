package com.dreamhack.morenukes.blocks;

import com.dreamhack.morenukes.abstracts.AbstractRBMKColumn;
import com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity;
import com.dreamhack.morenukes.containers.RBMKFuelCoreContainer;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;


public class RBMKFuelCore extends AbstractRBMKColumn {

    public static final String SCREEN_RBMK_FUEL_CORE = "morenukes.screen.rbmk_fuel_core";
    public static final IntegerProperty HEIGHT = IntegerProperty.create("height", 3, 10);

    public RBMKFuelCore() {
        super(Properties.of()
                .strength(3.0f, 3.0f)
                .noOcclusion()
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(HEIGHT, 3));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HEIGHT);
    }

    @Override
    public String getColumnType() {
        return "";
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RBMKFuelCoreEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> tBlockEntityType) {
        if (level.isClientSide) {
            // We don't have anything to do on the client side
            return null;
        } else {
            // Server side we delegate ticking to our block entity
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof RBMKFuelCoreEntity source) {
                    source.tickServer();
                }
            };
        }
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!world.isClientSide && !state.is(newState.getBlock())) {
            BlockEntity be = world.getBlockEntity(pos);
            if(be instanceof Container) {
                Containers.dropContents(world, pos, (Container)be);
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof RBMKFuelCoreEntity) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.translatable(SCREEN_RBMK_FUEL_CORE);
                    }

                    @Override
                    public @Nullable AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
                        return new RBMKFuelCoreContainer(windowId, playerInventory, pos);
                    }
                };
                NetworkHooks.openScreen((ServerPlayer) player, containerProvider, be.getBlockPos());
            }
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }

}
