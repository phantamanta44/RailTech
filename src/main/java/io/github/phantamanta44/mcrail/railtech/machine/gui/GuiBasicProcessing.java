package io.github.phantamanta44.mcrail.railtech.machine.gui;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.UpgradeType;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotProgress;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotUpgrade;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileBasicProcessing;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import org.bukkit.entity.Player;

public class GuiBasicProcessing<T> extends GuiInventory {

    private final TileBasicProcessing<T> tile;

    public GuiBasicProcessing(String name, Player player, TileBasicProcessing<T> tile) {
        super(4, name, player, tile.inv());
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(10, genSlot(0));
        slot(12, genSlot(1));
        if (tile.recipeType().outputType() == Pair.class)
            slot(21, genSlot(2));
        slot(23, genSlot(3));
        //slot(19, genSlot(4)); TODO Secondary input
        slot(25, genSlot(5));
        slot(11, new SlotProgress(tile::completion));
        slot(14, new SlotEnergyMeter(tile));
        slot(16, new SlotUpgrade(Rail.itemRegistry().create("railtech:mac-upgradeSpeed"),
                () -> tile.upgrades(UpgradeType.SPEED), () -> tile.offsetUpgrades(UpgradeType.SPEED, -1)));
    }

}
