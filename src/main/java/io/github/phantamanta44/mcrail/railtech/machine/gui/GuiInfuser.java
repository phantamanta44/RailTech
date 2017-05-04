package io.github.phantamanta44.mcrail.railtech.machine.gui;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.gui.GuiInventory;
import io.github.phantamanta44.mcrail.gui.slot.GuiSlot;
import io.github.phantamanta44.mcrail.railtech.common.UpgradeType;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotEnergyMeter;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotProgress;
import io.github.phantamanta44.mcrail.railtech.common.gui.slot.SlotUpgrade;
import io.github.phantamanta44.mcrail.railtech.machine.tile.TileInfuser;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class GuiInfuser extends GuiInventory {

    private final TileInfuser tile;

    public GuiInfuser(Player player, TileInfuser tile) {
        super(4, "Metallurgic Infuser", player, tile.inv());
        this.tile = tile;
    }

    @Override
    public void init() {
        slot(10, genSlot(0));
        slot(19, genSlot(1));
        slot(12, genSlot(2));
        slot(23, genSlot(3));
        slot(25, genSlot(4));
        slot(11, new SlotProgress(tile::completion));
        slot(14, new SlotEnergyMeter(tile));
        slot(16, new SlotUpgrade(Rail.itemRegistry().create("railtech:mac-upgradeSpeed"),
                () -> tile.upgrades(UpgradeType.SPEED), () -> tile.offsetUpgrades(UpgradeType.SPEED, -1)));
        slot(20, new SlotInfuse(tile));
    }

    private static class SlotInfuse extends GuiSlot {

        private static final ItemStack EMPTY_STACK;

        static {
            EMPTY_STACK = new ItemStack(Material.GLASS_BOTTLE);
            ItemMeta meta = EMPTY_STACK.getItemMeta();
            meta.setDisplayName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Infusion Agent");
            meta.setLore(Collections.singletonList(ChatColor.RESET + "No infusion agent!"));
            EMPTY_STACK.setItemMeta(meta);
        }

        private final TileInfuser tile;

        public SlotInfuse(TileInfuser tile) {
            this.tile = tile;
        }

        @Override
        public ItemStack stack() {
            if (tile.resource() == null)
                return EMPTY_STACK;
            ItemStack stack = new ItemStack(tile.resource().icon());
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Infusion Agent");
            meta.setLore(Arrays.asList(
                    ChatColor.RESET + String.format("%dmb %s", tile.resourceAmount(), tile.resource().displayName()),
                    ChatColor.GRAY + "Click to empty."));
            stack.setItemMeta(meta);
            return stack;
        }

        @Override
        public boolean onInteract(Player player, InventoryClickEvent event) {
            tile.clearResource();
            return false;
        }

    }

}
