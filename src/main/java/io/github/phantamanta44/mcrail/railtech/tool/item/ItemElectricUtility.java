package io.github.phantamanta44.mcrail.railtech.tool.item;

import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.railtech.common.item.ItemEnergizedRated;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ItemElectricUtility extends ItemEnergizedRated {

    protected final int energyCost;

    private final Material material;

    public ItemElectricUtility(Material tool, String name, int energyMax, int energyRate, int energyCost) {
        super(energyMax, energyRate);
        this.material = tool;
        this.energyCost = energyCost;
        characteristics.add(new CharName(name));
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public boolean onDamage(PlayerItemDamageEvent event, ItemStack stack) {
        if (requestEnergy(event.getDamage() * energyCost, stack) > 0) {
            event.getPlayer().updateInventory();
            return false;
        }
        return true;
    }

}
