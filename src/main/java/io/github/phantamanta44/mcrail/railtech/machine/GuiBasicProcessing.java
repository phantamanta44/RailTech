package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotProgress;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import org.bukkit.entity.Player;

public class GuiBasicProcessing<T> extends GuiInventory {

    private final TileBasicProcessing<T> tile;

    public GuiBasicProcessing(String name, Player player, TileBasicProcessing<T> tile) {
        super(4, name, player, tile.inv);
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(11, genSlot(0));
        slot(13, genSlot(1));
        if (tile.recipeType.outputType() == Pair.class)
            slot(22, genSlot(2));
        slot(24, genSlot(3));
        //slot(20, genSlot(4)); TODO Secondary input
        slot(12, new SlotProgress(tile::completion));
        slot(15, new SlotEnergyMeter(tile));
    }

}
