package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.util.BlockPos;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.LongConsumer;

public class EnergyNetworkManager implements Listener, LongConsumer {

    private final Map<String, Map<BlockPos, EnergyNetwork>> checked;
    private final Map<String, Set<Material>> vectorBlocks;

    public EnergyNetworkManager() {
        checked = new HashMap<>();
        vectorBlocks = new HashMap<>();
        registerDefaultTypes();
        Rail.INSTANCE.onTick(this);
    }

    private void registerDefaultTypes() {
        registerType("steam");
        addVector("steam", Material.GLASS);
        registerType("pneu");
        addVector("pneu", Material.BRICK);
        registerType("elec");
        addVector("elec", Material.IRON_BLOCK);
        addVector("elec", Material.GOLD_BLOCK);
    }

    public void registerType(String type) {
        checked.put(type, new HashMap<>());
        vectorBlocks.put(type, EnumSet.noneOf(Material.class));
    }

    public void addVector(String type, Material vector) {
        vectorBlocks.get(type).add(vector);
    }

    public boolean isVector(String type, Material material) {
        return vectorBlocks.get(type).contains(material);
    }

    public Map<BlockPos, EnergyNetwork> checked(String type) {
        return checked.get(type);
    }

    public EnergyNetwork get(String type, BlockPos pos) {
        return checked.get(type).computeIfAbsent(pos, k -> new EnergyNetwork(k, type));
    }

    @Override
    public void accept(long tick) {
        checked.forEach((k, v) -> v.clear());
    }

}
