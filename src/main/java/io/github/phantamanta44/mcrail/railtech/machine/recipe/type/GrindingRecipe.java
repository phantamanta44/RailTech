package io.github.phantamanta44.mcrail.railtech.machine.recipe.type;

import io.github.phantamanta44.mcrail.railtech.machine.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.ItemStackInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.MultiStackOutput;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

public class GrindingRecipe implements IMachineRecipe<ItemStack, ItemStackInput, MultiStackOutput> {

    private final ItemStackInput input;
    private final MultiStackOutput output;

    public GrindingRecipe(ItemStackInput input, MultiStackOutput output) {
        this.input = input;
        this.output = output;
    }

    public GrindingRecipe(Predicate<ItemStack> input, int amount, MultiStackOutput output) {
        this(new ItemStackInput(input, amount), output);
    }

    public GrindingRecipe(Predicate<ItemStack> input, MultiStackOutput output) {
        this(input, 1, output);
    }

    @Override
    public ItemStackInput input() {
        return input;
    }

    @Override
    public MultiStackOutput mapToOutput(ItemStack input) {
        return output;
    }

}
