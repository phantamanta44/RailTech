package io.github.phantamanta44.mcrail.railtech.machine.recipe.output;

import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.inventory.ItemStack;

public class MultiStackOutput implements IMachineOutput {

    private final ItemStack prim, sec;
    private final float chance;

    public MultiStackOutput(ItemStack prim, ItemStack sec, float chance) {
        this.prim = prim;
        this.sec = sec;
        this.chance = chance;
    }

    public MultiStackOutput(ItemStack prim, ItemStack sec) {
        this(prim, sec, 1);
    }

    public MultiStackOutput(ItemStack output) {
        this(output, null, 0);
    }

    public ItemStack getPrimaryOutput() {
        return prim;
    }

    public ItemStack getSecondaryOutput() {
        return sec;
    }

    public float getChance() {
        return chance;
    }
    
    public boolean isAcceptable(ItemStack primStack, ItemStack secStack) {
        return (ItemUtils.isNully(primStack) || (ItemUtils.isMatch(primStack, getPrimaryOutput())
                        && primStack.getAmount() + getPrimaryOutput().getAmount() <= primStack.getMaxStackSize()))
                && (ItemUtils.isNully(secStack) || ItemUtils.isNully(getSecondaryOutput())
                        || (ItemUtils.isMatch(secStack, getSecondaryOutput())
                        && secStack.getAmount() + getSecondaryOutput().getAmount() <= secStack.getMaxStackSize()));
    }

}
