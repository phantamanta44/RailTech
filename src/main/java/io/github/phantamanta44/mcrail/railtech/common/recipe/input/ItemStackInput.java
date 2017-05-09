package io.github.phantamanta44.mcrail.railtech.common.recipe.input;

import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

public class ItemStackInput implements IMachineInput<ItemStack> {

    private final Predicate<ItemStack> matcher;
    private final int amount;

    public ItemStackInput(Predicate<ItemStack> matcher, int amount) {
        this.matcher = matcher;
        this.amount = amount;
    }

    @Override
    public boolean matches(ItemStack input) {
        return input.getAmount() >= amount && matcher.test(input);
    }

    @Override
    public ItemStack consume(ItemStack input) {
        input.setAmount(input.getAmount() - amount);
        return ItemUtils.isNully(input) ? null : input;
    }

}
