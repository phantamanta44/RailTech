package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain.Module;
import io.github.phantamanta44.mcrail.railtech.tool.item.ItemBattery;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ToolModule implements Module {

    @Override
    public void registerItems() {
        Rail.itemRegistry().register("railtech:tol-battery-lv",
                new ItemBattery(ChatColor.RESET + "Light Battery", Material.CAULDRON_ITEM, 320000, 512));
    }

    @Override
    public void registerRecipes() {
        // TODO recipes
    }

}
