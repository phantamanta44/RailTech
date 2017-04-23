package io.github.phantamanta44.mcrail.railtech.util;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class BurnTime {

    private static final Map<Material, Integer> burnTimes;

    static {
        burnTimes = new HashMap<>();
        burnTimes.put(Material.LAVA_BUCKET, 20000);
        burnTimes.put(Material.COAL_BLOCK, 16000);
        burnTimes.put(Material.BLAZE_ROD, 2400);
        burnTimes.put(Material.COAL, 1600);
        burnTimes.put(Material.BOAT, 400);

        burnTimes.put(Material.LOG, 300);
        burnTimes.put(Material.LOG_2, 300);
        burnTimes.put(Material.WOOD, 300);
        burnTimes.put(Material.WOOD_PLATE, 300);
        burnTimes.put(Material.FENCE, 300);
        burnTimes.put(Material.FENCE_GATE, 300);
        burnTimes.put(Material.WOOD_STAIRS, 300);
        burnTimes.put(Material.BIRCH_WOOD_STAIRS, 300);
        burnTimes.put(Material.JUNGLE_WOOD_STAIRS, 300);
        burnTimes.put(Material.SPRUCE_WOOD_STAIRS, 300);
        burnTimes.put(Material.ACACIA_STAIRS, 300);
        burnTimes.put(Material.DARK_OAK_STAIRS, 300);
        burnTimes.put(Material.TRAP_DOOR, 300);
        burnTimes.put(Material.WORKBENCH, 300);
        burnTimes.put(Material.BOOKSHELF, 300);
        burnTimes.put(Material.CHEST, 300);
        burnTimes.put(Material.TRAPPED_CHEST, 300);
        burnTimes.put(Material.DAYLIGHT_DETECTOR, 300);
        burnTimes.put(Material.JUKEBOX, 300);
        burnTimes.put(Material.NOTE_BLOCK, 300);
        burnTimes.put(Material.HUGE_MUSHROOM_1, 300);
        burnTimes.put(Material.HUGE_MUSHROOM_2, 300);
        burnTimes.put(Material.BANNER, 300);
        burnTimes.put(Material.BOW, 300);
        burnTimes.put(Material.FISHING_ROD, 300);
        burnTimes.put(Material.LADDER, 300);

        burnTimes.put(Material.WOOD_PICKAXE, 200);
        burnTimes.put(Material.WOOD_AXE, 200);
        burnTimes.put(Material.WOOD_SWORD, 200);
        burnTimes.put(Material.WOOD_HOE, 200);
        burnTimes.put(Material.WOOD_SPADE, 200);
        burnTimes.put(Material.SIGN, 200);
        burnTimes.put(Material.WOOD_DOOR, 200);
        burnTimes.put(Material.DARK_OAK_DOOR_ITEM, 200);
        burnTimes.put(Material.ACACIA_DOOR_ITEM, 200);
        burnTimes.put(Material.BIRCH_DOOR_ITEM, 200);
        burnTimes.put(Material.SPRUCE_DOOR_ITEM, 200);
        burnTimes.put(Material.JUNGLE_DOOR_ITEM, 200);

        burnTimes.put(Material.WOOD_STEP, 150);

        burnTimes.put(Material.SAPLING, 100);
        burnTimes.put(Material.BOWL, 100);
        burnTimes.put(Material.STICK, 100);
        burnTimes.put(Material.WOOD_BUTTON, 100);
        burnTimes.put(Material.WOOL, 100);
        burnTimes.put(Material.CARPET, 67);
    }

    public static Integer of(Material material) {
        return burnTimes.getOrDefault(material, 0);
    }

}
