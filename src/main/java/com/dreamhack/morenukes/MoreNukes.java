package com.dreamhack.morenukes;

import com.dreamhack.morenukes.client.particles.MNParticlesRegistrator;
import com.dreamhack.morenukes.data.DataGeneration;
import com.dreamhack.morenukes.registration.MNEntities;
import com.dreamhack.morenukes.registration.MNRegistrator;
import com.dreamhack.morenukes.registration.MNSounds;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MoreNukes.MODID)
public class MoreNukes
{
    public static final String MODID = "morenukes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MoreNukes(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(MNRegistrator::addCreative);
        modEventBus.addListener(DataGeneration::generate);
        MinecraftForge.EVENT_BUS.register(this);

        MNRegistrator.register(modEventBus);
        MNSounds.register(modEventBus);
        MNParticlesRegistrator.register(modEventBus);
        MNEntities.register(modEventBus);
//        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
