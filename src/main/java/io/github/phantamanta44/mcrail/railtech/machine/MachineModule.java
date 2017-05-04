package io.github.phantamanta44.mcrail.railtech.machine;

import io.github.phantamanta44.mcrail.Rail;
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

        // Speed upgrade
        Rail.itemRegistry().register("railtech:mac-upgradeSpeed", new ItemResource(Material.DIAMOND_BARDING, "Speed Upgrade"));

        // Metallurgic infuser
        Rail.signRegistry().register(
                "railtech:mac-infuser",
                ChatColor.RESET + "Metallurgic Infuser",
                TileInfuser::new);
        InfuserRecipe.registerDefault();

        // Energetic infuser
        Rail.signRegistry().register(
                "railtech:mac-charger",
                ChatColor.RESET + "Energetic Infuser",
                TileCharger::new);

        // Macerator
        Rail.signRegistry().register(
                "railtech:mac-macerator",
                ChatColor.RESET + "Macerator",
                TileMacerator::new);
        MaceratorRecipe.registerDefault();
    }

}
