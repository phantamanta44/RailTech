package io.github.phantamanta44.mcrail.railtech.common.energy;

import com.github.fge.lambdas.Throwing;
import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EnergyNetworkManager implements Listener {

    private final Collection<EnergyNetwork> networks;
    private final Map<String, INetworkType> types;

    public EnergyNetworkManager() {
        networks = new HashSet<>();
        types = new HashMap<>();
        Bukkit.getScheduler().scheduleSyncDelayedTask(RTMain.INSTANCE, this::loadNetworks, 1L);
        Rail.onSave(this::saveNetworks);
    }

    public void add(EnergyNetwork network) {
        networks.add(network);
    }

    public void remove(EnergyNetwork network) {
        networks.remove(network);
    }

    public void registerType(INetworkType type) {
        types.put(type.id(), type);
    }

    public INetworkType typeFor(String id) {
        return types.get(id);
    }

    public void loadNetworks() {
        try {
            Files.walk(RTMain.INSTANCE.getDataFolder().toPath())
                    .filter(p -> p.getFileName().startsWith("networks_") && p.getFileName().endsWith(".json"))
                    .map(Throwing.function(Files::newBufferedReader))
                    .peek(r -> {
                        // TODO Parse json
                    })
                    .forEach(Throwing.consumer(BufferedReader::close));
        } catch (IOException e) {
            // TODO Handle failure
        }
    }

    public void saveNetworks(World world) {
        File file = new File(RTMain.INSTANCE.getDataFolder(), "networks_" + world.getName() + ".json");
        file.getParentFile().mkdirs();
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            // TODO Write json
        } catch (IOException e) {
            // TODO Handle failure
        }
    }

    public void saveAll() {
        Bukkit.getServer().getWorlds().forEach(this::saveNetworks);
    }

    // TODO Implement mutation listeners

}
