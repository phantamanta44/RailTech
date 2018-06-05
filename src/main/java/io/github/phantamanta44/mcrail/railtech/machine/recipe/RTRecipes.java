package io.github.phantamanta44.mcrail.railtech.machine.recipe;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.IMachineInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.IMachineOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.GrindingRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.ITieredRecipeList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RTRecipes implements IRecipeManager {

    private static IRecipeManager INSTANCE;

    public static void init() {
        INSTANCE = new RTRecipes();
        registerType(GrindingRecipe.class);
    }

    public static void registerType(Class<? extends IMachineRecipe> type) {
        INSTANCE.addType(type);
    }

    @SuppressWarnings("unchecked")
    public static <T, I extends IMachineInput<T>, O extends IMachineOutput, R extends IMachineRecipe<T, I, O>> ITieredRecipeList<T, I, O, R> forType(Class<R> type) {
        return (ITieredRecipeList<T, I, O, R>)INSTANCE.getRecipeList(type);
    }

    private final Map<Class<? extends IMachineRecipe>, ITieredRecipeList> recipeMap;

    private RTRecipes() {
        this.recipeMap = new HashMap<>();
    }

    @Override
    public ITieredRecipeList getRecipeList(Class<? extends IMachineRecipe> type) {
        return recipeMap.get(type);
    }

    @Override
    public void addType(Class<? extends IMachineRecipe> type) {
        recipeMap.put(type, new TieredRecipeListImpl());
    }

    private static class TieredRecipeListImpl implements ITieredRecipeList {

        private final List<Collection<IMachineRecipe>> tiers;

        private TieredRecipeListImpl() {
            this.tiers = new LinkedList<>();
        }

        @Override
        @SuppressWarnings("unchecked")
        public Function forTier(int tier) {
            List<IMachineRecipe> recipes = tiers.stream()
                    .limit(tier + 1)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            Collections.reverse(recipes);
            return i -> recipes.stream()
                    .filter(r -> r.input().matches(i))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Collection recipes(int tier) {
            return tiers.get(tier);
        }

        @Override
        public void add(int tier, IMachineRecipe recipe) {
            while (tier >= tiers.size())
                tiers.add(new HashSet<>());
            tiers.get(tier).add(recipe);
        }

    }

}
