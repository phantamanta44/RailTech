package io.github.phantamanta44.mcrail.railtech.machine.recipe;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.IMachineInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.IMachineOutput;

import java.util.Collection;

public interface IRecipeList<T, I extends IMachineInput<T>, O extends IMachineOutput, R extends IMachineRecipe<T, I, O>> {

    R findRecipe(T input);

    Collection<R> recipes();

    void add(R recipe);

}
