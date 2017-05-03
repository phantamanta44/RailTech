package io.github.phantamanta44.mcrail.railtech.machine.tile;

import io.github.phantamanta44.mcrail.railtech.common.recipe.impl.MaceratorRecipe;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class TileMacerator extends TileBasicProcessing<Pair<ItemStack, ItemStack>> {

    public TileMacerator(Block block) {
        super(block, "railtech:mac-macerator", "Macerator", MaceratorRecipe.TYPE, 20, 200, 20000);
    }

    @Override
    public void init() {
        // NO-OP
    }

}
