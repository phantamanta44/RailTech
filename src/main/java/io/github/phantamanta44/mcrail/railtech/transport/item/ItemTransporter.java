package io.github.phantamanta44.mcrail.railtech.transport.item;

import io.github.phantamanta44.mcrail.item.ItemRailTile;
import org.bukkit.Material;

public class ItemTransporter extends ItemRailTile {

    public ItemTransporter(String id, String name, Material material, byte meta) {
        super("railtech:trn-" + id, name, material, meta);
    }

    public ItemTransporter(String id, String name, Material material) {
        super("railtech:trn-" + id, name, material, (byte)0);
    }
    
}
