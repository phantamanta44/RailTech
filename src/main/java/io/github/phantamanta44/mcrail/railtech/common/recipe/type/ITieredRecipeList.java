package io.github.phantamanta44.mcrail.railtech.common.recipe.type;

import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.input.IMachineInput;

import java.util.Collection;
import java.util.function.Function;

public interface ITieredRecipeList<T, I extends IMachineInput<T>, O, R extends IMachineRecipe<T, I, O>> {

    Function<T, O> forTier(int tier);

    Collection<R> recipes(int tier);

    void add(int tier, R recipe);

}
