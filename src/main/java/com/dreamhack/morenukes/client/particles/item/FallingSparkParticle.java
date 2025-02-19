package com.dreamhack.morenukes.client.particles.item;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FallingSparkParticle extends TextureSheetParticle {

    private int bounces;

    protected FallingSparkParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
        super(level, x, y, z, vx, vy, vz);
        this.xd = vx;
        this.yd = vy;
        this.zd = vz;
        this.gravity = 0.1F;
        this.lifetime = 20 + level.random.nextInt(10);
        this.hasPhysics = true;
        this.bounces = 2;
    }

    @Override
    public void tick() {
        if (this.onGround) {
            if (bounces > 0) {
                this.yd = 0.08 + this.random.nextDouble() * 0.05;
                this.xd *= 0.7;
                this.zd *= 0.7;
                bounces--;
            } else {
                this.alpha *= 0.9F;
                if (this.alpha < 0.05F) {
                    this.remove();
                }
            }
        } else {
            this.yd -= 0.005;
        }
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class FallingSparkParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FallingSparkParticleProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
            FallingSparkParticle particle = new FallingSparkParticle(level, x, y, z, vx, vy, vz);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }

}
