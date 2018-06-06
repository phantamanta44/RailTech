package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railflux.IEnergyContainer;
import io.github.phantamanta44.mcrail.railtech.util.NumberUtils;
import io.github.phantamanta44.mcrail.railtech.util.RunningAverage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotEnergyMeter extends GuiSlot {

    private final IEnergyContainer source;
    private final ItemStack stack;
    private final RunningAverage avgDiff;
    private int lastEnergy;

    public SlotEnergyMeter(IEnergyContainer source, Material material) {
        this.source = source;
        this.stack = new ItemStack(material);
        this.avgDiff = new RunningAverage(5);
        this.lastEnergy = source.energyStored();
    }

    @Override
    public ItemStack stack() {
        int energy = source.energyStored();
        int diff = avgDiff.calculateAndCycle(energy - lastEnergy);
        lastEnergy = energy;
        int energyMax = source.energyCapacity();
        float percent = (float)energy / (float)energyMax;
        stack.setDurability((short)Math.max(
                (int)Math.floor((stack.getType().getMaxDurability() - 1) * (1 - percent)), 1));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Energy");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY + String.format("%s / %s (%.1f%%)",
                        NumberUtils.formatSI(energy, "RJ"),
                        NumberUtils.formatSI(energyMax, "RJ"),
                        percent * 100),
                NumberUtils.colourForSign(diff) + (diff >= 0 ? "+" : "") + NumberUtils.formatSI(diff, "RJ/t")));
        stack.setItemMeta(meta);
        return stack;
    }

}
