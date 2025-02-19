package com.dreamhack.morenukes.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGeneration {

    public static void generate(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new MNBlockStates(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new MNItemModels(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new MNLanguageGen(packOutput, "en_us"));
        generator.addProvider(event.includeClient(), new MNAnimationGen(packOutput));
        generator.addProvider(event.includeClient(), new MNSoundsGen(packOutput, event.getExistingFileHelper()));

        MNBlockTagsGen blockTags = new MNBlockTagsGen(packOutput, lookupProvider, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new MNItemTagsGen(packOutput, lookupProvider, blockTags, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new MNRecipesGen(packOutput));
//        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
//                List.of(new LootTableProvider.SubProviderEntry(MNLootTablesGen::new, LootContextParamSets.BLOCK))));
    }


}
