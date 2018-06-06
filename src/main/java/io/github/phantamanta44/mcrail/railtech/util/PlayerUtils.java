package io.github.phantamanta44.mcrail.railtech.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class PlayerUtils {

    public static String nameFromId(UUID id) {
        OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(id);
        return player != null ? player.getName() : id.toString();
    }

}
