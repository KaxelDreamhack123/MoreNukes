package com.dreamhack.morenukes.data;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class MNAnimationGen implements DataProvider {
    private final PackOutput output;
    private final List<String> animatedTextures = List.of("gas_welder_2d_icon");

    public MNAnimationGen(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf(
                animatedTextures.stream()
                        .map(texture -> generateMcmetaFile(cache, texture))
                        .toArray(CompletableFuture[]::new)
        );
    }

    private CompletableFuture<?> generateMcmetaFile(CachedOutput cache, String textureName) {
        Path path = output.getOutputFolder()
                .resolve("assets/morenukes/textures/item/" + textureName + ".png.mcmeta");

        JsonObject animation = createAnimationJson();

        return DataProvider.saveStable(cache, animation, path);
    }

    private static JsonObject createAnimationJson() {
        JsonObject animation = new JsonObject();
        JsonObject animSettings = new JsonObject();

        animSettings.addProperty("frametime", 4);
        animSettings.addProperty("interpolate", false);

        animation.add("animation", animSettings);
        return animation;
    }

    @Override
    public String getName() {
        return "MoreNukes Animation Provider";
    }
}
