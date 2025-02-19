package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.blocks.RBMKFuelCore;
import com.dreamhack.morenukes.registration.MNEntities;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class MNLanguageGen extends LanguageProvider {
    public MNLanguageGen(PackOutput output, String locale) {
        super(output, MoreNukes.MODID, locale);
    }

    @Override
    protected void addTranslations() {

        /*************** BLOCKS *************/
        add(MNRegistrator.RBMK_FUEL_CORE.get(), "RBMK Fuel Core");
        add(MNRegistrator.RBMK_FUEL_COLUMN_PART.get(), "RBMK Fuel Column Part");

        /*************** ITEMS *************/
        add(MNRegistrator.GAS_WELDER.get(), "Gas Welder");

        /*************** SOUNDS *************/
        add("sound.morenukes.gas_welder_ambient", "Gas Welder Ambient Sound");
        add("sound.morenukes.gas_welder_use", "Gas Welder Use Sound");

        /*************** SCREENS *************/
        add(RBMKFuelCore.SCREEN_RBMK_FUEL_CORE, "RBMK Fuel Core");

        /*************** ENTITIES *************/
        add(MNEntities.BLOOM_SPARK.get(), "Spark");

    }
}
