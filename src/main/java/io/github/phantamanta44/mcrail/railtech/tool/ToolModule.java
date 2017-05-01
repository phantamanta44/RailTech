package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ToolModule {

    public static void init() {
        Rail.itemRegistry().register("railtech:tool-sledgehammer", new ItemSledgehammer());
        Rail.recipes().register(new RailRecipe()
                .line("iii").line("isi").line(" s ")
                .ingredient('i', Material.IRON_INGOT)
                .ingredient('s', Material.STICK)
                .withResult(m -> {
                    ItemStack stack = Rail.itemRegistry().create("railtech:tool-sledgehammer");
                    stack.setDurability((short)235);
                    return stack;
                }));
        energyTool(Material.DIAMOND_PICKAXE, "epick", "Pickaxe", 250000);
        energyTool(Material.DIAMOND_AXE, "eaxe", "Axe", 250000);
        energyTool(Material.DIAMOND_HOE, "ehoe", "Hoe", 250000);
        energyTool(Material.DIAMOND_SWORD, "esword", "Sword", 250000);
        energyTool(Material.DIAMOND_SPADE, "espade", "Shovel", 250000);
        energyTool(Material.FLINT_AND_STEEL, "elighter", "Igniter", 25000);
        energyTool(Material.BOW, "ebow", "Bow", 50000);
        energyTool(Material.FISHING_ROD, "erod", "Rod", 25000);
        energyTool(Material.SHEARS, "eshears", "Shears", 50000);
    }

    private static void energyTool(Material material, String id, String name, int charge) {
        Rail.itemRegistry().register("railtech:tool-" + id,
                new ItemEnergizedTool(material, "Energized " + name, charge));
        Rail.recipes().register(new RailRecipe()
                .line("ata").line("apa").line(" o ")
                .ingredient('a', Material.DIAMOND)
                .ingredient('t', Material.REDSTONE_COMPARATOR)
                .ingredient('p', material)
                .ingredient('o', Material.OBSIDIAN)
                .withResult(mat -> {
                    ItemStack stack = Rail.itemRegistry().create("railtech:tool-" + id);
                    stack.setDurability(mat[4].getDurability());
                    return stack;
                }));
    }

}
