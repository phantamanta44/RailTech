package io.github.phantamanta44.mcrail.railtech.energetics.gui;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentSecurity;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotRedstoneControl;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotSecurity;
import io.github.phantamanta44.mcrail.railtech.energetics.gui.slot.SlotStorageProperties;
import io.github.phantamanta44.mcrail.railtech.energetics.tile.TileBattery;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GuiBattery extends GuiInventory {

    private final TileBattery tile;

    public GuiBattery(Player player, TileBattery tile) {
        super(4, tile.name, player, tile.getInventory());
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(8, new SlotStorageProperties(tile::reset));
        slot(10, genSlot(0));
        slot(12, new SlotEnergyMeter(tile, Material.DIAMOND_BOOTS));
        slot(14, genSlot(1));
        slot(17, genSlot(2));
        slot(26, new SlotRedstoneControl((MachineComponentRedstone)tile.machineCore.get("redstone")));
        slot(35, new SlotSecurity((MachineComponentSecurity)tile.machineCore.get("security")));
    }

}
