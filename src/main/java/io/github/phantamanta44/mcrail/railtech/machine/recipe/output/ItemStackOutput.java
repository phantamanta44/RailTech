package io.github.phantamanta44.mcrail.railtech.machine.recipe.output;

import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.inventory.ItemStack;

public class ItemStackOutput implements IMachineOutput {

    private final ItemStack stack;

    public ItemStackOutput(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getOutput() {
        return stack;
    }
    
    public boolean isAcceptable(ItemStack stack) {
        return (ItemUtils.isNully(stack) || (ItemUtils.isMatch(stack, getOutput())
                && stack.getAmount() + getOutput().getAmount() <= stack.getMaxStackSize()));
    }

}
