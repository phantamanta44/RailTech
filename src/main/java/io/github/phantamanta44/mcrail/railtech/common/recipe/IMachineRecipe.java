package io.github.phantamanta44.mcrail.railtech.common.recipe;

public interface IMachineRecipe<I, O> {

    RecipeType<I, O> type();

    boolean matches(I input);

    boolean canProcess(I input, O output);

    O output(I input);

}
