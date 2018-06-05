package io.github.phantamanta44.mcrail.railtech.machine.recipe;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.ITieredRecipeList;

public interface IRecipeManager {

    ITieredRecipeList getRecipeList(Class<? extends IMachineRecipe> type);

    void addType(Class<? extends IMachineRecipe> type);
    
}
