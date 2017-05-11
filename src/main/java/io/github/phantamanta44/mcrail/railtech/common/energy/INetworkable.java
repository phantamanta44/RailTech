package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.util.BlockPos;

public interface INetworkable {

    BlockPos pos();

    EnergyNetwork network();

    void setNetwork(EnergyNetwork network);

    int capacity();

}
