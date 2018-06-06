package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotRedstoneControl extends GuiSlot {

    private static final String CYCLE_STR = ChatColor.GRAY + "Click to cycle through control modes.";

    private final MachineComponentRedstone component;
    private final ItemStack stack;

    public SlotRedstoneControl(MachineComponentRedstone component) {
        this.component = component;
        this.stack = new ItemStack(Material.SULPHUR);
    }

    @Override
    public ItemStack stack() {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Redstone Control");
        switch (component.getState()) {
            case IGNORE:
                stack.setType(Material.SULPHUR);
                meta.setLore(Arrays.asList(
                        ChatColor.GREEN + "Ignored: " + ChatColor.RESET + "always active.",
                        CYCLE_STR));
                break;
            case NORMAL:
                stack.setType(Material.REDSTONE);
                meta.setLore(Arrays.asList(
                        ChatColor.GREEN + "High: " + ChatColor.RESET + "active with signal.",
                        CYCLE_STR));
                break;
            case INVERT:
                stack.setType(Material.REDSTONE_TORCH_ON);
                meta.setLore(Arrays.asList(
                        ChatColor.GREEN + "Low: " + ChatColor.RESET + "active without signal.",
                        CYCLE_STR));
                break;
        }
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public boolean onInteract(Player player, InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT)
            component.cycleState();
        return false;
    }

}
