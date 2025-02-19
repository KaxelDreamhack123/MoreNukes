package com.dreamhack.morenukes.registration;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.client.entity.BloomSparkEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MNEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoreNukes.MODID);

    public static final RegistryObject<EntityType<BloomSparkEntity>> BLOOM_SPARK = ENTITY_TYPES.register("bloom_spark",
            () -> EntityType.Builder.of(BloomSparkEntity::new, MobCategory.MISC)
                    .sized(1.f, 1.f)
                    .clientTrackingRange(10)
                    .build("bloom_spark"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

