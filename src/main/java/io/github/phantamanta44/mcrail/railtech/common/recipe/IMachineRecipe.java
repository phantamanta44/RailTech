package io.github.phantamanta44.mcrail.railtech.common.recipe;

import io.github.phantamanta44.mcrail.railtech.common.recipe.input.IMachineInput;

public interface IMachineRecipe<T, I extends IMachineInput<T>, O> {

    I input();

    O mapToOutput(T input);

}
