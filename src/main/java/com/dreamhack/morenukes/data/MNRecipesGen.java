package com.dreamhack.morenukes.data;

import com.dreamhack.morenukes.registration.MNRegistrator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class MNRecipesGen extends RecipeProvider {
    public MNRecipesGen(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MNRegistrator.RBMK_FUEL_CORE.get())
                .pattern("dsd")
                .pattern("dxd")
                .pattern("ddd")
                .define('d', ItemTags.DIRT)
                .define('x', Tags.Items.GEMS_DIAMOND)
                .define('s', Items.STICK)
                .group("tutorial")
                .unlockedBy("iron_ingot", has(Items.DIRT))
                .save(consumer);
    }
}
