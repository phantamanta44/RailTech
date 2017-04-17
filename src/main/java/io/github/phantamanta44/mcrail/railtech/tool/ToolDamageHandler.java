package io.github.phantamanta44.mcrail.railtech.tool;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.IEnergyProvider;
import io.github.phantamanta44.mcrail.railtech.util.Hardness;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ToolDamageHandler implements Listener {

    private Map<UUID, Long> damageCancels;

    public ToolDamageHandler() {
        this.damageCancels = new HashMap<>();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        ItemStack stack = event.getPlayer().getItemInHand();
        if (stack != null) {
            IEnergyProvider eProv = AdapterUtils.adapt(IEnergyProvider.class, stack);
            if (eProv.requestEnergy((int)Math.ceil(Hardness.of(event.getBlock().getType()) * 150)) > 0)
                damageCancels.put(event.getPlayer().getUniqueId(), Rail.currentTick());
        }
    }

    @EventHandler
    public void onToolDamage(PlayerItemDamageEvent event) {
        Long cancelTime = damageCancels.remove(event.getPlayer().getUniqueId());
        if (cancelTime != null && Rail.currentTick() - cancelTime <= 1)
            event.setCancelled(true);
        IEnergyProvider eProv = AdapterUtils.adapt(IEnergyProvider.class, event.getItem());
        if (eProv.requestEnergy(event.getDamage() * 200) > 0)
            event.setCancelled(true);
    }

}
