package io.github.phantamanta44.mcrail.railtech;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.module.IRailModule;
import io.github.phantamanta44.mcrail.railtech.energetics.EnergeticsModule;
import io.github.phantamanta44.mcrail.railtech.machine.MachineModule;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.IRecipeList;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.ItemStackInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.ItemStackOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.SmeltingRecipe;
import io.github.phantamanta44.mcrail.railtech.resource.ResourceModule;
import io.github.phantamanta44.mcrail.railtech.tool.ToolModule;
import io.github.phantamanta44.mcrail.railtech.transport.TransportModule;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class RTMain extends JavaPlugin implements IRailModule {

    private static final Collection<Module> MODULES = Arrays.asList(
            new ResourceModule(), new MachineModule(), new EnergeticsModule(), new TransportModule(), new ToolModule()
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

    @Override
    public void phasePostConference() {
        IRecipeList<ItemStack, ItemStackInput, ItemStackOutput, SmeltingRecipe> recipeList
                = RTRecipes.forType(SmeltingRecipe.class);
        Iterator<Recipe> recipes = Bukkit.getServer().recipeIterator();
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();
            if (recipe instanceof FurnaceRecipe)
                recipeList.add(new SmeltingRecipe((FurnaceRecipe)recipe));
        }
        Rail.recipes().smeltRecipes().forEach(r -> recipeList.add(new SmeltingRecipe(r)));
    }

    public interface Module {

        void registerItems();

        void registerRecipes();

    }

}
