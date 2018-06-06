package io.github.phantamanta44.mcrail.railtech.energetics.gui;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotRedstoneControl;
import io.github.phantamanta44.mcrail.railtech.energetics.gui.slot.SlotGeneratorProperties;
import io.github.phantamanta44.mcrail.railtech.energetics.tile.TileGenerator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class GuiGenerator<T extends TileGenerator> extends GuiInventory {

    protected final T tile;

    public GuiGenerator(String title, Player player, T tile) {
        super(5, title, player, tile.getInventory());
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(8, new SlotEnergyMeter(tile, Material.IRON_BOOTS));
        slot(17, new SlotGeneratorProperties(tile::reset));
        slot(26, genSlot(0));
        slot(35, new SlotRedstoneControl((MachineComponentRedstone)tile.machineCore.get("redstone")));
    }

}
