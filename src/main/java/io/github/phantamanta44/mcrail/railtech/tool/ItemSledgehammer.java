package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.item.IItemBehaviour;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.item.characteristic.IItemCharacteristic;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.impl.MaceratorRecipe;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Collections;

public class ItemSledgehammer implements IItemBehaviour {

    private static final Collection<IItemCharacteristic> CHARS =
            Collections.singleton(new CharName(ChatColor.BLACK + ChatColor.RESET.toString() + "Sledgehammer"));

    @Override
    public Material material() {
        return Material.IRON_PICKAXE;
    }

    @Override
    public Collection<IItemCharacteristic> characteristics() {
        return CHARS;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, ItemStack stack) {
        Block block = event.getBlock();
        Pair<ItemStack, ItemStack> result =
                RTRecipes.result(MaceratorRecipe.TYPE, new ItemStack(block.getType(), 1, event.getBlock().getData()));
        if (result != null) {
            event.setCancelled(true);
            block.setType(Material.AIR);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RTMain.INSTANCE, () -> {
                block.getWorld().dropItemNaturally(block.getLocation(), result.getA());
                if (ItemUtils.isNotNully(result.getB()))
                    block.getWorld().dropItemNaturally(block.getLocation(), result.getB());
            });
            stack.setDurability((short)(stack.getDurability() + 1));
            if (stack.getDurability() > 250)
                event.getPlayer().setItemInHand(null);
        }
    }

}
