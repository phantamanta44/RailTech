package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.function.IntSupplier;

public class SlotBurnTime extends GuiSlot {

    private final IntSupplier source;

    public SlotBurnTime(IntSupplier source) {
        this.source = source;
    }

    @Override
    public ItemStack stack() {
        int time = source.getAsInt();
        ItemStack stack = new ItemStack(time > 0 ? Material.LAVA_BUCKET : Material.IRON_FENCE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Burn Time");
        meta.setLore(Collections.singletonList(
                ChatColor.GRAY + (time > 0 ? String.format("%d tick(s)", time) : "Nothing's burning!")));
        stack.setItemMeta(meta);
        return stack;
    }

}
