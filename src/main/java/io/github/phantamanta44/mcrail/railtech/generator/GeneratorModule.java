package io.github.phantamanta44.mcrail.railtech.generator;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import io.github.phantamanta44.mcrail.railtech.generator.tile.TileThermoGenerator;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class GeneratorModule {

    public static void init() {
        Rail.signRegistry().register(
                "railtech:gen-heat",
                ChatColor.RESET + "Heat Generator",
                TileThermoGenerator::new);
        Rail.recipes().register(new RailRecipe()
                .line("iii").line("wtw").line("cfc")
                .ingredient('i', Material.IRON_INGOT)
                .ingredient('w', Material.WOOD)
                .ingredient('t', "railtech:res-ingotTitanium")
                .ingredient('c', "railtech:res-ingotCopper")
                .ingredient('f', Material.FURNACE)
                .withResult("railtech:gen-heat"));
    }

}
