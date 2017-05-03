package io.github.phantamanta44.mcrail.railtech.common.recipe.impl;

import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MaceratorRecipe implements IMachineRecipe<ItemStack, Pair<ItemStack, ItemStack>> {

    public static final RecipeType<ItemStack, Pair<ItemStack, ItemStack>> TYPE = new RecipeType<>(ItemStack.class, Pair.class);
    
    private final Predicate<ItemStack> input;
    private final UnaryOperator<ItemStack> primOutput, secOutput;
    private final float chance;

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> primOutput, UnaryOperator<ItemStack> secOutput, float chance) {
        this.input = input;
        this.primOutput = primOutput;
        this.secOutput = secOutput;
        this.chance = chance;
    }

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> primOutput, UnaryOperator<ItemStack> secOutput) {
        this(input, primOutput, secOutput, 1F);
    }

    public MaceratorRecipe(Predicate<ItemStack> input, UnaryOperator<ItemStack> output) {
        this(input, output, null, 0F);
    }

    @Override
    public RecipeType<ItemStack, Pair<ItemStack, ItemStack>> type() {
        return TYPE;
    }

    @Override
    public boolean matches(ItemStack input) {
        return this.input.test(input);
    }

    @Override
    public boolean canProcess(ItemStack input, Pair<ItemStack, ItemStack> output) {
        ItemStack a = primOutput.apply(input);
        if (ItemUtils.isNotNully(output.getA())
                && (!output.getA().isSimilar(a) || output.getA().getAmount() + a.getAmount() > a.getMaxStackSize())) {
            return false;
        }
        if (secOutput == null || chance == 0)
            return true;
        ItemStack b = secOutput.apply(input);
       return ItemUtils.isNully(output.getB())
               || (output.getB().isSimilar(b) && output.getB().getAmount() + b.getAmount() <= b.getMaxStackSize());
    }

    @Override
    public Pair<ItemStack, ItemStack> output(ItemStack input) {
        return Pair.of(primOutput.apply(input), Math.random() < chance ? secOutput.apply(input) : null);
    }

    public static void registerDefault() {
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.CLAY), i -> new ItemStack(Material.CLAY_BALL, 4)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.LAPIS_ORE), i -> new ItemStack(Material.INK_SACK, 12, (short)4)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.REDSTONE_ORE), i -> new ItemStack(Material.REDSTONE, 12)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.DIAMOND_ORE), i -> new ItemStack(Material.DIAMOND, 2)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.COAL_ORE), i -> new ItemStack(Material.COAL, 2)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.GLOWSTONE), i -> new ItemStack(Material.GLOWSTONE_DUST, 4)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.QUARTZ_ORE), i -> new ItemStack(Material.QUARTZ, 6)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.EMERALD_ORE), i -> new ItemStack(Material.EMERALD, 2)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.COBBLESTONE), i -> new ItemStack(Material.SAND),
                i -> new ItemStack(Material.GRAVEL), 0.15F));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.STONE), i -> new ItemStack(Material.GRAVEL),
                i -> new ItemStack(Material.SAND), 0.15F));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.GRAVEL), i -> new ItemStack(Material.FLINT),
                i -> new ItemStack(Material.SAND), 0.15F));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.GLASS), i -> new ItemStack(Material.SAND)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.SMOOTH_BRICK, (byte)0)), i -> new ItemStack(Material.SMOOTH_BRICK, 1, (short)2)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.QUARTZ_BLOCK), i -> new ItemStack(Material.QUARTZ, 4)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.QUARTZ_STAIRS), i -> new ItemStack(Material.QUARTZ, 6)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.SANDSTONE), i -> new ItemStack(Material.SAND, 2)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.NETHERRACK), i -> new ItemStack(Material.GRAVEL)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.SUGAR_CANE), i -> new ItemStack(Material.SUGAR, 2)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.BONE), i -> new ItemStack(Material.INK_SACK, 6, (short)15)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.BLAZE_ROD), i -> new ItemStack(Material.BLAZE_POWDER, 4)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.WOOL), i -> new ItemStack(Material.STRING, 4),
                i -> {
                    switch (i.getData().getData()) {
                        case 0:
                        case 12:
                        case 13:
                        case 15:
                            return null;
                        default:
                            return new ItemStack(Material.INK_SACK, 1, (short)(15 - i.getData().getData()));
                    }
                }, 0.05F));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(Material.YELLOW_FLOWER), i -> new ItemStack(Material.INK_SACK, 4, (short)11)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)0)), i -> new ItemStack(Material.INK_SACK, 4, (short)1)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)1)), i -> new ItemStack(Material.INK_SACK, 4, (short)12)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)2)), i -> new ItemStack(Material.INK_SACK, 4, (short)13)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)3)), i -> new ItemStack(Material.INK_SACK, 4, (short)7)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)4)), i -> new ItemStack(Material.INK_SACK, 4, (short)1)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)5)), i -> new ItemStack(Material.INK_SACK, 4, (short)14)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)6)), i -> new ItemStack(Material.INK_SACK, 4, (short)7)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)7)), i -> new ItemStack(Material.INK_SACK, 4, (short)9)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.RED_ROSE, (byte)8)), i -> new ItemStack(Material.INK_SACK, 4, (short)7)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.DOUBLE_PLANT, (byte)0)), i -> new ItemStack(Material.INK_SACK, 8, (short)11)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.DOUBLE_PLANT, (byte)1)), i -> new ItemStack(Material.INK_SACK, 8, (short)13)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.DOUBLE_PLANT, (byte)2)), i -> new ItemStack(Material.INK_SACK, 8, (short)1)));
        RTRecipes.register(new MaceratorRecipe(
                ItemUtils.matching(new MaterialData(Material.DOUBLE_PLANT, (byte)3)), i -> new ItemStack(Material.INK_SACK, 8, (short)9)));
    }

}
