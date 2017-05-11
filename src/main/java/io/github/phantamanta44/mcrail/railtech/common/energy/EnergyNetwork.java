package io.github.phantamanta44.mcrail.railtech.common.energy;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergized;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.util.BlockPos;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.block.BlockFace;

import java.util.*;
import java.util.stream.Stream;

public class EnergyNetwork implements IEnergized {

    private final INetworkType type;
    private final Collection<INetworkable> nodes;
    private final Deque<INetworkable> toScan;
    private final Set<BlockPos> scanned;
    private INetworkable rootNode;
    private long energy, energyMax;

    public EnergyNetwork(INetworkable rootNode, INetworkType type) {
        this(type);
        if (!type.compatible(rootNode))
            throw new IllegalArgumentException("Incompatible node and network!");
        this.rootNode = rootNode;
        RTMain.INSTANCE.netMan().add(this);
        rescan();
    }

    private EnergyNetwork(INetworkType type) {
        this.type = type;
        this.nodes = new HashSet<>();
        this.toScan = new LinkedList<>();
        this.scanned = new HashSet<>();
        RTMain.INSTANCE.netMan().add(this);
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
        recalculateEnergyMax();
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

    private void recalculateEnergyMax() {
        energyMax = nodes.stream().mapToInt(INetworkable::capacity).sum();
    }

    private void tryRegenerateNetwork(INetworkable rootNode) {
        if (rootNode.network() == null)
            new EnergyNetwork(rootNode, type);
    }

    @Override
    public int energyStored() {
        return Long.valueOf(energy).intValue();
    }

    public long eStoredActual() {
        return energy;
    }

    @Override
    public int energyCapacity() {
        return Long.valueOf(energyMax).intValue();
    }

    public long eMaxActual() {
        return energyMax;
    }

    @Override
    public int offerEnergy(int amount) {
        int toTransfer = Math.min(amount, (int)Math.min(energyMax - energy, Integer.MAX_VALUE));
        energy += toTransfer;
        return toTransfer;
    }

    public long eOfferActual(long amount) {
        long toTransfer = Math.min(amount, energyMax - energy);
        energy += toTransfer;
        return toTransfer;
    }

    @Override
    public boolean canAccept(int amount) {
        return eCanAcceptActual(amount);
    }

    public boolean eCanAcceptActual(long amount) {
        return energyMax - energy >= amount;
    }

    @Override
    public int requestEnergy(int amount) {
        int toTransfer = Math.min(amount, (int)Math.min(energy, Integer.MAX_VALUE));
        energy -= toTransfer;
        return toTransfer;
    }

    public long eRequestActual(long amount) {
        long toTransfer = Math.min(amount, energy);
        energy -= toTransfer;
        return toTransfer;
    }

    @Override
    public boolean canProvide(int amount) {
        return eCanProvideActual(amount);
    }

    public boolean eCanProvideActual(long amount) {
        return energy >= amount;
    }

    public static EnergyNetwork deserialize(JsonObject dto) {
        EnergyNetwork network = new EnergyNetwork(RTMain.INSTANCE.netMan().typeFor(dto.get("type").getAsString()));
        network.rootNode = network.type.tryGenerateNode(BlockPos.deserialize(dto.get("root").getAsJsonObject()));
        dto.get("nodes").getAsJsonArray().forEach(
                e -> network.nodes.add(network.type.tryGenerateNode(BlockPos.deserialize(e.getAsJsonObject()))));
        network.recalculateEnergyMax();
        network.energy = dto.get("energy").getAsLong();
        return network;
    }

    public JsonObject serialize() {
        JsonObject dto = new JsonObject();
        dto.addProperty("type", type.id());
        dto.add("root", rootNode.pos().serialize());
        dto.add("nodes", nodes.stream().map(n -> n.pos().serialize()).collect(JsonUtils.arrayCollector()));
        dto.addProperty("energy", energy);
        return dto;
    }

}
