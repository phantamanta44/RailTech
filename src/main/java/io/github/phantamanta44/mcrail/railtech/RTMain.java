package io.github.phantamanta44.mcrail.railtech;

import io.github.phantamanta44.mcrail.module.IRailModule;
import io.github.phantamanta44.mcrail.railtech.common.energy.EnergyNetworkManager;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.resource.ResourceModule;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class RTMain extends JavaPlugin implements IRailModule {

    public static RTMain INSTANCE;

    private EnergyNetworkManager netMan;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public void phaseRegistration() {
        ResourceModule.registerItems();
    }

    @Override
    public void phasePostRegistration() {
        RTRecipes.init();
        ResourceModule.registerRecipes();
        Bukkit.getServer().getPluginManager().registerEvents(netMan = new EnergyNetworkManager(), this);
    }

    public EnergyNetworkManager netMan() {
        return netMan;
    }

}
