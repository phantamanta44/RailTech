package io.github.phantamanta44.mcrail.railtech.common.tile;

import com.google.gson.JsonObject;
import org.bukkit.block.Block;

public abstract class TileEnergizedRated extends TileEnergized {

    protected int energyRateIn, energyRateOut;

    public TileEnergizedRated(Block block, String id, int energyMax, int rateIn, int rateOut) {
        super(block, id, energyMax);
        this.energyRateIn = rateIn;
        this.energyRateOut = rateOut;
    }

    public TileEnergizedRated(Block block, String id, int energyMax, int rateInOut) {
        super(block, id, energyMax);
        this.energyRateIn = this.energyRateOut = rateInOut;
    }

    @Override
    public int offerEnergy(int amount) {
        return super.offerEnergy(Math.min(amount, energyRateIn));
    }

    @Override
    public boolean canAccept(int amount) {
        return energyRateIn >= amount && super.canAccept(amount);
    }

    @Override
    public int requestEnergy(int amount) {
        return super.requestEnergy(Math.min(amount, energyRateOut));
    }

    @Override
    public boolean canProvide(int amount) {
        return energyRateOut >= amount && super.canProvide(amount);
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
