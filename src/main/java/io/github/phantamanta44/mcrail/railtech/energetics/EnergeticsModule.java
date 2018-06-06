package io.github.phantamanta44.mcrail.railtech.energetics;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.energetics.item.ItemEnergeticMachine;
import io.github.phantamanta44.mcrail.railtech.energetics.tile.TileBattery;
import io.github.phantamanta44.mcrail.railtech.energetics.tile.TileSolarPanel;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class EnergeticsModule implements RTMain.Module {

    @Override
    public void registerItems() {
        // Generators
        Rail.tileRegistry().register(
                new ItemEnergeticMachine("gen-solar", ChatColor.RESET + "Solar Panel", Material.LAPIS_BLOCK),
                TileSolarPanel::new);

        // Energy storage units
        Rail.tileRegistry().register(
                new ItemEnergeticMachine("bat-l", ChatColor.RESET + "Light Energy Storage Unit",
                        Material.GOLD_BLOCK),
                b -> new TileBattery(b, "railtech:enr-bat-l", ChatColor.RESET + "Light ESU", 1280000, 3000));
        Rail.tileRegistry().register(
                new ItemEnergeticMachine("bat-m", ChatColor.RESET + "Medium Energy Storage Unit",
                        Material.DIAMOND_BLOCK),
                b -> new TileBattery(b, "railtech:enr-bat-m", ChatColor.RESET + "Medium ESU", 4000000, 8000));
        Rail.tileRegistry().register(
                new ItemEnergeticMachine("bat-h", ChatColor.AQUA + "Heavy Energy Storage Unit",
                        Material.SEA_LANTERN),
                b -> new TileBattery(b, "railtech:enr-bat-h", ChatColor.RESET + "Heavy ESU", 36000000, 50000));
        Rail.tileRegistry().register(
                new ItemEnergeticMachine("bat-e", ChatColor.LIGHT_PURPLE + "Omega-Klein Energy Storage Unit",
                        Material.BEACON),
                b -> new TileBattery(b, "railtech:enr-bat-e", ChatColor.RESET + "Omega-Klein ESU", 204800000, 200000));
    }

    @Override
    public void registerRecipes() {
        // TODO recipes
    }

}
