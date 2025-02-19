package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MNItemModels extends ItemModelProvider {
    public MNItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MoreNukes.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        /*********** BLOCKS ***************/
        withExistingParent(MNRegistrator.RBMK_FUEL_CORE.getId().getPath(), modLoc("block/rbmk/fuel/rbmk_fuel_core"));
        withExistingParent(MNRegistrator.RBMK_FUEL_COLUMN_PART.getId().getPath(), modLoc("block/rbmk/fuel/rbmk_fuel_column_part"));


        /*********** ITEMS ***************/
        ItemModelBuilder gasWelder2d = singleTexture(MNRegistrator.GAS_WELDER.getId().getPath() + "_2d",
                mcLoc("item/handheld"),
                "layer0",
                modLoc("item/gas_welder_2d_icon"));


        //Bro
        withExistingParent(MNRegistrator.GAS_WELDER.getId().getPath(), mcLoc("item/handheld"))
                .customLoader((itemModelBuilder, existingFileHelper1)
                        -> SeparateTransformsModelBuilder.begin(itemModelBuilder, existingFileHelper1)
                        .base(withExistingParent(MNRegistrator.GAS_WELDER.getId().getPath() + "_3d_generated", modLoc("item/gas_welder_3d")))
                        .perspective(ItemDisplayContext.GUI, gasWelder2d)
                        .perspective(ItemDisplayContext.GROUND, gasWelder2d)
                        .perspective(ItemDisplayContext.FIXED, gasWelder2d)
                ).end();
    }
}
