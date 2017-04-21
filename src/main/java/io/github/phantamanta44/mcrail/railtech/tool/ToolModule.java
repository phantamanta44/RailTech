package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import io.github.phantamanta44.mcrail.railflux.EnergyItem;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Function;

public class ToolModule {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ToolDamageHandler(), RTMain.INSTANCE);
        Rail.recipes().register(genRecipe(Material.DIAMOND_AXE, "Energized Diamond Axe", 128000));
        Rail.recipes().register(genRecipe(Material.DIAMOND_PICKAXE, "Energized Diamond Pickaxe", 128000));
        Rail.recipes().register(genRecipe(Material.DIAMOND_SWORD, "Energized Diamond Sword", 128000));
        Rail.recipes().register(genRecipe(Material.DIAMOND_HOE, "Energized Diamond Hoe", 128000));
        Rail.recipes().register(genRecipe(Material.DIAMOND_SPADE, "Energized Diamond Shovel", 128000));
    }
    
    private static RailRecipe genRecipe(Material type, String name, int charge) {
        return new RailRecipe()
                .line("rrr").line("rtr").line("rpr")
                .ingredient('r', Material.REDSTONE_BLOCK)
                .ingredient('t', s -> ItemUtils.isNotNully(s)
                        && s.getType() == type
                        && AdapterUtils.adapt(EnergyItem.class, s) == null)
                .ingredient('p', Material.PISTON_BASE)
                .withResult(genResult(type, name, charge));
    }
    
    private static Function<ItemStack[], ItemStack> genResult(Material type, String name, int charge) {
        return mat -> {
            ItemStack stack = new ItemStack(type, 1, mat[4].getDurability());
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + name);
            stack.setItemMeta(meta);
            EnergyItem.energize(stack, charge, charge);
            return stack;
        };
    }

}
