package io.github.phantamanta44.mcrail.railtech.machine.gui;

import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileInfuser;
import org.bukkit.entity.Player;

public class GuiInfuser extends GuiInventory {

    private final TileInfuser tile;

    public GuiInfuser(String name, Player player, TileInfuser tile) {
        super(4, name, player, tile.inv());
        this.tile = tile;
    }

    @Override
    public void init() {
        // TODO Implement
    }

}
