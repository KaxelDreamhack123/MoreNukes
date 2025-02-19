package com.dreamhack.morenukes.registration;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.client.particles.MNParticlesRegistrator;
import com.dreamhack.morenukes.client.particles.item.FallingSparkParticle;
import com.dreamhack.morenukes.client.screens.RBMKFuelCoreScreen;
import com.dreamhack.morenukes.items.GasWelderItem;
import com.dreamhack.morenukes.qol.GasWelderAmbientSound;
import com.dreamhack.morenukes.qol.GasWelderUseSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.UUID;

import static com.dreamhack.morenukes.MoreNukes.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MNClientSetup {

    public static void playWelderSound(UUID playerUUID, Player player) {
        GasWelderAmbientSound sound = new GasWelderAmbientSound(player);
        Minecraft.getInstance().getSoundManager().play(sound);
        GasWelderItem.ACTIVE_AMBIENT_SOUNDS.put(playerUUID, sound);
    }

    public static void playWelderUseSound(UUID playerUUID, Player player) {
        GasWelderUseSound sound = new GasWelderUseSound(player);
        Minecraft.getInstance().getSoundManager().play(sound);
        GasWelderItem.ACTIVE_USE_SOUNDS.put(playerUUID, sound);
    }

    public static void stopWelderSounds(UUID playerUUID) {
        GasWelderAmbientSound ambientSound = GasWelderItem.ACTIVE_AMBIENT_SOUNDS.remove(playerUUID);
        if (ambientSound != null) {
            ambientSound.stopSound();
        }

        GasWelderUseSound useSound = GasWelderItem.ACTIVE_USE_SOUNDS.remove(playerUUID);
        if (useSound != null) {
            useSound.stopSound();
        }
    }

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MNRegistrator.RBMK_FUEL_CORE_CONTAINER.get(), RBMKFuelCoreScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        MoreNukes.LOGGER.debug("MN: PARTICLES REGISTRY");
        event.registerSpriteSet(MNParticlesRegistrator.FALLING_SPARK.get(), FallingSparkParticle.FallingSparkParticleProvider::new);
    }

    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        //event.registerEntityRenderer(MNEntities.BLOOM_SPARK.get(), BloomSparkEntityRender::new);
    }
}
