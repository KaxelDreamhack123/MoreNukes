package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.blocks.RBMKFuelCore;
import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class MNBlockStates extends BlockStateProvider {

    private static <T extends Block> RegistryObject<Block> castToBlock(RegistryObject<T> obj) {
        return (RegistryObject<Block>) obj;
    }

    List<Pair<RegistryObject<Block>, String>> blocks = List.of(
            Pair.of(castToBlock(MNRegistrator.RBMK_FUEL_CORE), "rbmk/fuel/rbmk_fuel_core"),
            Pair.of(castToBlock(MNRegistrator.RBMK_FUEL_COLUMN_PART), "rbmk/fuel/rbmk_fuel_column_part")
    );

    public MNBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MoreNukes.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (var entry : blocks) {
            registerBlock(entry.getLeft(), entry.getRight());
        }
    }

    private void registerBlock(RegistryObject<Block> block, String path) {
        BlockModelBuilder base = models().getBuilder("block/" + path);
        base.parent(models().getExistingFile(mcLoc("cube")));
        base.element()
                .face(Direction.UP).texture("#top").end()
                .face(Direction.DOWN).texture("#bottom").end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end()
                .end();

        base.texture("top", modLoc("block/" + path + "_top"));
        base.texture("bottom", modLoc("block/" + path + "_bottom"));
        base.texture("side", modLoc("block/" + path + "_side"));
        base.texture("particle", modLoc("block/" + path + "_side"));

        base.renderType("solid");
        simpleBlock(block.get(), base);
    }
}
