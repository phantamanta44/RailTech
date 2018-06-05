package io.github.phantamanta44.mcrail.railtech;

import io.github.phantamanta44.mcrail.module.IRailModule;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.machine.MachineModule;
import io.github.phantamanta44.mcrail.railtech.resource.ResourceModule;
import io.github.phantamanta44.mcrail.railtech.tool.ToolModule;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;

public class RTMain extends JavaPlugin implements IRailModule {

    private static final Collection<Module> MODULES = Arrays.asList(
            new ResourceModule(), new MachineModule(), new ToolModule()
    );

    public static RTMain INSTANCE;

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
        MODULES.forEach(Module::registerItems);
    }

    @Override
    public void phasePostRegistration() {
        RTRecipes.init();
        MODULES.forEach(Module::registerRecipes);
    }

    public interface Module {

        void registerItems();

        void registerRecipes();

    }

}
