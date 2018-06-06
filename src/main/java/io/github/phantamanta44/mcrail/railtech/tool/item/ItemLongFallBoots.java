package io.github.phantamanta44.mcrail.railtech.tool.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ItemLongFallBoots extends ItemElectricUtility {

    public ItemLongFallBoots() {
        super(Material.CHAINMAIL_BOOTS, ChatColor.RESET + "Free Runners", 720000, 8000, 250);
    }

    @Override
    public boolean onPlayerDamage(EntityDamageEvent event, ItemStack stack) {
        return event.getCause() != EntityDamageEvent.DamageCause.FALL
                || requestEnergy((int)Math.floor(event.getDamage() * energyCost), stack) == 0;
    }

}
