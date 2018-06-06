package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.machine.item.ItemMachine;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileGrinder;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileSmelter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

public class MachineModule implements RTMain.Module {

    @Override
    public void registerItems() {
        Rail.tileRegistry().register(
                new ItemMachine("grinder", ChatColor.RESET + "Electric Grinder", Material.DISPENSER),
                TileGrinder::new);
        Rail.tileRegistry().register(
                new ItemMachine("smelter", ChatColor.RESET + "Resistive Smelter", Material.FURNACE),
                TileSmelter::new);
    }

    @Override
    public void registerRecipes() {
        // TODO machine module recipes
        // Smelter recipes
    }

}
