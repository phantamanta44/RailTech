package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.machine.resource.ItemMachine;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileGrinderLV;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class MachineModule implements RTMain.Module {

    @Override
    public void registerItems() {
        Rail.tileRegistry().register(
                new ItemMachine("railtech:mac-grinder-lv", ChatColor.RESET + "Electric Grinder", Material.DISPENSER),
                TileGrinderLV::new);
    }

    @Override
    public void registerRecipes() {
        // TODO machine module recipes
    }

}
