package com.dreamhack.morenukes.containers;

import com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import static com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity.SLOT_INPUT_INDEX;

public class RBMKFuelCoreContainer extends AbstractContainerMenu {

    private final ContainerLevelAccess access;

    public RBMKFuelCoreContainer(int windowId, Inventory playerInventory, BlockPos pos) {
        super(MNRegistrator.RBMK_FUEL_CORE_CONTAINER.get(), windowId);
        this.access = ContainerLevelAccess.create(playerInventory.player.level(), pos);

        if (playerInventory.player.level().getBlockEntity(pos) instanceof RBMKFuelCoreEntity fuelColumnEntity) {
            addSlot(new SlotItemHandler(fuelColumnEntity.getItems(), SLOT_INPUT_INDEX, 64, 24));
        }

        layoutPlayerInventorySlots(playerInventory);
    }

    private void layoutPlayerInventorySlots(Inventory playerInventory) {
        // Инвентарь (3 строки по 9 слотов)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 10 + col * 18, 70 + row * 18));
            }
        }
        // Горячая панель (9 слотов)
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 10 + col * 18, 128));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index == SLOT_INPUT_INDEX) { // Перемещение из колонки в инвентарь
                if (!this.moveItemStackTo(stack, 1, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, SLOT_INPUT_INDEX, SLOT_INPUT_INDEX + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, stack);
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, MNRegistrator.RBMK_FUEL_CORE.get());
    }
}
