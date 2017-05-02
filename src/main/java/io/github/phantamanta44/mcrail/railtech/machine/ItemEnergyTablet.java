package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.railflux.item.ItemLastLoreEnergy;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ItemEnergyTablet extends ItemLastLoreEnergy {

    public ItemEnergyTablet() {
        super(400000);
        characteristics.add(new CharName(ChatColor.AQUA + "Energy Tablet"));
    }

    @Override
    public Material material() {
        return Material.BOOK;
    }

}
