package io.github.phantamanta44.mcrail.railtech.machine.recipe.type;

import io.github.phantamanta44.mcrail.crafting.RailSmeltRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.ItemStackInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.ItemStackOutput;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;
import java.util.function.Predicate;

public class SmeltingRecipe implements IMachineRecipe<ItemStack, ItemStackInput, ItemStackOutput> {

    private final ItemStackInput input;
    private final Function<ItemStack, ItemStack> mapper;

    public SmeltingRecipe(Predicate<ItemStack> input, Function<ItemStack, ItemStack> mapper) {
        this.input = new ItemStackInput(input, 1);
        this.mapper = mapper;
    }

    public SmeltingRecipe(RailSmeltRecipe recipe) {
        this(recipe::matches, recipe::mapToResult);
    }

    public SmeltingRecipe(FurnaceRecipe recipe) {
        this(ItemUtils.matching(recipe.getInput()), is -> recipe.getResult());
    }

    @Override
    public ItemStackInput input() {
        return input;
    }

    @Override
    public ItemStackOutput mapToOutput(ItemStack input) {
        return new ItemStackOutput(mapper.apply(input));
    }

}
