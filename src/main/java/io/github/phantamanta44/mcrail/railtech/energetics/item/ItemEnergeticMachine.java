package io.github.phantamanta44.mcrail.railtech.energetics.item;

import io.github.phantamanta44.mcrail.item.ItemRailTile;
import org.bukkit.Material;

public class ItemEnergeticMachine extends ItemRailTile {

    public ItemEnergeticMachine(String id, String name, Material material, byte meta) {
        super("railtech:enr-" + id, name, material, meta);
    }

    public ItemEnergeticMachine(String id, String name, Material material) {
        super("railtech:enr-" + id, name, material, (byte)0);
    }

}
