package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain.Module;
import io.github.phantamanta44.mcrail.railtech.tool.item.ItemBattery;
import io.github.phantamanta44.mcrail.railtech.tool.item.ItemBiometricCard;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ToolModule implements Module {

    @Override
    public void registerItems() {
        // Biometric card
        Rail.itemRegistry().register("railtech:tol-biocard", new ItemBiometricCard());

        // Batteries
        Rail.itemRegistry().register("railtech:tol-battery-l",
                new ItemBattery(ChatColor.RESET + "Light Battery",
                        Material.CAULDRON_ITEM, 320000, 768));
        Rail.itemRegistry().register("railtech:tol-battery-m",
                new ItemBattery(ChatColor.RESET + "Medium Battery",
                        Material.BREWING_STAND_ITEM, 1000000, 2000));
        Rail.itemRegistry().register("railtech:tol-battery-h",
                new ItemBattery(ChatColor.AQUA + "Heavy Battery",
                        Material.POWERED_MINECART, 9000000, 16000));
        Rail.itemRegistry().register("railtech:tol-battery-e",
                new ItemBattery(ChatColor.LIGHT_PURPLE + "Omega-Klein Battery",
                        Material.NETHER_STAR, 51200000, 50000));
    }

    @Override
    public void registerRecipes() {
        // TODO recipes
    }

}
