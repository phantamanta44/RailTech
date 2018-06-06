package io.github.phantamanta44.mcrail.railtech.energetics.gui;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railtech.energetics.tile.TileSolarPanel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class GuiSolarPanel extends GuiGenerator<TileSolarPanel> {

    public GuiSolarPanel(Player player, TileSolarPanel tile) {
        super(ChatColor.RESET + "Solar Panel", player, tile);
    }

    @Override
    public void init() {
        super.init();
        slot(21, new SlotSolar(tile));
    }

    private static class SlotSolar extends GuiSlot {

        private final TileSolarPanel tile;
        private final ItemStack stack;

        public SlotSolar(TileSolarPanel tile) {
            this.tile = tile;
            this.stack = new ItemStack(Material.DOUBLE_PLANT);
        }

        @Override
        public ItemStack stack() {
            ItemMeta meta = stack.getItemMeta();
            switch (tile.getEnvironment()) {
                case HOSTILE:
                    stack.setType(Material.NETHER_STALK);
                    meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "No Sun");
                    meta.setLore(Collections.singletonList(
                            ChatColor.RESET + "This world is not conducive to solar power!"));
                    break;
                case OBSTRUCTED:
                    stack.setType(Material.CHAINMAIL_HELMET);
                    meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Sky Not Visible");
                    meta.setLore(Collections.singletonList(
                            ChatColor.RESET + "The panel cannot operate without sunlight!"));
                    break;
                case NIGHT:
                    stack.setType(Material.COAL);
                    meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Too Dark");
                    meta.setLore(Collections.singletonList(
                            ChatColor.RESET + "The panel cannot operate at night!"));
                    break;
                case NORMAL:
                    stack.setType(Material.DOUBLE_PLANT);
                    meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Operating");
                    meta.setLore(Collections.singletonList(
                            ChatColor.RESET + "The panel is operating normally."));
                    break;
                case RAINY:
                    stack.setType(Material.WATER_BUCKET);
                    meta.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Operating (Rain)");
                    meta.setLore(Collections.singletonList(
                            ChatColor.RESET + "The panel is operating below capacity."));
                    break;
                case STORM:
                    stack.setType(Material.BLAZE_POWDER);
                    meta.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Operating (Storm)");
                    meta.setLore(Collections.singletonList(
                            ChatColor.RESET + "The panel is operating significantly below capacity."));
                    break;
            }
            stack.setItemMeta(meta);
            return stack;
        }

    }

}
