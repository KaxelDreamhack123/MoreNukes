package com.dreamhack.morenukes.client.render.rbmk;

import com.dreamhack.morenukes.blocks.RBMKFuelCore;
import com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity;
import com.dreamhack.morenukes.registration.MNRegistrator;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class RBMKFuelCoreEntityRender implements BlockEntityRenderer<RBMKFuelCoreEntity> {

    public RBMKFuelCoreEntityRender(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(RBMKFuelCoreEntity rbmkFuelCoreEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        int height = rbmkFuelCoreEntity.getBlockState().getValue(RBMKFuelCore.HEIGHT);

        for (int i = 1; i < height; i++) {
            poseStack.pushPose();
            poseStack.translate(0, -i, 0);

            int maxBrightness = 0xF000F0;

            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                    MNRegistrator.RBMK_FUEL_COLUMN_PART.get().defaultBlockState(),
                    poseStack, buffer, maxBrightness, packedOverlay
            );

            poseStack.popPose();
        }
    }
}
