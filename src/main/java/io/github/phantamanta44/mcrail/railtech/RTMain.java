package io.github.phantamanta44.mcrail.railtech;

import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.resource.ResourceModule;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class RTMain extends JavaPlugin {

    public static RTMain INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        RTRecipes.init();
        ResourceModule.init();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}
