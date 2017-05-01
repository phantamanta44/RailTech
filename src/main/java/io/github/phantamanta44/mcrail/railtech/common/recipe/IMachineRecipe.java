package io.github.phantamanta44.mcrail.railtech.common.recipe;

public interface IMachineRecipe<I, O> {

    RecipeType<I, O> type();

    boolean matches(I input);

    O output(I input);

}
