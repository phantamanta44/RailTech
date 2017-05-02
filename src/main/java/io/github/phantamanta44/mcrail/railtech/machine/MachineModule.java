package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import org.bukkit.ChatColor;

public class MachineModule {

    public static void init() {
        Rail.itemRegistry().register("railtech:mac-energyTablet", new ItemEnergyTablet());
        // TODO Energy tablet recipe
        Rail.signRegistry().register(
                "railtech:mac-charger",
                ChatColor.RESET + "Energetic Infuser",
                TileCharger::new);
        Rail.recipes().register(new RailRecipe()
                .line(" t ").line("cfc").line("ege")
                .ingredient('t', "railtech:mac-energyTablet")
                .ingredient('c', "railtech:res-circuit0")
                .ingredient('f', "railtech:res-steelCasing")
                .ingredient('e', "railtech:res-alloy0")
                .ingredient('g', "railtech:res-goldDust")
                .withResult("railtech:mac-charger"));
    }

}
