package io.github.phantamanta44.mcrail.railtech.machine.tile;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.machine.gui.GuiSmelter;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.ItemStackInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.ItemStackOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.SmeltingRecipe;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TileSmelter extends TileRecipeMachine<ItemStack, ItemStackInput, ItemStackOutput, SmeltingRecipe> {

    public TileSmelter(Block block) {
        super(block, "railtech:mac-smelter", 2, SmeltingRecipe.class, 200, 1600, 80000, 360);
    }

    @Override
    protected ItemStack getInput() {
        return inv[2];
    }

    @Override
    protected boolean canAcceptOutput(ItemStackOutput output) {
        return output.isAcceptable(inv[1]);
    }

    @Override
    protected void doWork() {
        if (Rail.currentTick() % 5 == 0)
            block().getWorld().playSound(location(), Sound.HORSE_BREATHE, 0.2F, 0.5F);
    }

    @Override
    protected void process(SmeltingRecipe recipe, ItemStackOutput output) {
        inv[2] = recipe.input().consume(inv[2]);
        if (ItemUtils.isNully(inv[3]))
            inv[3] = output.getOutput().clone();
        else
            inv[3].setAmount(inv[3].getAmount() + output.getOutput().getAmount());
    }

    @Override
    protected boolean doInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking()) {
            new GuiSmelter(event.getPlayer(), this);
            return false;
        }
        return true;
    }

}
