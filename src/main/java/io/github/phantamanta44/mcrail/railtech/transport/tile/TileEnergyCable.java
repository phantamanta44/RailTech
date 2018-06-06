package io.github.phantamanta44.mcrail.railtech.transport.tile;

import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergizedRated;
import io.github.phantamanta44.mcrail.railtech.util.EnergyUtils;
import org.bukkit.block.Block;

public class TileEnergyCable extends TileEnergizedRated {

    public TileEnergyCable(Block block, String id, int transferRate) {
        super(block, id, transferRate, transferRate, transferRate);
    }

    @Override
    public void init() {
        // NO-OP
    }

    @Override
    public void tick() {
        requestEnergyRaw(EnergyUtils.distributeAdj(pos(), energyStored()));
    }

}
