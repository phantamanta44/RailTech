package io.github.phantamanta44.mcrail.railtech.common.recipe.impl;

import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.util.Duo;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MaceratorRecipe implements IMachineRecipe<ItemStack, Duo<ItemStack>> {

    public static final RecipeType<ItemStack, Duo<ItemStack>> TYPE = new RecipeType<>();
    
    private final Predicate<ItemStack> input;
    private final UnaryOperator<ItemStack> primOutput, secOutput;
    private final float chance;

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> primOutput, UnaryOperator<ItemStack> secOutput, float chance) {
        this.input = input;
        this.primOutput = primOutput;
        this.secOutput = secOutput;
        this.chance = chance;
    }

    @Override
    public RecipeType<ItemStack, Duo<ItemStack>> type() {
        return TYPE;
    }

    @Override
    public boolean matches(ItemStack input) {
        return this.input.test(input);
    }

    @Override
    public Duo<ItemStack> output(ItemStack input) {
        return Duo.of(primOutput.apply(input), secOutput.apply(input)); // FIXME
    }

}