package io.github.phantamanta44.mcrail.railtech.energetics.gui.slot;

import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotReset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotStorageProperties extends SlotReset {

    private final ItemStack stack;

    public SlotStorageProperties(Runnable resetCallback) {
        super(resetCallback,
                ChatColor.RED + "Are you sure you want to reset the storage unit?",
                ChatColor.RED + "Upgrades won't be refunded and you cannot",
                ChatColor.RED + "undo this action afterwards!");
        this.stack = new ItemStack(Material.COMPASS);
    }

    @Override
    protected ItemStack getStack() {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Storage Properties");
        meta.setLore(Arrays.asList(
                ChatColor.GREEN + "This... is a pulley.", // TODO display battery properties
                ChatColor.GRAY + "Right-click to reset storage unit."));
        stack.setItemMeta(meta);
        return stack;
    }

}
