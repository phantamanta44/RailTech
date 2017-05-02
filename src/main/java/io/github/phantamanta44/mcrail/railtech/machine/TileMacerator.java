package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import org.bukkit.block.Block;

public class TileMacerator extends TileEnergized {

    public TileMacerator(Block block) {
        super(block, "railtech:mac-macerator", 20000);
    }

    @Override
    public void init() {
        // NO-OP
    }

    @Override
    public void tick() {
        // TODO Finish implementation
    }

}
