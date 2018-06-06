package io.github.phantamanta44.mcrail.railtech.machine.gui.slot;

import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotReset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotMachineProperties extends SlotReset {

    private final ItemStack stack;

    public SlotMachineProperties(Runnable resetCallback) {
        super(resetCallback,
                ChatColor.RED + "Are you sure you want to reset the machine?",
                ChatColor.RED + "Upgrades won't be refunded and you cannot",
                ChatColor.RED + "undo this action afterwards!");
        this.stack = new ItemStack(Material.WATCH);
    }

    @Override
    protected ItemStack getStack() {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Machine Properties");
        meta.setLore(Arrays.asList(
                ChatColor.GREEN + "This... is a pulley.", // TODO display machine properties
                ChatColor.GRAY + "Right-click to reset machine."));
        stack.setItemMeta(meta);
        return stack;
    }

}
