package io.github.phantamanta44.mcrail.railtech.generator;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class GeneratorModule {

    public static void init() {
        Rail.signRegistry().register(
                "railtech:gen-thermo",
                ChatColor.RESET + "Thermoelectric Generator",
                TileThermoGenerator::new);
        Rail.recipes().register(new RailRecipe()
                .line("bbb").line("fpf").line("rir")
                .ingredient('b', Material.CLAY_BRICK)
                .ingredient('f', Material.FURNACE)
                .ingredient('p', Material.PISTON_BASE)
                .ingredient('r', Material.REDSTONE)
                .ingredient('i', Material.IRON_INGOT)
                .withResult("railtech:gen-thermo"));
    }

}
