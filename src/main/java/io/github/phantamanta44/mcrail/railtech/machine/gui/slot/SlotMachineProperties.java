package io.github.phantamanta44.mcrail.railtech.machine.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotMachineProperties extends GuiSlot {

    private final Runnable resetCallback;
    private final ItemStack stack;
    private boolean resetting;

    public SlotMachineProperties(Runnable resetCallback) {
        this.resetCallback = resetCallback;
        this.resetting = false;
        this.stack = new ItemStack(Material.WATCH);
    }

    @Override
    public ItemStack stack() {
        if (resetting) {
            stack.setType(Material.TNT);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Resetting Machine!");
            meta.setLore(Arrays.asList(
                    ChatColor.RED + "Are you sure you want to reset the machine?",
                    ChatColor.RED + "Upgrades won't be refunded and you cannot",
                    ChatColor.RED + "undo this action afterwards!",
                    ChatColor.GRAY + "Left-click to cancel.",
                    ChatColor.DARK_GRAY + "Right-click to confirm."));
            stack.setItemMeta(meta);
        } else {
            stack.setType(Material.WATCH);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Machine Properties");
            meta.setLore(Arrays.asList(
                    ChatColor.GREEN + "This... is a pulley.", // TODO display machine properties
                    ChatColor.GRAY + "Right-click to reset machine."));
            stack.setItemMeta(meta);
        }
        return stack;
    }

    @Override
    public boolean onInteract(Player player, InventoryClickEvent event) {
        if (resetting) {
            if (event.getClick() == ClickType.RIGHT) {
                resetCallback.run();
                resetting = false;
            } else if (event.getClick() == ClickType.LEFT) {
                resetting = false;
            }
        } else if (event.getClick() == ClickType.RIGHT) {
            resetting = true;
        }
        return false;
    }


}