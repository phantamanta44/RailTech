package io.github.phantamanta44.mcrail.railtech.machine.recipe;

public interface IRecipeManager {

    IRecipeList getRecipeList(Class<? extends IMachineRecipe> type);

    void addType(Class<? extends IMachineRecipe> type);
    
}
