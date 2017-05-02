package io.github.phantamanta44.mcrail.railtech.common.recipe.impl;

import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MaceratorRecipe implements IMachineRecipe<ItemStack, Pair<ItemStack, ItemStack>> {

    public static final RecipeType<ItemStack, Pair<ItemStack, ItemStack>> TYPE = new RecipeType<>(ItemStack.class, Pair.class);
    
    private final Predicate<ItemStack> input;
    private final UnaryOperator<ItemStack> primOutput, secOutput;
    private final float chance;

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> primOutput, UnaryOperator<ItemStack> secOutput, float chance) {
        this.input = input;
        this.primOutput = primOutput;
        this.secOutput = secOutput;
        this.chance = chance;
    }

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> primOutput, UnaryOperator<ItemStack> secOutput) {
        this(input, primOutput, secOutput, 1F);
    }

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> output) {
        this(input, output, null, 0F);
    }

    @Override
    public RecipeType<ItemStack, Pair<ItemStack, ItemStack>> type() {
        return TYPE;
    }

    @Override
    public boolean matches(ItemStack input) {
        return this.input.test(input);
    }

    @Override
    public boolean canProcess(ItemStack input, Pair<ItemStack, ItemStack> output) {
        return false; // TODO Implement
    }

    @Override
    public Pair<ItemStack, ItemStack> output(ItemStack input) {
        return Pair.of(primOutput.apply(input), Math.random() < chance ? secOutput.apply(input) : null);
    }

}
