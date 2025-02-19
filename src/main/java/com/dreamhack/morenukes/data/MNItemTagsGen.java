package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.MoreNukes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MNItemTagsGen extends ItemTagsProvider {

    public MNItemTagsGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), MoreNukes.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
