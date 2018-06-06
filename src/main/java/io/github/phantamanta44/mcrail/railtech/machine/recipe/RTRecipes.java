package io.github.phantamanta44.mcrail.railtech.machine.recipe;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.IMachineInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.IMachineOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.GrindingRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.SmeltingRecipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RTRecipes implements IRecipeManager {

    private static IRecipeManager INSTANCE;

    public static void init() {
        INSTANCE = new RTRecipes();
        registerType(GrindingRecipe.class);
        registerType(SmeltingRecipe.class);
    }

    public static void registerType(Class<? extends IMachineRecipe> type) {
        INSTANCE.addType(type);
    }

    @SuppressWarnings("unchecked")
    public static <T, I extends IMachineInput<T>, O extends IMachineOutput, R extends IMachineRecipe<T, I, O>> IRecipeList<T, I, O, R> forType(Class<R> type) {
        return (IRecipeList<T, I, O, R>)INSTANCE.getRecipeList(type);
    }

    private final Map<Class<? extends IMachineRecipe>, IRecipeList> recipeMap;

    private RTRecipes() {
        this.recipeMap = new HashMap<>();
    }

    @Override
    public IRecipeList getRecipeList(Class<? extends IMachineRecipe> type) {
        return recipeMap.get(type);
    }

    @Override
    public void addType(Class<? extends IMachineRecipe> type) {
        recipeMap.put(type, new RecipeListImpl());
    }

    private static class RecipeListImpl implements IRecipeList {

        private final Collection<IMachineRecipe> recipes;

        private RecipeListImpl() {
            this.recipes = new HashSet<>();
        }

        @Override
        @SuppressWarnings("unchecked")
        public IMachineRecipe findRecipe(Object input) {
            return recipes.stream()
                    .filter(r -> r.input().matches(input))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Collection recipes() {
            return recipes;
        }

        @Override
        public void add(IMachineRecipe recipe) {
            recipes.add(recipe);
        }

    }

}
