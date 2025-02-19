package com.dreamhack.morenukes.qol;

import com.dreamhack.morenukes.registration.MNClientSetup;
import com.dreamhack.morenukes.registration.MNSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class GasWelderAmbientSound extends AbstractTickableSoundInstance {
    private final Player player;

    public GasWelderAmbientSound(Player player) {
        super(MNSounds.GAS_WELDER_AMBIENT.get(), SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
        this.player = player;
        this.looping = true;
        this.volume = 0.05f;
        this.delay = 0;
        this.pitch = 1.0f;

    }

    @Override
    public void tick() {
        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            stop();
            return;
        }

        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();

    }

    public void stopSound() {
        stop();
    }

}
