package io.github.phantamanta44.mcrail.railtech.tool.item;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.railtech.common.item.ItemEnergizedRated;
import io.github.phantamanta44.mcrail.railtech.util.Hardness;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ItemElectricTool extends ItemEnergizedRated {

    private final Material material;
    private final int energyCost;
    private final Map<UUID, Long> damageCancels;

    public ItemElectricTool(Material tool, String name, int energyMax, int energyRate, int energyCost) {
        super(energyMax, energyRate);
        this.material = tool;
        this.energyCost = energyCost;
        this.damageCancels = new ConcurrentHashMap<>();
        characteristics.add(new CharName(name));
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public boolean onDamage(PlayerItemDamageEvent event, ItemStack stack) {
        Long cancelTime = damageCancels.remove(event.getPlayer().getUniqueId());
        if (cancelTime != null && Rail.currentTick() - cancelTime <= 1) {
            event.getPlayer().updateInventory();
            return false;
        }
        if (requestEnergy(event.getDamage() * energyCost, stack) > 0) {
            event.getPlayer().updateInventory();
            return false;
        }
        return true;
    }

    @Override
    public boolean onBlockBreak(BlockBreakEvent event, ItemStack stack) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE
                && requestEnergy((int)Math.ceil(Hardness.of(event.getBlock().getType()) * energyCost), stack) > 0) {
            damageCancels.put(event.getPlayer().getUniqueId(), Rail.currentTick());
        }
        return true;
    }

    @Override
    public boolean onUse(PlayerInteractEvent event, ItemStack stack) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) { // TODO remove test code
            Wrapper battery = wrap(stack);
            battery.offerEnergyRaw(battery.energyCapacity() - battery.energyStored());
        }
        return true;
    }

}
