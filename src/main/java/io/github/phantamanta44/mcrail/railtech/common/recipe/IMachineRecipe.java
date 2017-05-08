package io.github.phantamanta44.mcrail.railtech.common.recipe;

import io.github.phantamanta44.mcrail.railtech.common.recipe.input.MachineInput;

@FunctionalInterface
public interface IMachineRecipe<I extends MachineInput, O> {

    O mapToOutput(I input);

}
