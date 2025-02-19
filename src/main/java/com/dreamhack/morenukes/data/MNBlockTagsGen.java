package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class MNBlockTagsGen extends BlockTagsProvider {

    public MNBlockTagsGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MoreNukes.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MNRegistrator.RBMK_FUEL_CORE.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(MNRegistrator.RBMK_FUEL_CORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MNRegistrator.RBMK_FUEL_COLUMN_PART.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(MNRegistrator.RBMK_FUEL_COLUMN_PART.get());
    }
}
