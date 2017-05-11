package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.util.BlockPos;

public interface INetworkType {

    String id();

    boolean compatible(INetworkable node);

    INetworkable tryGenerateNode(BlockPos pos);

}
