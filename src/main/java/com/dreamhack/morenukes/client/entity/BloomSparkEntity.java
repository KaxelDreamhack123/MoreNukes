package com.dreamhack.morenukes.client.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class BloomSparkEntity extends Entity {

    private float alpha = 0.0f;
    private float scale = 0.3f;
    private boolean shouldFadeOut = false;
    private int fadeOutTimer = 10;

    public BloomSparkEntity(EntityType<? extends Entity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    @Override
    public void tick() {
        super.tick();

        if (shouldFadeOut) {
            if (fadeOutTimer-- > 0) {
                float progress = fadeOutTimer / 10.0f;
                this.alpha = (float) Math.sin(Math.PI * progress);
                this.scale = 0.3f + (float) Math.sin(Math.PI * progress) * 0.5f;
            } else {
                this.discard();
            }
        } else {
            float time = (float) tickCount / 10.0f;
            this.alpha = (float) (0.5f + 0.5f * Math.sin(time * Math.PI * 2));
            this.scale = 0.3f + 0.5f * this.alpha;
        }
    }

    public void setShouldFadeOut(boolean shouldFadeOut) {
        this.shouldFadeOut = shouldFadeOut;
    }

    public float getAlpha() {
        return alpha;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}
}
