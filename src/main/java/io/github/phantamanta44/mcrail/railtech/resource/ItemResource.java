package io.github.phantamanta44.mcrail.railtech.resource;

import io.github.phantamanta44.mcrail.item.IItemBehaviour;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.item.characteristic.IItemCharacteristic;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collection;

public class ItemResource implements IItemBehaviour {

    private final Material material;
    private final Collection<IItemCharacteristic> characteristics;

    public ItemResource(Material material, String name) {
        this.material = material;
        this.characteristics = Arrays.asList(
                new CharName(ChatColor.BLACK + ChatColor.RESET.toString() + name));
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public Collection<IItemCharacteristic> characteristics() {
        return characteristics;
    }

}
