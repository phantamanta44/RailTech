package io.github.phantamanta44.mcrail.railtech.common.recipe.output;

import org.bukkit.inventory.ItemStack;

public class MultiStackOutput {

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

    // TODO Finish implementation

}
