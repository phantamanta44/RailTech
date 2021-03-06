package io.github.phantamanta44.mcrail.railtech.resource;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import io.github.phantamanta44.mcrail.crafting.RailShapelessRecipe;
import io.github.phantamanta44.mcrail.crafting.RailSmeltRecipe;
import io.github.phantamanta44.mcrail.oredict.OreDictionary;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.MultiStackOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.GrindingRecipe;
import io.github.phantamanta44.mcrail.railtech.resource.item.ItemResource;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ResourceModule implements RTMain.Module {

    @Override
    public void registerItems() {
        // Copper stuff
        Rail.itemRegistry().register("railtech:res-ingotCopper", new ItemResource(Material.CLAY_BRICK, "Copper Ingot"));
        Rail.itemRegistry().register("railtech:res-dustCopper", new ItemResource(Material.INK_SACK, "Copper Dust", (short)14));
        Rail.itemRegistry().register("railtech:res-nuggetCopper", new ItemResource(Material.RABBIT_FOOT, "Copper Nugget"));
        registerOreDict("Copper");

        // Steel stuff
        Rail.itemRegistry().register("railtech:res-ingotSteel", new ItemResource(Material.IRON_INGOT, "Steel Ingot"));
        Rail.itemRegistry().register("railtech:res-dustSteel", new ItemResource(Material.SULPHUR, "Steel Dust"));
        Rail.itemRegistry().register("railtech:res-nuggetSteel", new ItemResource(Material.GHAST_TEAR, "Steel Nugget"));
        registerOreDict("Steel");

        // Tin stuff
        Rail.itemRegistry().register("railtech:res-ingotTin", new ItemResource(Material.IRON_INGOT, "Tin Ingot"));
        Rail.itemRegistry().register("railtech:res-dustTin", new ItemResource(Material.SUGAR, "Tin Dust"));
        Rail.itemRegistry().register("railtech:res-nuggetTin", new ItemResource(Material.GHAST_TEAR, "Tin Nugget"));
        registerOreDict("Tin");

        // Bronze stuff
        Rail.itemRegistry().register("railtech:res-ingotBronze", new ItemResource(Material.CLAY_BRICK, "Bronze Ingot"));
        Rail.itemRegistry().register("railtech:res-dustBronze", new ItemResource(Material.INK_SACK, "Bronze Dust", (short)3));
        Rail.itemRegistry().register("railtech:res-nuggetBronze", new ItemResource(Material.RABBIT_FOOT, "Bronze Nugget"));
        registerOreDict("Bronze");

        // Iron stuff
        Rail.itemRegistry().register("railtech:res-dustIron", new ItemResource(Material.INK_SACK, "Iron Dust", (short)7));
        OreDictionary.register("dustIron", "railtech:res-dustIron");
        Rail.itemRegistry().register("railtech:res-nuggetIron", new ItemResource(Material.RABBIT_FOOT, "Iron Nugget"));
        OreDictionary.register("dustIron", "railtech:res-dustIron");
        OreDictionary.register("nuggetIron", "railtech:res-nuggetIron");

        // Gold stuff
        Rail.itemRegistry().register("railtech:res-dustGold", new ItemResource(Material.GLOWSTONE_DUST, "Gold Dust"));
        OreDictionary.register("dustGold", "railtech:res-dustGold");

        // Coal dust
        Rail.itemRegistry().register("railtech:res-dustCoal", new ItemResource(Material.MELON_SEEDS, "Coal Dust"));
        OreDictionary.register("dustCoal", "railtech:res-dustCoal");
    }

    @Override
    public void registerRecipes() {
        // Non-vanilla
        registerOreRecipes("Copper");
        registerOreRecipes("Steel");
        registerOreRecipes("Tin");
        registerOreRecipes("Bronze");
        
        // Iron stuff
        Rail.recipes().register(new RailShapelessRecipe()
                .ingOreDict("ingotIron")
                .withOutput("railtech:res-nuggetIron", 9));
        Rail.recipes().register(new RailRecipe()
                .line("nnn").line("nnn").line("nnn")
                .ingOreDict('n', "nuggetIron")
                .withResult(new ItemStack(Material.IRON_INGOT, 1)));
        Rail.recipes().register(new RailSmeltRecipe()
                .withInputOreDict("dustIron")
                .withOutput(new ItemStack(Material.IRON_INGOT, 1)));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                OreDictionary.predicate("ingotIron"),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustIron"))));
        
        // Gold stuff
        Rail.recipes().register(new RailSmeltRecipe()
                .withInputOreDict("dustGold")
                .withOutput(new ItemStack(Material.GOLD_INGOT, 1)));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                OreDictionary.predicate("ingotGold"),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustGold"))));
        
        // Coal dust
        Rail.recipes().registerFurnaceFuelOreDict("dustCoal", 300);
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 1))));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 2))));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 3))));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 4))));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 5))));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 6))));
    }

    private void registerOreDict(String ore) {
        OreDictionary.register("ingot" + ore, "railtech:res-ingot" + ore);
        OreDictionary.register("dust" + ore, "railtech:res-dust" + ore);
        OreDictionary.register("nugget" + ore, "railtech:res-nugget" + ore);
    }

    private void registerOreRecipes(String ore) {
        Rail.recipes().register(new RailShapelessRecipe()
                .ingOreDict("ingot" + ore)
                .withOutput("railtech:res-nugget" + ore, 9));
        Rail.recipes().register(new RailRecipe()
                .line("nnn").line("nnn").line("nnn")
                .ingOreDict('n', "nugget" + ore)
                .withResult("railtech:res-ingot" + ore));
        Rail.recipes().register(new RailSmeltRecipe()
                .withInputOreDict("dust" + ore)
                .withOutput("railtech:res-ingot" + ore));
        RTRecipes.forType(GrindingRecipe.class).add(new GrindingRecipe(
                OreDictionary.predicate("ingot" + ore),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dust" + ore))));
    }

}
