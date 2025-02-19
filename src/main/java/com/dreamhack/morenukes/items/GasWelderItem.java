package com.dreamhack.morenukes.items;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.client.entity.BloomSparkEntity;
import com.dreamhack.morenukes.client.particles.MNParticlesRegistrator;
import com.dreamhack.morenukes.qol.GasWelderAmbientSound;
import com.dreamhack.morenukes.qol.GasWelderUseSound;
import com.dreamhack.morenukes.registration.MNClientSetup;
import com.dreamhack.morenukes.registration.MNEntities;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class GasWelderItem extends Item {

    public static final Map<UUID, GasWelderAmbientSound> ACTIVE_AMBIENT_SOUNDS = new HashMap<>();
    public static final Map<UUID, GasWelderUseSound> ACTIVE_USE_SOUNDS = new HashMap<>();
    private static final Map<UUID, BloomSparkEntity> ACTIVE_GLOW_ENTITIES = new HashMap<>();

    public GasWelderItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 7200;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (entity instanceof Player player) {
            if (!level.isClientSide()) {
                if (!player.isAlive()) {
                    MNClientSetup.stopWelderSounds(player.getUUID());
                }
                if (!ACTIVE_AMBIENT_SOUNDS.containsKey(player.getUUID())) {
                    MNClientSetup.playWelderSound(player.getUUID(), player);
                } else if (!ACTIVE_USE_SOUNDS.containsKey(player.getUUID())) {
                    if (remainingUseDuration > 15) {
                        MNClientSetup.playWelderUseSound(player.getUUID(), player);
                    }
                }
            }
            if (remainingUseDuration > 15) {

                Vec3 startPos = player.getEyePosition();
                Vec3 lookVec = player.getViewVector(1.0F).scale(3);
                Vec3 endPos = startPos.add(lookVec);

                BlockHitResult hitResult = level.clip(new ClipContext(startPos, endPos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));

                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = hitResult.getBlockPos();
                    Direction face = hitResult.getDirection();

                    Vec3 particlePos = Vec3.atCenterOf(blockPos).add(Vec3.atLowerCornerOf(face.getNormal()).scale(0.7));
                    if (level.isClientSide()) {

                        level.addParticle(MNParticlesRegistrator.FALLING_SPARK.get(),
                                particlePos.x, particlePos.y, particlePos.z,
                                (Math.random() - 0.5) * 0.1,
                                -0.02,
                                (Math.random() - 0.5) * 0.1
                        );
//                        if (!ACTIVE_GLOW_ENTITIES.containsKey(player.getUUID())) {
//                            BloomSparkEntity glowEntity = new BloomSparkEntity(MNEntities.BLOOM_SPARK.get(), level);
//                            glowEntity.setPos(particlePos.x, particlePos.y, particlePos.z);
//                            level.addFreshEntity(glowEntity);
//                            ACTIVE_GLOW_ENTITIES.put(player.getUUID(), glowEntity);
//                        }
                    }
                }
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeCharged) {
        if (!level.isClientSide() && entity instanceof Player player) {
            MNClientSetup.stopWelderSounds(player.getUUID());
            BloomSparkEntity glowEntity = ACTIVE_GLOW_ENTITIES.remove(player.getUUID()); // Удаляем из списка
            if (glowEntity != null) {
                glowEntity.setShouldFadeOut(true); // Включаем плавное исчезновение
            }
        }
    }

}
