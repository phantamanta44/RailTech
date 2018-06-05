package io.github.phantamanta44.mcrail.railtech.machine.tile;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.machine.gui.GuiGrinderLV;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.ItemStackInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.MultiStackOutput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.type.GrindingRecipe;
import io.github.phantamanta44.mcrail.railtech.util.Tier;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TileGrinderLV extends TileRecipeMachine<ItemStack, ItemStackInput, MultiStackOutput, GrindingRecipe> {

    public TileGrinderLV(Block block) {
        super(block, "railtech:mac-grinder-lv", Tier.LV, 3, GrindingRecipe.class,   200, 2400);
        on(BlockDispenseEvent.class, e -> e.setCancelled(true));
    }

    @Override
    protected ItemStack getInput() {
        return inv[2];
    }

    @Override
    protected boolean canAcceptOutput(MultiStackOutput output) {
        return (ItemUtils.isNully(inv[3]) || (ItemUtils.isMatch(inv[3], output.getPrimaryOutput())
                        && inv[3].getAmount() + output.getPrimaryOutput().getAmount() <= inv[3].getMaxStackSize()))
                && (ItemUtils.isNully(inv[4]) || ItemUtils.isNully(output.getSecondaryOutput())
                        || (ItemUtils.isMatch(inv[4], output.getSecondaryOutput())
                        && inv[4].getAmount() + output.getSecondaryOutput().getAmount() <= inv[4].getMaxStackSize()));

    }

    @Override
    protected void doWork() {
        if (Rail.currentTick() % 16 == 0)
            block().getWorld().playSound(location(), Sound.BAT_LOOP, 0.5F, 0.5F);
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
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking()) {
            event.setCancelled(true);
            new GuiGrinderLV(event.getPlayer(), this);
        }
    }

}
