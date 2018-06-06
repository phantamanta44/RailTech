package io.github.phantamanta44.mcrail.railtech.energetics;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.energetics.item.ItemEnergeticMachine;
import io.github.phantamanta44.mcrail.railtech.energetics.tile.TileSolarPanel;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class EnergeticsModule implements RTMain.Module {

    @Override
    public void registerItems() {
        Rail.tileRegistry().register(
                new ItemEnergeticMachine("gen-solar", ChatColor.RESET + "Solar Panel", Material.ENDER_PORTAL_FRAME),
                TileSolarPanel::new);
    }

    @Override
    public void registerRecipes() {
        // TODO recipes
    }

}
