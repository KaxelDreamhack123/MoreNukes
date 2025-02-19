package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.registration.MNSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import static com.dreamhack.morenukes.MoreNukes.MODID;

public class MNSoundsGen extends SoundDefinitionsProvider {
    public MNSoundsGen(PackOutput output, ExistingFileHelper helper) {
        super(output, MODID, helper);
    }

    @Override
    public void registerSounds() {

        /********** GAS WELDER *********/
        this.add(MNSounds.GAS_WELDER_AMBIENT, definition()
                .subtitle("sound.morenukes.gas_welder_ambient")
                .with(
                        sound(ResourceLocation.fromNamespaceAndPath(MODID, "item/gas_welder_ambient"))
                                .volume(0.5)
                                .weight(1)
                                .stream()
                )
        );
        this.add(MNSounds.GAS_WELDER_USE, definition()
                .subtitle("sound.morenukes.gas_welder_use")
                .with(
                        sound(ResourceLocation.fromNamespaceAndPath(MODID, "item/gas_welder_use"))
                                .volume(1)
                                .weight(1)
                                .preload()
                )
        );
    }
}
