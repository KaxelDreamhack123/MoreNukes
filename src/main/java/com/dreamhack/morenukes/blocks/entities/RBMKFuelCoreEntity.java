package com.dreamhack.morenukes.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static com.dreamhack.morenukes.registration.MNRegistrator.RBMK_FUEL_CORE_ENTITY;

public class RBMKFuelCoreEntity extends BlockEntity {
    public RBMKFuelCoreEntity(BlockPos blockPos, BlockState blockState) {
        super(RBMK_FUEL_CORE_ENTITY.get(), blockPos, blockState);
    }

    public static final String ITEMS_INPUT_TAG  = "RODS";
    public static int SLOT_INPUT_INDEX = 0;
    public static int SLOT_COUNT = 1;

    private final ItemStackHandler items = createItemHandler();

    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> items);

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }

    @Nonnull
    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(SLOT_COUNT) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandler.cast();
        }
        else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveClientData(tag);
    }

    private void saveClientData(CompoundTag tag) {
        tag.put(ITEMS_INPUT_TAG, items.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadClientData(tag);
    }

    private void loadClientData(CompoundTag tag) {
        if (tag.contains(ITEMS_INPUT_TAG)) {
            items.deserializeNBT(tag.getCompound(ITEMS_INPUT_TAG));
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveClientData(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag != null) {
            loadClientData(tag);
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            handleUpdateTag(tag);
        }
    }

    public void tickServer() {
        if(level.getGameTime() % 80 == 0) {
            ItemStack stack = items.getStackInSlot(0);
            if(!stack.isEmpty() && stack.is(Items.NETHERITE_BLOCK)) {
                stack.setCount(stack.getCount() - 1);
            }
        }
    }

    public ItemStackHandler getItems() {
        return items;
    }

}
