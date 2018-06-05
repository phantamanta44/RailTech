package io.github.phantamanta44.mcrail.railtech.common.tile;

import com.google.gson.JsonObject;
import org.bukkit.block.Block;

public abstract class TileEnergizedRated extends TileEnergized {

    private int energyRateIn, energyRateOut;

    public TileEnergizedRated(Block block, String id, int energyMax, int rateIn, int rateOut) {
        super(block, id, energyMax);
        this.energyRateIn = rateIn;
        this.energyRateOut = rateOut;
    }

    public TileEnergizedRated(Block block, String id, int energyMax, int rateInOut) {
        this(block, id, energyMax, rateInOut, rateInOut);
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
        return energyRateIn;
    }

    public int getEnergyRateOut() {
        return energyRateOut;
    }

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        this.energyRateIn = dto.get("energyRateIn").getAsInt();
        this.energyRateOut = dto.get("energyRateOut").getAsInt();
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.addProperty("energyRateIn", energyRateIn);
        dto.addProperty("energyRateOut", energyRateOut);
        return dto;
    }

}
