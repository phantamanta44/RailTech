package io.github.phantamanta44.mcrail.railtech.common.recipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RTRecipes {

    private static RTRecipes INSTANCE;

    public static void init() {
        INSTANCE = new RTRecipes();
    }

    public static <I, O> void register(IMachineRecipe<I, O> recipe) {
        INSTANCE.recipes.computeIfAbsent(recipe.type(), k -> new LinkedList<>()).add(recipe);
    }

    @SuppressWarnings("unchecked")
    public static <I, O> IMachineRecipe<I, O> recipeFor(RecipeType<I, O> type, I input) {
        Collection<IMachineRecipe> recipes = INSTANCE.recipes.get(type);
        if (recipes == null)
            return null;
        return (IMachineRecipe<I, O>)recipes.stream()
                .filter(r -> r.matches(input))
                .findFirst().orElse(null);
    }

    public static <I, O> O result(RecipeType<I, O> type, I input) {
        IMachineRecipe<I, O> recipe = recipeFor(type, input);
        return recipe != null ? recipe.output(input) : null;
    }

    private final Map<RecipeType, Collection<IMachineRecipe>> recipes;

    private RTRecipes() {
        recipes = new HashMap<>();
    }

}
