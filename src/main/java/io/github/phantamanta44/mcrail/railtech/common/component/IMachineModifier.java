package io.github.phantamanta44.mcrail.railtech.common.component;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public interface IMachineModifier {

    default int modifyEnergyRateIn(int rate) {
        return rate;
    }

    default int modifyEnergyRateOut(int rate) {
        return rate;
    }

    default int modifyEnergyCapacity(int capacity) {
        return capacity;
    }

    default int modifyWorkRate(int work) {
        return work;
    }

    default int modifyEnergyConsumption(int consumption) {
        return consumption;
    }

    default void modifyDrops(Collection<ItemStack> drops) {
        // NO-OP
    }

    default void tick() {
        // NO-OP
    }

    default boolean checkInteract(PlayerInteractEvent event) {
        return true;
    }

    default boolean checkWork() {
        return true;
    }

}
