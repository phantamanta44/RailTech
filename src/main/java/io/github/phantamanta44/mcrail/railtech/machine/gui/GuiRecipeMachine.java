package io.github.phantamanta44.mcrail.railtech.machine.gui;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import io.github.phantamanta44.mcrail.railtech.machine.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.machine.gui.slot.SlotMachineProperties;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileRecipeMachine;
import org.bukkit.entity.Player;

public abstract class GuiRecipeMachine<T extends TileRecipeMachine> extends GuiInventory {

    protected final T tile;

    public GuiRecipeMachine(String title, Player player, T tile) {
        super(6, title, player, tile.getInventory());
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(8, new SlotEnergyMeter(tile));
        slot(17, genSlot(0));
        slot(26, new SlotMachineProperties(tile::reset));
        slot(35, genSlot(1));
        slot(44, new SlotRedstoneControl((MachineComponentRedstone)tile.machineCore.get("redstone")));
    }

}