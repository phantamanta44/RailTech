package io.github.phantamanta44.mcrail.railtech.resource;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import io.github.phantamanta44.mcrail.crafting.RailShapelessRecipe;
import io.github.phantamanta44.mcrail.crafting.RailSmeltRecipe;
import io.github.phantamanta44.mcrail.oredict.OreDictionary;
import io.github.phantamanta44.mcrail.railtech.resource.item.ItemResource;
import org.bukkit.Material;

public class ResourceModule {

    public static void init() {
        // Copper stuff
        Rail.itemRegistry().register("railtech:res-ingotCopper", new ItemResource(Material.CLAY_BRICK, "Copper Ingot"));
        Rail.itemRegistry().register("railtech:res-dustCopper", new ItemResource(Material.INK_SACK, "Copper Dust", (short)14));
        Rail.itemRegistry().register("railtech:res-nuggetCopper", new ItemResource(Material.RABBIT_FOOT, "Copper Nugget"));
        registerOre("Copper");

        // Steel stuff
        Rail.itemRegistry().register("railtech:res-ingotSteel", new ItemResource(Material.IRON_INGOT, "Steel Ingot"));
        Rail.itemRegistry().register("railtech:res-dustSteel", new ItemResource(Material.SULPHUR, "Steel Dust"));
        Rail.itemRegistry().register("railtech:res-nuggetSteel", new ItemResource(Material.GHAST_TEAR, "Steel Nugget"));
        registerOre("Steel");
        
        // Tin stuff
        Rail.itemRegistry().register("railtech:res-ingotTin", new ItemResource(Material.IRON_INGOT, "Tin Ingot"));
        Rail.itemRegistry().register("railtech:res-dustTin", new ItemResource(Material.SUGAR, "Tin Dust"));
        Rail.itemRegistry().register("railtech:res-nuggetTin", new ItemResource(Material.GHAST_TEAR, "Tin Nugget"));
        registerOre("Tin");

        // Bronze stuff
        Rail.itemRegistry().register("railtech:res-ingotBronze", new ItemResource(Material.CLAY_BRICK, "Bronze Ingot"));
        Rail.itemRegistry().register("railtech:res-dustBronze", new ItemResource(Material.INK_SACK, "Bronze Dust", (short)3));
        Rail.itemRegistry().register("railtech:res-nuggetBronze", new ItemResource(Material.RABBIT_FOOT, "Bronze Nugget"));
        registerOre("Bronze");

        // Iron stuff
        Rail.itemRegistry().register("railtech:res-dustIron", new ItemResource(Material.INK_SACK, "Iron Dust", (short)7));
        OreDictionary.register("dustIron", "railtech:res-dustIron");
        Rail.itemRegistry().register("railtech:res-nuggetIron", new ItemResource(Material.RABBIT_FOOT, "Iron Nugget"));
        OreDictionary.register("nuggetIron", "railtech:res-nuggetIron");
        registerOreRecipes("Iron");

        // Gold stuff
        Rail.itemRegistry().register("railtech:res-dustGold", new ItemResource(Material.GLOWSTONE_DUST, "Gold Dust"));
        OreDictionary.register("dustGold", "railtech:res-dustGold");
        registerOreRecipesWithoutNugget("Gold");

        // Coal dust
        Rail.itemRegistry().register("railtech:res-dustCoal", new ItemResource(Material.MELON_SEEDS, "Coal Dust"));
        OreDictionary.register("dustCoal", "railtech:res-dustCoal");
        Rail.recipes().registerFurnaceFuel("dustCoal", 300);
    }

    private static void registerOre(String ore) {
        OreDictionary.register("ingot" + ore, "railtech:res-ingot" + ore);
        OreDictionary.register("dust" + ore, "railtech:res-dust" + ore);
        OreDictionary.register("nugget" + ore, "railtech:res-nugget" + ore);
        registerOreRecipes(ore);
    }

    private static void registerOreRecipes(String ore) {
        Rail.recipes().register(new RailShapelessRecipe()
                .ingredient("ingot" + ore)
                .withOutput("railtech:res-nugget" + ore, 9));
        Rail.recipes().register(new RailRecipe()
                .line("nnn").line("nnn").line("nnn")
                .ingredient('n', "railtech:res-nugget" + ore)
                .withResult("railtech:res-ingot" + ore));
        registerOreRecipesWithoutNugget(ore);
    }

    private static void registerOreRecipesWithoutNugget(String ore) {
        Rail.recipes().register(new RailSmeltRecipe()
                .withInput("dust" + ore)
                .withOutput("railtech:res-ingot" + ore));
    }

}
