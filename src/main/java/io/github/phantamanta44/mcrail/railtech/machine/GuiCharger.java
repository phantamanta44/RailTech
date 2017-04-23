package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import org.bukkit.entity.Player;

public class GuiCharger extends GuiInventory {

    private final TileCharger tile;

    public GuiCharger(TileCharger tile, Player pl) {
        super(3, "Charging Apparatus", pl, tile.getInventory());
        this.tile = tile;
    }

    @Override
    public void init() {
        for (int i = 0; i < 5; i++)
            slot(10 + i, genSlot(i));
        slot(16, new SlotEnergyMeter(tile));
    }

}
