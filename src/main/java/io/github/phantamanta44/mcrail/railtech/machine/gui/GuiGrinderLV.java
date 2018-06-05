package io.github.phantamanta44.mcrail.railtech.machine.gui;

import io.github.phantamanta44.mcrail.railtech.machine.gui.slot.SlotProcessingProgress;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileGrinderLV;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GuiGrinderLV extends GuiRecipeMachine<TileGrinderLV> {

    public GuiGrinderLV(Player player, TileGrinderLV tile) {
        super(ChatColor.RESET + "Electric Grinder", player, tile);
    }

    @Override
    public void init() {
        super.init();
        slot(19, genSlot(2));
        slot(21, new SlotProcessingProgress(
                tile::getProcessingProgress, Material.IRON_PICKAXE, "Grinding..."));
        slot(23, genSlot(3));
        slot(32, genSlot(4));
    }

}
