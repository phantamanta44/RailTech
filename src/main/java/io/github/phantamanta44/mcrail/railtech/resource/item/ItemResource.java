package io.github.phantamanta44.mcrail.railtech.resource.item;

import io.github.phantamanta44.mcrail.item.IItemBehaviour;
import io.github.phantamanta44.mcrail.item.characteristic.CharDamage;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.item.characteristic.IItemCharacteristic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ItemResource implements IItemBehaviour {

    private final Material material;
    private final Collection<IItemCharacteristic> characteristics;

    public ItemResource(Material material, String name) {
        this.material = material;
        this.characteristics = Collections.singletonList(
                new CharName(ChatColor.BLACK + ChatColor.RESET.toString() + name));
    }

    public ItemResource(Material material, String name, short data) {
        this.material = material;
        this.characteristics = Arrays.asList(
                new CharName(ChatColor.BLACK + ChatColor.RESET.toString() + name),
                new CharDamage(data));
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public Collection<IItemCharacteristic> characteristics() {
        return characteristics;
    }

    @Override
    public void onPlace(BlockPlaceEvent event, ItemStack stack) {
        event.setCancelled(true);
    }

}
