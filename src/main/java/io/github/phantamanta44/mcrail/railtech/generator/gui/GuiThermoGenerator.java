package io.github.phantamanta44.mcrail.railtech.generator.gui;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotBurnTime;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.generator.tile.TileThermoGenerator;
import org.bukkit.entity.Player;

public class GuiThermoGenerator extends GuiInventory {

    private final TileThermoGenerator tile;

    public GuiThermoGenerator(TileThermoGenerator tile, Player pl) {
        super(3, "Heat Generator", pl, tile.getFuelInv());
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(11, genSlot(0));
        slot(13, new SlotBurnTime(tile::getBurnTime));
        slot(15, new SlotEnergyMeter(tile));
    }

}