package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.util.BlockPos;
import org.bukkit.block.BlockFace;

import java.util.*;
import java.util.stream.Stream;

public class EnergyNetwork {

    private final INetworkType type;
    private final Collection<INetworkable> nodes;
    private final Deque<INetworkable> toScan;
    private final Set<BlockPos> scanned;
    private INetworkable rootNode;

    public EnergyNetwork(INetworkable rootNode, INetworkType type) {
        if (!type.compatible(rootNode))
            throw new IllegalArgumentException("Incompatible node and network!");
        this.type = type;
        this.rootNode = rootNode;
        this.nodes = new HashSet<>();
        this.toScan = new LinkedList<>();
        this.scanned = new HashSet<>();
        RTMain.INSTANCE.netMan().add(this);
        rescan();
    }

    public void resetRootNode() {
        nodes.remove(rootNode);
        Optional<INetworkable> rootOpt = nodes.stream().findAny();
        if (rootOpt.isPresent()) {
            rootNode = rootOpt.get();
            rescan();
        } else {
            RTMain.INSTANCE.netMan().remove(this);
            nodes.forEach(n -> n.setNetwork(null));
            nodes.forEach(this::tryRegenerateNetwork);
        }
    }

    public void rescan() {
        if (rootNode.network() != this)
            return;
        nodes.forEach(n -> n.setNetwork(null));
        INetworkable[] oldNodes = nodes.toArray(new INetworkable[nodes.size()]);
        nodes.clear();
        toScan.add(rootNode);
        while (!toScan.isEmpty())
            scan(toScan.pop());
        scanned.clear();
        Arrays.stream(oldNodes).filter(n -> !nodes.contains(n)).forEach(this::tryRegenerateNetwork);
    }

    private void scan(INetworkable tile) {
        if (!scanned.contains(tile.pos())) {
            scanned.add(tile.pos());
            nodes.add(tile);
            tile.setNetwork(this);
            Stream.of(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN)
                    .map(f -> type.tryGenerateNode(tile.pos().add(f.getModX(), f.getModY(), f.getModZ())))
                    .filter(Objects::nonNull)
                    .forEach(toScan::add);
        }
    }

    private void tryRegenerateNetwork(INetworkable rootNode) {
        if (rootNode.network() == null)
            new EnergyNetwork(rootNode, type);
    }

}
