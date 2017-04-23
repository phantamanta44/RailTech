package io.github.phantamanta44.mcrail.railtech.common.tile;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergized;
import io.github.phantamanta44.mcrail.sign.SignEntity;
import org.bukkit.block.Block;

public abstract class TileEnergized extends SignEntity implements IEnergized { // TODO Rate limited variant

    protected int energy, energyMax;

    public TileEnergized(Block block, String id, int energyMax) {
        super(block, id);
        this.energy = 0;
        this.energyMax = energyMax;
    }

    @Override
    public int energyStored() {
        return energy;
    }

    @Override
    public int energyCapacity() {
        return energyMax;
    }

    @Override
    public int offerEnergy(int amount) {
        int toTransfer = Math.min(amount, energyMax - energy);
        energy += toTransfer;
        return toTransfer;
    }

    @Override
    public boolean canAccept(int amount) {
        return energyMax - energy >= amount;
    }

    @Override
    public int requestEnergy(int amount) {
        int toTransfer = Math.min(amount, energy);
        energy -= toTransfer;
        return toTransfer;
    }

    @Override
    public boolean canProvide(int amount) {
        return energy >= amount;
    }

    @Override
    public void deserialize(JsonObject dto) {
        this.energy = dto.get("energy").getAsInt();
        this.energyMax = dto.get("energyMax").getAsInt();
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = new JsonObject();
        dto.addProperty("energy", energy);
        dto.addProperty("energyMax", energyMax);
        return dto;
    }

}
