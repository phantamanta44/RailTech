package io.github.phantamanta44.mcrail.railtech.common.recipe.impl;

import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileInfuser;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.*;
import java.util.function.Predicate;

public class InfuserRecipe implements IMachineRecipe<InfuserRecipe.Input, ItemStack> {

    public static final RecipeType<Input, ItemStack> TYPE = new RecipeType<>(Input.class, ItemStack.class);

    private final Predicate<ItemStack> inputPred;
    private final Type resType;
    private final int resAmount;
    private final ItemStack output;

    public InfuserRecipe(Predicate<ItemStack> input, Type resType, int resAmount, ItemStack output) {
        this.inputPred = input;
        this.resType = resType;
        this.resAmount = resAmount;
        this.output = output;
    }

    @Override
    public RecipeType<Input, ItemStack> type() {
        return TYPE;
    }

    @Override
    public boolean matches(Input input) {
        return input.type == resType && input.amount >= resAmount && inputPred.test(input.input);
    }

    @Override
    public boolean canProcess(Input input, ItemStack output) {
        return ItemUtils.isNully(output) || (this.output.isSimilar(output)
                && output.getAmount() + this.output.getAmount() <= output.getMaxStackSize());
    }

    @Override
    public ItemStack output(Input input) {
        return output.clone();
    }

    public int resourceCost() {
        return resAmount;
    }

    public static class Input {

        private final ItemStack input;
        private final Type type;
        private final int amount;

        public Input(ItemStack input, Type type, int amount) {
            this.input = input;
            this.type = type;
            this.amount = amount;
        }

        public Input(TileInfuser tile) {
            this(tile.inv()[0], tile.resource(), tile.resourceAmount());
        }

    }
    
    public static class Type {

        private static final Map<String, Type> types;

        static {
            types = new HashMap<>();

            registerType("carbon", "Carbon", new ItemStack(Material.COAL));
            registerSource("carbon", ItemUtils.matching(new MaterialData(Material.COAL, (byte)0)), 10);
            registerSource("carbon", ItemUtils.matching(new MaterialData(Material.COAL, (byte)1)), 20);
            registerSource("carbon", ItemUtils.matching("railtech:res-compressedCarbon"), 100);

            registerType("redstone", "Redstone", new ItemStack(Material.REDSTONE));
            registerSource("redstone", ItemUtils.matching(Material.REDSTONE), 10);
            registerSource("redstone", ItemUtils.matching(Material.REDSTONE_BLOCK), 90);
            registerSource("redstone", ItemUtils.matching("railtech:res-compressedRedstone"), 100);

            registerType("diamond", "Diamond", new ItemStack(Material.DIAMOND));
            // TODO Diamond dust infuser source

            registerType("tin", "Tin", new ItemStack(Material.IRON_INGOT));
            registerSource("redstone", ItemUtils.matching("railtech:res-dustTin"), 50);

            registerType("biomass", "Biomass", new ItemStack(Material.SEEDS));
            // TODO Biofuel dust infuser source

            registerType("fungi", "Fungi", new ItemStack(Material.RED_MUSHROOM));
            registerSource("redstone", ItemUtils.matching(Material.BROWN_MUSHROOM), 10);
            registerSource("redstone", ItemUtils.matching(Material.RED_MUSHROOM), 10);
        }

        public static void registerType(String name, String displayName, ItemStack icon) {
            types.put(name, new Type(name, displayName, icon));
        }

        public static void registerSource(String name, Predicate<ItemStack> matcher, int amount) {
            types.get(name).sources.add(Pair.of(matcher, amount));
        }

        public static Type byName(String name) {
            return types.get(name);
        }

        public static Pair<Type, Integer> forStack(ItemStack stack) {
            return types.values().stream()
                    .map(t -> t.sources.stream()
                            .filter(m -> m.getA().test(stack))
                            .findAny()
                            .map(m -> Pair.of(t, m.getB()))
                            .orElse(null))
                    .filter(Objects::nonNull)
                    .findAny()
                    .orElse(null);
        }

        private final String name, displayName;
        private final ItemStack icon;
        private final Collection<Pair<Predicate<ItemStack>, Integer>> sources;

        private Type(String name, String displayName, ItemStack icon) {
            this.name = name;
            this.displayName = displayName;
            this.icon = icon;
            this.sources = new HashSet<>();
        }

        public String name() {
            return name;
        }

        public String displayName() {
            return displayName;
        }

        public ItemStack icon() {
            return icon.clone();
        }

    }

    public static void registerDefault() {
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.DIRT), Type.byName("fungi"), 10, new ItemStack(Material.MYCEL)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.COBBLESTONE), Type.byName("biomass"), 10, new ItemStack(Material.MOSSY_COBBLESTONE)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(new MaterialData(Material.SMOOTH_BRICK, (byte)0)), Type.byName("carbon"), 10, new ItemStack(Material.SMOOTH_BRICK, 1, (short)1)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.SAND), Type.byName("biomass"), 10, new ItemStack(Material.DIRT)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.DIRT), Type.byName("biomass"), 10, new ItemStack(Material.DIRT, 1, (short)2)));
    }

}
