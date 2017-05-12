package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.railflux.IEnergized;

public interface INetworkable extends IEnergized {

    boolean netCompatible(String type);

}
