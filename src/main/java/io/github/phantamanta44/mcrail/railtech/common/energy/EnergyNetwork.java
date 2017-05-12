package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.railflux.IEnergyContainer;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.sign.SignEntity;
import io.github.phantamanta44.mcrail.util.BlockPos;
import io.github.phantamanta44.mcrail.util.SignUtils;
import org.bukkit.block.BlockFace;

import java.util.*;
import java.util.stream.Stream;

public class EnergyNetwork {

    private final String type;
    private final Set<INetworkable> nodes;

    public EnergyNetwork(BlockPos root, String type) {
        this.type = type;
        this.nodes = new HashSet<>();
        scanFrom(root);
    }

    public void scanFrom(BlockPos root) {
        nodes.clear();
        Deque<BlockPos> toScan = new LinkedList<>();
        toScan.add(root);
        Map<BlockPos, EnergyNetwork> scanned = RTMain.INSTANCE.netMan().checked(type);
        while (!toScan.isEmpty())
            scan(toScan.pop(), scanned, toScan);
    }

    private void scan(BlockPos pos, Map<BlockPos, EnergyNetwork> scanned, Deque<BlockPos> toScan) {
        if (!scanned.containsKey(pos)) {
            scanned.put(pos, this);
            if (pos.exists()) {
                SignEntity tile = SignUtils.getAt(pos);
                if (tile != null && tile instanceof INetworkable) {
                    INetworkable node = (INetworkable)tile;
                    if (node.netCompatible(type))
                        nodes.add((INetworkable)tile);
                } else if (!RTMain.INSTANCE.netMan().isVector(type, pos.block().getType())) {
                    return;
                }
            }
            Stream.of(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN)
                    .map(f -> pos.add(f.getModX(), f.getModY(), f.getModZ()))
                    .forEach(toScan::add);
        }
    }

    public float averageEnergy() {
        return (float)energyStored() / (float)nodes.size();
    }

    public long energyStored() {
        return nodes.stream().mapToInt(IEnergyContainer::energyStored).sum();
    }

    public long energyCapacity() {
        return nodes.stream().mapToInt(IEnergyContainer::energyCapacity).sum();
    }

    public long offerEnergy(long amount) {
        // TODO Implement
    }

    public boolean canAccept(long amount) {
        // TODO Implement
    }

    public long requestEnergy(long amount) {
        // TODO Implement
    }

    public boolean canProvide(long amount) {
        // TODO Implement
    }

}
