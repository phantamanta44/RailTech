package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.railtech.RTMain;
import org.bukkit.Bukkit;

public class ToolModule {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ToolDamageHandler(), RTMain.INSTANCE);
    }

}
