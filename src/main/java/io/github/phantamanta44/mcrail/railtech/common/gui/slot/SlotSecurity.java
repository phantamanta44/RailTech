package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentSecurity;
import io.github.phantamanta44.mcrail.railtech.util.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class SlotSecurity extends GuiSlot {

    private final MachineComponentSecurity component;
    private final ItemStack stack;

    public SlotSecurity(MachineComponentSecurity component) {
        this.component = component;
        this.stack = new ItemStack(Material.SULPHUR);
    }

    @Override
    public ItemStack stack() {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Security");
        List<String> lore = new LinkedList<>();
        if (component.isEnforcing()) {
            stack.setType(Material.GOLD_CHESTPLATE);
            lore.add(ChatColor.GREEN + "Enforcing");
        } else {
            stack.setType(Material.IRON_CHESTPLATE);
            lore.add(ChatColor.RED + "Not Enforcing");
        }
        lore.add(ChatColor.RESET + "Registered:");
        for (UUID id : component.getPermissible())
            lore.add(ChatColor.DARK_GRAY + "- " + PlayerUtils.nameFromId(id));
        lore.add(ChatColor.GRAY + "Click to toggle enforcing status.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public boolean onInteract(Player player, InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT) {
            component.toggleEnforcing();
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
        return false;
    }

}
