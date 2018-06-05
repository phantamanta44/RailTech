package io.github.phantamanta44.mcrail.railtech.machine.resource;

import io.github.phantamanta44.mcrail.item.ItemRailTile;
import org.bukkit.Material;

public class ItemMachine extends ItemRailTile {

    public ItemMachine(String id, String name, Material material, byte meta) {
        super(id, name, material, meta);
    }

    public ItemMachine(String id, String name, Material material) {
        super(id, name, material, (byte)0);
    }

}
