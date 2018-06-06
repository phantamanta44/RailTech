package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain.Module;
import io.github.phantamanta44.mcrail.railtech.tool.item.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ToolModule implements Module {

    @Override
    public void registerItems() {
        // Electrical tools
        Rail.itemRegistry().register("railtech:tol-electric-pick",
                new ItemElectricTool(Material.IRON_PICKAXE, ChatColor.RESET + "ePickaxe",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-pick-d",
                new ItemElectricTool(Material.DIAMOND_PICKAXE, ChatColor.RESET + "ePickaxe+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-axe",
                new ItemElectricTool(Material.IRON_AXE, ChatColor.RESET + "eHatchet",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-axe-d",
                new ItemElectricTool(Material.DIAMOND_AXE, ChatColor.RESET + "eHatchet+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-sword",
                new ItemElectricTool(Material.IRON_SWORD, ChatColor.RESET + "eBlade",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-sword-d",
                new ItemElectricTool(Material.DIAMOND_SWORD, ChatColor.RESET + "eBlade+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-spade",
                new ItemElectricTool(Material.IRON_SPADE, ChatColor.RESET + "eSpade",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-spade-d",
                new ItemElectricTool(Material.DIAMOND_SPADE, ChatColor.RESET + "eSpade+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-hoe",
                new ItemElectricTool(Material.IRON_HOE, ChatColor.RESET + "eHoe",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-hoe-d",
                new ItemElectricTool(Material.DIAMOND_HOE, ChatColor.RESET + "eHoe+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-bow",
                new ItemElectricUtility(Material.BOW, ChatColor.RESET + "eBow",
                        512000, 4000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-rod",
                new ItemElectricUtility(Material.FISHING_ROD, ChatColor.RESET + "eRod",
                        512000, 4000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-helm",
                new ItemElectricUtility(Material.IRON_HELMET, ChatColor.RESET + "eVisor",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-helm-d",
                new ItemElectricUtility(Material.DIAMOND_HELMET, ChatColor.RESET + "eVisor+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-chest",
                new ItemElectricUtility(Material.IRON_CHESTPLATE, ChatColor.RESET + "eChestplate",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-chest-d",
                new ItemElectricUtility(Material.DIAMOND_CHESTPLATE, ChatColor.RESET + "eChestplate+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-legs",
                new ItemElectricUtility(Material.IRON_LEGGINGS, ChatColor.RESET + "eLegs",
                        320000, 4000, 150));
        Rail.itemRegistry().register("railtech:tol-electric-legs-d",
                new ItemElectricUtility(Material.DIAMOND_LEGGINGS, ChatColor.RESET + "eLegs+",
                        720000, 8000, 250));
        Rail.itemRegistry().register("railtech:tol-electric-boots", new ItemLongFallBoots());

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
