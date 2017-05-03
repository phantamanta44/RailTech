package io.github.phantamanta44.mcrail.railtech.common.recipe.impl;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileInfuser;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.function.Predicate;

public class InfuserRecipe implements IMachineRecipe<InfuserRecipe.Input, ItemStack> {

    public static final RecipeType<Input, ItemStack> TYPE = new RecipeType<>(Input.class, ItemStack.class);

    private final Predicate<ItemStack> inputPred;
    private final Type resType;
    private final int resAmount;
    private final ItemStack output;

    public InfuserRecipe(Predicate<ItemStack> input, Type resType, int resAmount, ItemStack output) {
        this.inputPred = input;
        this.resType = resType;
        this.resAmount = resAmount;
        this.output = output;
    }

    @Override
    public RecipeType<Input, ItemStack> type() {
        return TYPE;
    }

    @Override
    public boolean matches(Input input) {
        return input.type == resType && input.amount >= resAmount && inputPred.test(input.input);
    }

    @Override
    public boolean canProcess(Input input, ItemStack output) {
        return this.output.isSimilar(output)
                && output.getAmount() + this.output.getAmount() <= output.getMaxStackSize();
    }

    @Override
    public ItemStack output(Input input) {
        return output.clone();
    }

    public static class Input {

        private final ItemStack input;
        private final Type type;
        private final int amount;

        public Input(ItemStack input, Type type, int amount) {
            this.input = input;
            this.type = type;
            this.amount = amount;
        }

        public Input(TileInfuser tile) {
            this(tile.getInv()[0], tile.getResource(), tile.getResourceAmount());
        }

    }

    public enum Type {
        BIOMASS, CARBON, REDSTONE, FUNGI, TIN, DIAMOND
    }

    public static void registerDefault() {
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.IRON_INGOT), Type.CARBON, 10, Rail.itemRegistry().create("railtech:res-enrichedIron")));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching("railtech:res-enrichedIron"), Type.CARBON, 10, Rail.itemRegistry().create("railtech:res-dustSteel")));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.IRON_INGOT), Type.REDSTONE, 10, Rail.itemRegistry().create("railtech:alloy0")));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching("railtech:res-ingotTitanium"), Type.REDSTONE, 10, Rail.itemRegistry().create("railtech:circuit0")));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.DIRT), Type.FUNGI, 10, new ItemStack(Material.MYCEL)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.COBBLESTONE), Type.BIOMASS, 10, new ItemStack(Material.MOSSY_COBBLESTONE)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(new MaterialData(Material.SMOOTH_BRICK, (byte)0)), Type.CARBON, 10, new ItemStack(Material.SMOOTH_BRICK, 1, (short)1)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.SAND), Type.BIOMASS, 10, new ItemStack(Material.DIRT)));
        RTRecipes.register(new InfuserRecipe(
                ItemUtils.matching(Material.DIRT), Type.BIOMASS, 10, new ItemStack(Material.DIRT, 1, (short)2)));
        // TODO Reinforced and atomic alloys
    }

}
