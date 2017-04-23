package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class MachineModule {

    public static void init() {
        Rail.signRegistry().register(
                "railtech:mac-charger",
                ChatColor.RESET + "Charging Apparatus",
                TileCharger::new);
        Rail.recipes().register(new RailRecipe()
                .line(" b ").line("vav").line("rgr")
                .ingredient('b', Material.REDSTONE_BLOCK)
                .ingredient('v', Material.GLASS)
                .ingredient('a', Material.ANVIL)
                .ingredient('r', Material.REDSTONE)
                .ingredient('g', Material.GOLD_INGOT)
                .withResult("railtech:mac-charger"));
    }

}
