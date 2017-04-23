package io.github.phantamanta44.mcrail.railtech;

import io.github.phantamanta44.mcrail.railtech.generator.GeneratorModule;
import io.github.phantamanta44.mcrail.railtech.machine.MachineModule;
import io.github.phantamanta44.mcrail.railtech.tool.ToolModule;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class RTMain extends JavaPlugin {

    public static RTMain INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        GeneratorModule.init();
        MachineModule.init();
        ToolModule.init();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}
