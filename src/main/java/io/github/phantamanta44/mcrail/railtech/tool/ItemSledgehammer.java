package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.item.IItemBehaviour;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.item.characteristic.IItemCharacteristic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Collections;

public class ItemSledgehammer implements IItemBehaviour {

    private static final Collection<IItemCharacteristic> CHARS =
            Collections.singleton(new CharName(ChatColor.BLACK + ChatColor.RESET.toString() + "Sledgehammer"));

    @Override
    public Material material() {
        return Material.IRON_AXE;
    }

    @Override
    public Collection<IItemCharacteristic> characteristics() {
        return CHARS;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, ItemStack stack) {
        
    }

}
