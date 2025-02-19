package com.dreamhack.morenukes.registration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dreamhack.morenukes.MoreNukes.MODID;

public class MNSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    /************* ITEM GAS WELDER SOUNDS **************/
    public static final RegistryObject<SoundEvent> GAS_WELDER_AMBIENT =
            registerSound("gas_welder_ambient");

    public static final RegistryObject<SoundEvent> GAS_WELDER_USE =
            registerSound("gas_welder_use");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
