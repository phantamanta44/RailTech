package io.github.phantamanta44.mcrail.railtech.machine;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class TileBasicProcessing<T> extends TileEnergized {

    protected final RecipeType<ItemStack, T> recipeType;
    protected final int basePower;
    protected final int baseWorkNeeded;
    protected int work;
    protected int speedUpgrades;
    protected ItemStack[] inv;

    public TileBasicProcessing(Block block, String id, RecipeType<ItemStack, T> recipeType, int power, int workTicks, int bufferSize) {
        super(block, id, bufferSize);
        this.recipeType = recipeType;
        this.basePower = power;
        this.baseWorkNeeded = workTicks;
        this.inv = new ItemStack[5];
    }

    @Override
    public void tick() {
        IMachineRecipe<ItemStack, T> recipe = RTRecipes.recipeFor(recipeType, inv[0]);
        if (recipe != null && recipe.canProcess(inv[0], outputFor(recipe, inv))) {
            int power = power();
            if (canProvide(power)) {
                requestEnergy(power);
                if (++work >= workNeeded()) {
                    work = 0;
                    // TODO Set output
                }
            }
        }
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        // TODO Open GUI
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        // TODO Add drops
    }

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        work = dto.get("work").getAsInt();
        speedUpgrades = dto.get("speedUpgrades").getAsInt();
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.addProperty("work", work);
        dto.addProperty("speedUpgrades", speedUpgrades);
        return dto;
    }

    protected int power() {
        return (int)Math.floor(basePower * Math.pow(1.33, 2 * speedUpgrades));
    }

    protected int workNeeded() {
        return (int)Math.floor(baseWorkNeeded * Math.pow(0.75, speedUpgrades));
    }

    protected float completion() {
        return (float)work / (float)workNeeded();
    }

    private static <I, O> O outputFor(IMachineRecipe<ItemStack, I> recipe, ItemStack[] inv) {
        return null; // TODO Implement
    }

}
