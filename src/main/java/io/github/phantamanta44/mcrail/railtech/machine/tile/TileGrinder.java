package io.github.phantamanta44.mcrail.railtech.machine.tile;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.machine.gui.GuiGrinder;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.ItemStackInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.MultiStackOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.GrindingRecipe;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TileGrinder extends TileRecipeMachine<ItemStack, ItemStackInput, MultiStackOutput, GrindingRecipe> {

    public TileGrinder(Block block) {
        super(block, "railtech:mac-grinder", 3, GrindingRecipe.class, 200, 2400, 80000, 360);
        on(BlockDispenseEvent.class, e -> e.setCancelled(true));
    }

    @Override
    protected ItemStack getInput() {
        return inv[2];
    }

    @Override
    protected boolean canAcceptOutput(MultiStackOutput output) {
        return output.isAcceptable(inv[3], inv[4]);
    }

    @Override
    protected void doWork() {
        if (Rail.currentTick() % 16 == 0)
            block().getWorld().playSound(location(), Sound.BAT_LOOP, 0.15F, 0.5F);
    }

    @Override
    protected void process(GrindingRecipe recipe, MultiStackOutput output) {
        inv[2] = recipe.input().consume(inv[2]);
        if (ItemUtils.isNully(inv[3]))
            inv[3] = output.getPrimaryOutput().clone();
        else
            inv[3].setAmount(inv[3].getAmount() + output.getPrimaryOutput().getAmount());
        if (output.getSecondaryOutput() != null && Math.random() <= output.getChance()) {
            if (ItemUtils.isNully(inv[4]))
                inv[4] = output.getSecondaryOutput().clone();
            else
                inv[4].setAmount(inv[4].getAmount() + output.getSecondaryOutput().getAmount());
        }
    }

    @Override
    public boolean doInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking()) {
            new GuiGrinder(event.getPlayer(), this);
            return false;
        }
        return true;
    }

}
