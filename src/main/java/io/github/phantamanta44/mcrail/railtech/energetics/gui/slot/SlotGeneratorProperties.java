package io.github.phantamanta44.mcrail.railtech.energetics.gui.slot;

import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotReset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotGeneratorProperties extends SlotReset {

    private final ItemStack stack;

    public SlotGeneratorProperties(Runnable resetCallback) {
        super(resetCallback,
                ChatColor.RED + "Are you sure you want to reset the generator?",
                ChatColor.RED + "Upgrades won't be refunded and you cannot",
                ChatColor.RED + "undo this action afterwards!");
        this.stack = new ItemStack(Material.COMPASS);
    }

    @Override
    protected ItemStack getStack() {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Generator Properties");
        meta.setLore(Arrays.asList(
                ChatColor.GREEN + "This... is a pulley.", // TODO display generator properties
                ChatColor.GRAY + "Right-click to reset generator."));
        stack.setItemMeta(meta);
        return stack;
    }

}
