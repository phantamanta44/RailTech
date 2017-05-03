package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.function.Supplier;

public class SlotProgress extends GuiSlot {

    private final Supplier<Float> source;

    public SlotProgress(Supplier<Float> source) {
        this.source = source;
    }

    @Override
    public ItemStack stack() {
        float percent = source.get();
        ItemStack stack = new ItemStack(Material.INK_SACK, Math.max((int)(percent * 10), 1),
                percent < 0.01F ? (short)8
                        : percent < 0.25F ? (short)1
                        : percent < 0.5F ? (short)14
                        : percent < 0.75F ? (short)11 : (short)10);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(percent > 0.01F
                ? ChatColor.AQUA + ChatColor.BOLD.toString() + "Processing..."
                : ChatColor.GRAY + ChatColor.BOLD.toString() + "Not Processing");
        if (percent >= 0.01F)
            meta.setLore(Collections.singletonList(ChatColor.GRAY + String.format("%.0f%%", percent * 100)));
        stack.setItemMeta(meta);
        return stack;
    }

}
