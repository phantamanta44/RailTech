package io.github.phantamanta44.mcrail.railtech.common.item;

import io.github.phantamanta44.mcrail.railflux.item.ItemLoreEnergy;
import io.github.phantamanta44.mcrail.railflux.item.LoreEnergyStackWrapper;
import org.bukkit.inventory.ItemStack;

public abstract class ItemEnergizedRated extends ItemLoreEnergy {

    private int energyRateIn, energyRateOut;

    public ItemEnergizedRated(int energyMax, int rateIn, int rateOut) {
        super(0, energyMax);
        this.energyRateIn = rateIn;
        this.energyRateOut = rateOut;
    }

    public ItemEnergizedRated(int energyMax, int rateInOut) {
        this(energyMax, rateInOut, rateInOut);
    }

    @Override
    public Wrapper wrap(ItemStack stack) {
        return Wrapper.wrap(stack, this);
    }

    public static class Wrapper extends LoreEnergyStackWrapper {

        public static Wrapper wrap(ItemStack stack, ItemEnergizedRated behaviour) {
            Wrapper item = new Wrapper(stack, behaviour);
            return item.matcher() == null ? null : item;
        }

        private final ItemEnergizedRated behaviour;

        private Wrapper(ItemStack stack, ItemEnergizedRated behaviour) {
            super(stack, 0);
            this.behaviour = behaviour;
        }

        @Override
        public int offerEnergy(int amount) {
            return super.offerEnergy(Math.min(amount, getEnergyRateIn()));
        }

        public int offerEnergyRaw(int amount) {
            return super.offerEnergy(amount);
        }

        @Override
        public boolean canAcceptEnergy(int amount) {
            return getEnergyRateIn() >= amount && super.canAcceptEnergy(amount);
        }

        @Override
        public int requestEnergy(int amount) {
            return super.requestEnergy(Math.min(amount, getEnergyRateOut()));
        }

        public int requestEnergyRaw(int amount) {
            return super.requestEnergy(amount);
        }

        @Override
        public boolean canProvideEnergy(int amount) {
            return getEnergyRateOut() >= amount && super.canProvideEnergy(amount);
        }

        public int getEnergyRateIn() {
            return behaviour.energyRateIn;
        }

        public int getEnergyRateOut() {
            return behaviour.energyRateOut;
        }

    }

}
