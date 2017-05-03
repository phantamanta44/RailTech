package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.impl.InfuserRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.impl.MaceratorRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.item.ItemEnergyTablet;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileCharger;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileInfuser;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileMacerator;
import io.github.phantamanta44.mcrail.railtech.resource.item.ItemResource;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class MachineModule {

    public static void init() {
        // Energy tablet
        Rail.itemRegistry().register("railtech:mac-energyTablet", new ItemEnergyTablet());
        Rail.recipes().register(new RailRecipe()
                .line("rgr").line("aga").line("rgr")
                .ingredient('r', Material.REDSTONE)
                .ingredient('g', Material.GOLD_INGOT)
                .ingredient('a', "railtech:res-alloy0")
                .withResult("railtech:mac-energyTablet"));

        // Speed upgrade
        Rail.itemRegistry().register("railtech:mac-upgradeSpeed", new ItemResource(Material.DIAMOND_BARDING, "Speed Upgrade"));
        Rail.recipes().register(new RailRecipe()
                .line(" g ").line("ata").line(" g ")
                .ingredient('g', Material.GLASS)
                .ingredient('a', "railtech:res-alloy0")
                .ingredient('t', "railtech:res-dustTitanium")
                .withResult("railtech:mac-upgradeSpeed"));
        // TODO Energy updade?

        // Metallurgic infuser
        Rail.signRegistry().register(
                "railtech:mac-infuser",
                ChatColor.RESET + "Metallurgic Infuser",
                TileInfuser::new);
        Rail.recipes().register(new RailRecipe()
                .line("ifi").line("rtr").line("ifi")
                .ingredient('i', Material.IRON_INGOT)
                .ingredient('f', Material.FURNACE)
                .ingredient('r', Material.REDSTONE)
                .ingredient('t', "railtech:res-ingotTitanium")
                .withResult("railtech:mac-infuser"));
        InfuserRecipe.registerDefault();

        // Energetic infuser
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

        // Macerator
        Rail.signRegistry().register(
                "railtech:mac-macerator",
                ChatColor.RESET + "Macerator",
                TileMacerator::new);
        Rail.recipes().register(new RailRecipe()
                .line("rcr").line("isi").line("rcr")
                .ingredient('r', Material.REDSTONE)
                .ingredient('c', "railtech:res-circuit0")
                .ingredient('i', Material.IRON_INGOT)
                .ingredient('s', "railtech:res-steelCasing")
                .withResult("railtech:mac-macerator"));
        MaceratorRecipe.registerDefault();
    }

}
