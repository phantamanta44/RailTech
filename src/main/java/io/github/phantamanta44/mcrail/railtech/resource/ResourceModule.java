package io.github.phantamanta44.mcrail.railtech.resource;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.crafting.RailRecipe;
import io.github.phantamanta44.mcrail.crafting.RailShapelessRecipe;
import io.github.phantamanta44.mcrail.crafting.RailSmeltRecipe;
import io.github.phantamanta44.mcrail.oredict.OreDictionary;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.output.MultiStackOutput;
import io.github.phantamanta44.mcrail.railtech.common.recipe.type.GrindingRecipe;
import io.github.phantamanta44.mcrail.railtech.resource.item.ItemResource;
import io.github.phantamanta44.mcrail.railtech.util.Tier;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Material;

public class ResourceModule {

    public static void init() {
        initOres();
        initTier1Resources();
    }

    private static void initOres() {
        // Copper stuff
        Rail.itemRegistry().register("railtech:res-ingotCopper", new ItemResource(Material.CLAY_BRICK, "Copper Ingot"));
        Rail.itemRegistry().register("railtech:res-dustCopper", new ItemResource(Material.INK_SACK, "Copper Dust", (short)14));
        Rail.itemRegistry().register("railtech:res-nuggetCopper", new ItemResource(Material.RABBIT_FOOT, "Copper Nugget"));
        registerOre("Copper", Tier.PNEU);

        // Steel stuff
        Rail.itemRegistry().register("railtech:res-ingotSteel", new ItemResource(Material.IRON_INGOT, "Steel Ingot"));
        Rail.itemRegistry().register("railtech:res-dustSteel", new ItemResource(Material.SULPHUR, "Steel Dust"));
        Rail.itemRegistry().register("railtech:res-nuggetSteel", new ItemResource(Material.GHAST_TEAR, "Steel Nugget"));
        registerOre("Steel", Tier.LV);
        
        // Tin stuff
        Rail.itemRegistry().register("railtech:res-ingotTin", new ItemResource(Material.IRON_INGOT, "Tin Ingot"));
        Rail.itemRegistry().register("railtech:res-dustTin", new ItemResource(Material.SUGAR, "Tin Dust"));
        Rail.itemRegistry().register("railtech:res-nuggetTin", new ItemResource(Material.GHAST_TEAR, "Tin Nugget"));
        registerOre("Tin", Tier.PNEU);

        // Bronze stuff
        Rail.itemRegistry().register("railtech:res-ingotBronze", new ItemResource(Material.CLAY_BRICK, "Bronze Ingot"));
        Rail.itemRegistry().register("railtech:res-dustBronze", new ItemResource(Material.INK_SACK, "Bronze Dust", (short)3));
        Rail.itemRegistry().register("railtech:res-nuggetBronze", new ItemResource(Material.RABBIT_FOOT, "Bronze Nugget"));
        registerOre("Bronze", Tier.LV);

        // Iron stuff
        Rail.itemRegistry().register("railtech:res-dustIron", new ItemResource(Material.INK_SACK, "Iron Dust", (short)7));
        OreDictionary.register("dustIron", "railtech:res-dustIron");
        Rail.itemRegistry().register("railtech:res-nuggetIron", new ItemResource(Material.RABBIT_FOOT, "Iron Nugget"));
        OreDictionary.register("dustIron", "railtech:res-dustIron");
        OreDictionary.register("nuggetIron", "railtech:res-nuggetIron");
        Rail.recipes().register(new RailShapelessRecipe()
                .ingredient("ingotIron")
                .withOutput("railtech:res-nuggetIron", 9));
        Rail.recipes().register(new RailRecipe()
                .line("nnn").line("nnn").line("nnn")
                .ingredient('n', "nuggetIron")
                .withResult("railtech:res-ingotIron"));
        Rail.recipes().register(new RailSmeltRecipe()
                .withInput("dustIron")
                .withOutput("railtech:res-ingotIron"));
        RTRecipes.forType(GrindingRecipe.class).add(Tier.LV, new GrindingRecipe(
                ItemUtils.matching("ingotIron"),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustIron"))));

        // Gold stuff
        Rail.itemRegistry().register("railtech:res-dustGold", new ItemResource(Material.GLOWSTONE_DUST, "Gold Dust"));
        OreDictionary.register("dustGold", "railtech:res-dustGold");
        Rail.recipes().register(new RailSmeltRecipe()
                .withInput("dustGold")
                .withOutput("railtech:res-ingotGold"));
        RTRecipes.forType(GrindingRecipe.class).add(Tier.PNEU, new GrindingRecipe(
                ItemUtils.matching("ingotGold"),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustGold"))));

        // Coal dust
        Rail.itemRegistry().register("railtech:res-dustCoal", new ItemResource(Material.MELON_SEEDS, "Coal Dust"));
        OreDictionary.register("dustCoal", "railtech:res-dustCoal");
        Rail.recipes().registerFurnaceFuel("dustCoal", 300);
        RTRecipes.forType(GrindingRecipe.class).add(Tier.MECH, new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 1))));
        RTRecipes.forType(GrindingRecipe.class).add(Tier.PNEU, new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 2))));
        RTRecipes.forType(GrindingRecipe.class).add(Tier.LV, new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 3))));
        RTRecipes.forType(GrindingRecipe.class).add(Tier.MV, new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 4))));
        RTRecipes.forType(GrindingRecipe.class).add(Tier.HV, new GrindingRecipe(
                ItemUtils.matching(Material.COAL),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dustCoal", 6))));
    }

    private static void registerOre(String ore, int tier) {
        OreDictionary.register("ingot" + ore, "railtech:res-ingot" + ore);
        OreDictionary.register("dust" + ore, "railtech:res-dust" + ore);
        OreDictionary.register("nugget" + ore, "railtech:res-nugget" + ore);
        Rail.recipes().register(new RailShapelessRecipe()
                .ingredient("ingot" + ore)
                .withOutput("railtech:res-nugget" + ore, 9));
        Rail.recipes().register(new RailRecipe()
                .line("nnn").line("nnn").line("nnn")
                .ingredient('n', "nugget" + ore)
                .withResult("railtech:res-ingot" + ore));
        Rail.recipes().register(new RailSmeltRecipe()
                .withInput("dust" + ore)
                .withOutput("railtech:res-ingot" + ore));
        RTRecipes.forType(GrindingRecipe.class).add(tier, new GrindingRecipe(
                ItemUtils.matching("ingot" + ore),
                new MultiStackOutput(Rail.itemRegistry().create("railtech:res-dust" + ore))));
    }

    private static void initTier1Resources() {
        // TODO Tier 1 resources
    }

}
