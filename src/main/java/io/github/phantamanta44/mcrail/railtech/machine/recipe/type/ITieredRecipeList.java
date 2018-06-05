package io.github.phantamanta44.mcrail.railtech.machine.recipe.type;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.IMachineInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.IMachineOutput;

import java.util.Collection;
import java.util.function.Function;

public interface ITieredRecipeList<T, I extends IMachineInput<T>, O extends IMachineOutput, R extends IMachineRecipe<T, I, O>> {

    Function<T, R> forTier(int tier);

    Collection<R> recipes(int tier);

    void add(int tier, R recipe);

}
