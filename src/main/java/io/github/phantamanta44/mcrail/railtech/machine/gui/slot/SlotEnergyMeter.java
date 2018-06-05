package io.github.phantamanta44.mcrail.railtech.machine.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railflux.IEnergyContainer;
import io.github.phantamanta44.mcrail.railtech.util.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class SlotEnergyMeter extends GuiSlot {

    private final IEnergyContainer source;
    private final ItemStack stack;

    public SlotEnergyMeter(IEnergyContainer source) {
        this.source = source;
        this.stack = new ItemStack(Material.GOLD_BOOTS);
    }

    @Override
    public ItemStack stack() {
        int energy = source.energyStored();
        int energyMax = source.energyCapacity();
        float percent = (float)energy / (float)energyMax;
        stack.setDurability((short)Math.max(
                (int)Math.floor((Material.GOLD_BOOTS.getMaxDurability() - 1) * (1 - percent)), 1));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Energy");
        meta.setLore(Collections.singletonList(
                ChatColor.GRAY + String.format("%s / %s (%.1f%%)",
                        NumberUtils.formatSI(energy, "RJ"),
                        NumberUtils.formatSI(energyMax, "RJ"),
                        percent * 100)));
        stack.setItemMeta(meta);
        return stack;
    }

}
