package com.dreamhack.morenukes.client.screens;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.containers.RBMKFuelCoreContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RBMKFuelCoreScreen extends AbstractContainerScreen<RBMKFuelCoreContainer> {

    private final ResourceLocation GUI = new ResourceLocation(MoreNukes.MODID, "textures/gui/rbmk_fuel_core_gui.png");

    public RBMKFuelCoreScreen(RBMKFuelCoreContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
        this.inventoryLabelY = this.imageHeight - 110;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
