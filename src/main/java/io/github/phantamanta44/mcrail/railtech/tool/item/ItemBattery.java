package io.github.phantamanta44.mcrail.railtech.tool.item;

import io.github.phantamanta44.mcrail.item.characteristic.CharLore;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.railtech.common.item.ItemEnergizedRated;
import io.github.phantamanta44.mcrail.railtech.util.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemBattery extends ItemEnergizedRated {

    private final Material material;

    public ItemBattery(String name, Material material, int energyMax, int rateInOut) {
        super(energyMax, rateInOut);
        this.material = material;
        this.characteristics.add(new CharName(name));
        this.characteristics.add(new CharLore(1, ChatColor.GRAY + NumberUtils.formatSI(rateInOut, "RJ/t")));
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public boolean onUse(PlayerInteractEvent event, ItemStack stack) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR)
            wrap(stack).offerEnergyRaw(320000);
        return false;
    }

}
