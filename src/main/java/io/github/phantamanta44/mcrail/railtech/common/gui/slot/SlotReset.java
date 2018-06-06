package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SlotReset extends GuiSlot {

    private final Runnable resetCallback;
    private final ItemStack stack;
    private boolean resetting;

    public SlotReset(Runnable resetCallback, String... resetText) {
        this.resetCallback = resetCallback;
        this.stack = new ItemStack(Material.TNT);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Resetting!");
        meta.setLore(Stream.concat(Arrays.stream(resetText), Stream.of(
                ChatColor.GRAY + "Left-click to cancel.",
                ChatColor.DARK_GRAY + "Right-click to confirm.")).collect(Collectors.toList()));
        stack.setItemMeta(meta);
        this.resetting = false;
    }

    @Override
    public ItemStack stack() {
        return resetting ? stack : getStack();
    }

    protected abstract ItemStack getStack();

    @Override
    public boolean onInteract(Player player, InventoryClickEvent event) {
        if (resetting) {
            if (event.getClick() == ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.GLASS, 1F, 0.7F);
                resetCallback.run();
                resetting = false;
            } else if (event.getClick() == ClickType.LEFT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1.2F);
                resetting = false;
            }
        } else if (event.getClick() == ClickType.RIGHT) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            resetting = true;
        }
        return false;
    }

}
