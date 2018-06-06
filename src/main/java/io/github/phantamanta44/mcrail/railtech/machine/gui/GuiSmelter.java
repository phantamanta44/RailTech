package io.github.phantamanta44.mcrail.railtech.machine.gui;

import io.github.phantamanta44.mcrail.railtech.machine.gui.slot.SlotProcessingProgress;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileSmelter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GuiSmelter extends GuiRecipeMachine<TileSmelter> {

    public GuiSmelter(Player player, TileSmelter tile) {
        super(ChatColor.RESET + "Resistive Smelter", player, tile);
    }

    @Override
    public void init() {
        super.init();
        slot(19, genSlot(2));
        slot(21, new SlotProcessingProgress(
                tile::getProcessingProgress, Material.FLINT_AND_STEEL, "Smelting..."));
        slot(23, genSlot(3));
    }

}
