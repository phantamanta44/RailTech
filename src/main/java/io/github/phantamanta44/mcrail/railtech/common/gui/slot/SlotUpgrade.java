package io.github.phantamanta44.mcrail.railtech.common.gui.slot;

import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.IntSupplier;

public class SlotUpgrade extends GuiSlot {

    private final ItemStack upgradeType;
    private final Material material;
    private final IntSupplier source;
    private final Runnable downgradeCallback;

    public SlotUpgrade(ItemStack stack, IntSupplier source, Runnable downgradeCallback) {
        this.upgradeType = stack;
        this.material = stack.getType();
        this.source = source;
        this.downgradeCallback = downgradeCallback;
    }

    @Override
    public ItemStack stack() {
        ItemStack stack = new ItemStack(material);
        int count = source.getAsInt();
        if (count > 0)
            stack.setAmount(count);
        else
            stack.setType(Material.IRON_BARDING);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Upgrades");
        meta.setLore(Arrays.asList(
                ChatColor.GREEN + String.format("x%.2f Processing Speed", Math.pow(1.33, count)),
                ChatColor.GREEN + String.format("x%.2f Energy Consumption", Math.pow(1.33, count)),
                ChatColor.GRAY + "Click to remove."));
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public boolean onInteract(Player player, InventoryClickEvent event) {
        if (source.getAsInt() > 0) {
            if (ItemUtils.isNully(player.getItemOnCursor()))
                player.setItemOnCursor(upgradeType.clone());
            else if (ItemUtils.isMatch(player.getItemOnCursor(), upgradeType))
                player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() + 1);
            player.updateInventory();
            downgradeCallback.run();
        }
        return false;
    }

}
