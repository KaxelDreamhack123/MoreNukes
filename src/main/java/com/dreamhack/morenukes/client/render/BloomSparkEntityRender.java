package com.dreamhack.morenukes.client.render;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.client.entity.BloomSparkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BloomSparkEntityRender extends EntityRenderer<BloomSparkEntity> {

    private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation(MoreNukes.MODID, "textures/entity/bloom_spark.png");

    public BloomSparkEntityRender(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(BloomSparkEntity bloomSparkEntity) {
        return GLOW_TEXTURE;
    }

    @Override
    public void render(BloomSparkEntity entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {

    }


}
