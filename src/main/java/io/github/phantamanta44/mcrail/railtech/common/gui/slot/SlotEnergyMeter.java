package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railflux.IEnergyContainer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class SlotEnergyMeter extends GuiSlot {

    private final IEnergyContainer source;

    public SlotEnergyMeter(IEnergyContainer source) {
        this.source = source;
    }

    @Override
    public ItemStack stack() {
        int energy = source.energyStored();
        int energyMax = source.energyCapacity();
        float percent = (float)energy / (float)energyMax;
        ItemStack stack = new ItemStack(percent < 0.01F
                ? Material.LEVER
                : percent < 0.5F ? Material.REDSTONE_TORCH_OFF : Material.REDSTONE_TORCH_ON);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Energy");
        meta.setLore(Collections.singletonList(
                ChatColor.GRAY + String.format("%d / %d RJ (%.1f%%)", energy, energyMax, percent)));
        stack.setItemMeta(meta);
        return stack;
    }

}
