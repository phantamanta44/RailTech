package io.github.phantamanta44.mcrail.railtech.tool.item;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.railflux.item.ItemLastLoreEnergy;
import io.github.phantamanta44.mcrail.railtech.util.Hardness;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ItemEnergizedTool extends ItemLastLoreEnergy {

    private final Material material;
    private final Map<UUID, Long> damageCancels;

    public ItemEnergizedTool(Material material, String name, int charge) {
        super(charge, charge);
        this.material = material;
        this.damageCancels = new ConcurrentHashMap<>();
        this.characteristics.add(new CharName(ChatColor.AQUA + name));
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public void onDamage(PlayerItemDamageEvent event, ItemStack stack) {
        Long cancelTime = damageCancels.remove(event.getPlayer().getUniqueId());
        if (cancelTime != null && Rail.currentTick() - cancelTime <= 1) {
            event.setCancelled(true);
            event.getPlayer().updateInventory();
        } else if (requestEnergy(event.getDamage() * 200, stack) > 0) {
            event.setCancelled(true);
            event.getPlayer().updateInventory();
        }
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, ItemStack stack) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE
                && requestEnergy((int)Math.ceil(Hardness.of(event.getBlock().getType()) * 150), stack) > 0) {
            damageCancels.put(event.getPlayer().getUniqueId(), Rail.currentTick());
        }
    }

}
