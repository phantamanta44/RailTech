package io.github.phantamanta44.mcrail.railtech.machine.recipe;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.IMachineInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.IMachineOutput;

public interface IMachineRecipe<T, I extends IMachineInput<T>, O extends IMachineOutput> {

    I input();

    O mapToOutput(T input);

}
