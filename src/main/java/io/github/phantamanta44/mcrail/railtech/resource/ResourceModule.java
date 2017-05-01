package io.github.phantamanta44.mcrail.railtech.resource;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailSmeltRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.impl.MaceratorRecipe;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ResourceModule {

    public static void init() {
        Rail.itemRegistry().register("railtech:res-ingotCopper", new ItemResource(Material.CLAY_BRICK, "Copper Ingot"));
        Rail.itemRegistry().register("railtech:res-dustCopper", new ItemResource(Material.INK_SACK, "Copper Dust", (short)14));
        dustRecipe("railtech:res-ingotCopper", "railtech:res-dustCopper");
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.GOLD_ORE),
                s -> Rail.itemRegistry().create("railtech:res-dustGold", 2),
                s -> Rail.itemRegistry().create("railtech:res-dustCopper"), 0.5F));
        Rail.itemRegistry().register("railtech:res-ingotSteel", new ItemResource(Material.IRON_INGOT, "Steel Ingot"));
        Rail.itemRegistry().register("railtech:res-dustSteel", new ItemResource(Material.SULPHUR, "Steel Dust"));
        dustRecipe("railtech:res-ingotSteel", "railtech:res-dustSteel");
        Rail.itemRegistry().register("railtech:res-ingotTin", new ItemResource(Material.IRON_INGOT, "Tin Ingot"));
        Rail.itemRegistry().register("railtech:res-dustTin", new ItemResource(Material.SUGAR, "Tin Dust"));
        dustRecipe("railtech:res-ingotTin", "railtech:res-dustTin");
        Rail.itemRegistry().register("railtech:res-ingotTitanium", new ItemResource(Material.IRON_INGOT, "Titanium Ingot"));
        Rail.itemRegistry().register("railtech:res-dustTitanium", new ItemResource(Material.INK_SACK, "Titanium Dust", (short)8));
        dustRecipe("railtech:res-ingotTitanium", "railtech:res-dustTitanium");
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.IRON_ORE),
                s -> Rail.itemRegistry().create("railtech:res-dustIron", 2),
                s -> Rail.itemRegistry().create("railtech:res-dustTitanium"), 0.15F));
        Rail.itemRegistry().register("railtech:res-dustIron", new ItemResource(Material.INK_SACK, "Iron Dust", (short)7));
        dustRecipe(Material.IRON_INGOT, "railtech:res-dustIron");
        Rail.itemRegistry().register("railtech:res-dustGold", new ItemResource(Material.GLOWSTONE_DUST, "Gold Dust"));
        dustRecipe(Material.GOLD_INGOT, "railtech:res-dustGold");
    }

    private static void dustRecipe(Material ingot, String dust) {
        RTRecipes.register(new MaceratorRecipe(ItemUtils.matching(ingot), s -> Rail.itemRegistry().create(dust)));
        Rail.recipes().register(new RailSmeltRecipe().withInput(dust).withOutput(new ItemStack(ingot)));
    }

    private static void dustRecipe(String ingot, String dust) {
        RTRecipes.register(new MaceratorRecipe(ItemUtils.matching(ingot), s -> Rail.itemRegistry().create(dust)));
        Rail.recipes().register(new RailSmeltRecipe().withInput(dust).withOutput(ingot));
    }

}
