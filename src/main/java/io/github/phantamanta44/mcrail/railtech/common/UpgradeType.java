package io.github.phantamanta44.mcrail.railtech.common;

import io.github.phantamanta44.mcrail.Rail;
import org.bukkit.inventory.ItemStack;

public enum UpgradeType {

    SPEED("railtech:mac-upgradeSpeed");

    private final String id;

    UpgradeType(String id) {
        this.id = id;
    }

    public ItemStack genStack() {
        return Rail.itemRegistry().create(id);
    }

}
