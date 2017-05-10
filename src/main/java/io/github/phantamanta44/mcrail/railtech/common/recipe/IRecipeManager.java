package io.github.phantamanta44.mcrail.railtech.common.recipe;

import io.github.phantamanta44.mcrail.railtech.common.recipe.type.ITieredRecipeList;

public interface IRecipeManager {

    ITieredRecipeList getRecipeList(Class<? extends IMachineRecipe> type);

    void addType(Class<? extends IMachineRecipe> type);
    
}
