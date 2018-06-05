package io.github.phantamanta44.mcrail.railtech.machine.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.function.Supplier;

public class SlotProcessingProgress extends GuiSlot {

    private final Supplier<Float> source;
    private final ItemStack stack;
    private final String actionText;

    public SlotProcessingProgress(Supplier<Float> source, Material material, String actionText) {
        this.source = source;
        this.stack = new ItemStack(material);
        this.actionText = actionText;
    }

    @Override
    public ItemStack stack() {
        float percent = source.get();
        stack.setDurability((short)Math.max(
                (int)Math.floor((stack.getType().getMaxDurability() - 1) * (1 - percent)), 1));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(percent >= 0.01F
                ? ChatColor.AQUA + ChatColor.BOLD.toString() + actionText
                : ChatColor.BLUE + ChatColor.BOLD.toString() + "Idle");
        if (percent >= 0.01F)
            meta.setLore(Collections.singletonList(ChatColor.GRAY + String.format("%.0f%%", percent * 100)));
        else
            meta.setLore(Collections.emptyList());
        stack.setItemMeta(meta);
        return stack;
    }

}
