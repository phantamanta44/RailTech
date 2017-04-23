package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ToolModule {

    public static void init() {
        energyTool(Material.DIAMOND_PICKAXE, "ediapick", "Pickaxe", 250000);
        energyTool(Material.DIAMOND_AXE, "ediaaxe", "Axe", 250000);
        energyTool(Material.DIAMOND_HOE, "ediahoe", "Hoe", 250000);
        energyTool(Material.DIAMOND_SWORD, "ediasword", "Sword", 250000);
        energyTool(Material.DIAMOND_SPADE, "ediaspade", "Shovel", 250000);
        energyTool(Material.DIAMOND_SPADE, "elighter", "Igniter", 25000);
        energyTool(Material.DIAMOND_SPADE, "ebow", "Bow", 50000);
        energyTool(Material.DIAMOND_SPADE, "erod", "Rod", 25000);
        energyTool(Material.DIAMOND_SPADE, "eshears", "Shears", 50000);
    }

    private static void energyTool(Material material, String id, String name, int charge) {
        Rail.itemRegistry().register("railtech:" + id,
                new ItemEnergizedTool(material, "Energized " + name, charge));
        Rail.recipes().register(new RailRecipe()
                .line("ata").line("apa").line(" o ")
                .ingredient('a', Material.DIAMOND)
                .ingredient('t', Material.REDSTONE_COMPARATOR)
                .ingredient('p', material)
                .ingredient('o', Material.OBSIDIAN)
                .withResult(mat -> {
                    ItemStack stack = Rail.itemRegistry().create("railtech:" + id);
                    stack.setDurability(mat[4].getDurability());
                    return stack;
                }));
    }

}
