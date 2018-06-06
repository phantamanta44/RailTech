package io.github.phantamanta44.mcrail.railtech.transport;

import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.transport.item.ItemTransporter;
import io.github.phantamanta44.mcrail.railtech.transport.tile.TileEnergyCable;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class TransportModule implements RTMain.Module {

    @Override
    public void registerItems() {
        // Cables
        Rail.tileRegistry().register(
                new ItemTransporter("cable-l", ChatColor.RESET + "Light Cable", Material.THIN_GLASS),
                b -> new TileEnergyCable(b, "railtech:trn-cable-l", 1280));
        Rail.tileRegistry().register(
                new ItemTransporter("cable-m", ChatColor.RESET + "Medium Cable",
                        Material.STAINED_GLASS_PANE, (byte)3),
                b -> new TileEnergyCable(b, "railtech:trn-cable-m", 5120));
        Rail.tileRegistry().register(
                new ItemTransporter("cable-h", ChatColor.AQUA + "Heavy Cable", Material.IRON_FENCE),
                b -> new TileEnergyCable(b, "railtech:trn-cable-h", 25600));
        Rail.tileRegistry().register(
                new ItemTransporter("cable-e", ChatColor.LIGHT_PURPLE + "Superconducting Cable",
                        Material.IRON_BLOCK),
                b -> new TileEnergyCable(b, "railtech:trn-cable-e", 128000));
    }

    @Override
    public void registerRecipes() {
        // TODO recipes
    }

}
