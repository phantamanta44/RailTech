package io.github.phantamanta44.mcrail.railtech.util;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Hardness {

    private static final Map<Material, Float> hardnesses;

    static {
        hardnesses = new HashMap<>();
        hardnesses.put(Material.CARPET, 0.1F);
        hardnesses.put(Material.SNOW, 0.1F);

        hardnesses.put(Material.BED, 0.2F);
        hardnesses.put(Material.COCOA, 0.2F);
        hardnesses.put(Material.DAYLIGHT_DETECTOR, 0.2F);
        hardnesses.put(Material.DAYLIGHT_DETECTOR_INVERTED, 0.2F);
        hardnesses.put(Material.HUGE_MUSHROOM_1, 0.2F);
        hardnesses.put(Material.HUGE_MUSHROOM_2, 0.2F);
        hardnesses.put(Material.LEAVES, 0.2F);
        hardnesses.put(Material.LEAVES_2, 0.2F);
        hardnesses.put(Material.SNOW_BLOCK, 0.2F);
        hardnesses.put(Material.VINE, 0.2F);

        hardnesses.put(Material.GLASS, 0.3F);
        hardnesses.put(Material.GLOWSTONE, 0.3F);
        hardnesses.put(Material.REDSTONE_LAMP_OFF, 0.3F);
        hardnesses.put(Material.REDSTONE_LAMP_ON, 0.3F);
        hardnesses.put(Material.SEA_LANTERN, 0.3F);
        hardnesses.put(Material.STAINED_GLASS, 0.3F);
        hardnesses.put(Material.STAINED_GLASS_PANE, 0.3F);
        hardnesses.put(Material.THIN_GLASS, 0.3F);

        hardnesses.put(Material.CACTUS, 0.4F);
        hardnesses.put(Material.LADDER, 0.4F);
        hardnesses.put(Material.NETHERRACK, 0.4F);

        hardnesses.put(Material.BREWING_STAND, 0.5F);
        hardnesses.put(Material.CAKE_BLOCK, 0.5F);
        hardnesses.put(Material.DIRT, 0.5F);
        hardnesses.put(Material.GOLD_PLATE, 0.5F);
        hardnesses.put(Material.HAY_BLOCK, 0.5F);
        hardnesses.put(Material.ICE, 0.5F);
        hardnesses.put(Material.IRON_PLATE, 0.5F);
        hardnesses.put(Material.LEVER, 0.5F);
        hardnesses.put(Material.PACKED_ICE, 0.5F);
        hardnesses.put(Material.PISTON_BASE, 0.5F);
        hardnesses.put(Material.PISTON_EXTENSION, 0.5F);
        hardnesses.put(Material.PISTON_STICKY_BASE, 0.5F);
        hardnesses.put(Material.SAND, 0.5F);
        hardnesses.put(Material.SOUL_SAND, 0.5F);
        hardnesses.put(Material.STONE_BUTTON, 0.5F);
        hardnesses.put(Material.STONE_PLATE, 0.5F);
        hardnesses.put(Material.WOOD_PLATE, 0.5F);

        hardnesses.put(Material.CLAY, 0.6F);
        hardnesses.put(Material.GRASS, 0.6F);
        hardnesses.put(Material.GRAVEL, 0.6F);
        hardnesses.put(Material.MYCEL, 0.6F);
        hardnesses.put(Material.SOIL, 0.6F);
        hardnesses.put(Material.SPONGE, 0.6F);

        hardnesses.put(Material.RAILS, 0.7F);

        hardnesses.put(Material.MONSTER_EGG, 0.75F);

        hardnesses.put(Material.NOTE_BLOCK, 0.8F);
        hardnesses.put(Material.QUARTZ_BLOCK, 0.8F);
        hardnesses.put(Material.QUARTZ_STAIRS, 0.8F);
        hardnesses.put(Material.RED_SANDSTONE, 0.8F);
        hardnesses.put(Material.RED_SANDSTONE_STAIRS, 0.8F);
        hardnesses.put(Material.SANDSTONE, 0.8F);
        hardnesses.put(Material.SANDSTONE_STAIRS, 0.8F);
        hardnesses.put(Material.WOOL, 0.8F);

        hardnesses.put(Material.MELON_BLOCK, 1F);
        hardnesses.put(Material.JACK_O_LANTERN, 1F);
        hardnesses.put(Material.PUMPKIN, 1F);
        hardnesses.put(Material.SIGN_POST, 1F);
        hardnesses.put(Material.SKULL, 1F);
        hardnesses.put(Material.STANDING_BANNER, 1F);
        hardnesses.put(Material.WALL_BANNER, 1F);
        hardnesses.put(Material.WALL_SIGN, 1F);

        hardnesses.put(Material.HARD_CLAY, 1.25F);

        hardnesses.put(Material.BOOKSHELF, 1.5F);
        hardnesses.put(Material.PRISMARINE, 1.5F);
        hardnesses.put(Material.SMOOTH_BRICK, 1.5F);
        hardnesses.put(Material.SMOOTH_STAIRS, 1.5F);
        hardnesses.put(Material.STONE, 1.5F);

        hardnesses.put(Material.BRICK, 2F);
        hardnesses.put(Material.BRICK_STAIRS, 2F);
        hardnesses.put(Material.CAULDRON, 2F);
        hardnesses.put(Material.COBBLE_WALL, 2F);
        hardnesses.put(Material.COBBLESTONE, 2F);
        hardnesses.put(Material.COBBLESTONE_STAIRS, 2F);
        hardnesses.put(Material.DOUBLE_STEP, 2F);
        hardnesses.put(Material.DOUBLE_STONE_SLAB2, 2F);
        hardnesses.put(Material.FENCE, 2F);
        hardnesses.put(Material.FENCE_GATE, 2F);
        hardnesses.put(Material.JUKEBOX, 2F);
        hardnesses.put(Material.LOG, 2F);
        hardnesses.put(Material.LOG_2, 2F);
        hardnesses.put(Material.MOSSY_COBBLESTONE, 2F);
        hardnesses.put(Material.NETHER_BRICK, 2F);
        hardnesses.put(Material.NETHER_BRICK_STAIRS, 2F);
        hardnesses.put(Material.NETHER_FENCE, 2F);
        hardnesses.put(Material.STEP, 2F);
        hardnesses.put(Material.STONE_SLAB2, 2F);
        hardnesses.put(Material.WOOD, 2F);
        hardnesses.put(Material.WOOD_DOUBLE_STEP, 2F);
        hardnesses.put(Material.WOOD_STAIRS, 2F);
        hardnesses.put(Material.WOOD_STEP, 2F);

        hardnesses.put(Material.CHEST, 2.5F);
        hardnesses.put(Material.TRAPPED_CHEST, 2.5F);
        hardnesses.put(Material.WORKBENCH, 2.5F);

        hardnesses.put(Material.BEACON, 3F);
        hardnesses.put(Material.COAL_ORE, 3F);
        hardnesses.put(Material.DIAMOND_ORE, 3F);
        hardnesses.put(Material.DRAGON_EGG, 3F);
        hardnesses.put(Material.EMERALD_ORE, 3F);
        hardnesses.put(Material.ENDER_STONE, 3F);
        hardnesses.put(Material.GLOWING_REDSTONE_ORE, 3F);
        hardnesses.put(Material.GOLD_BLOCK, 3F);
        hardnesses.put(Material.GOLD_ORE, 3F);
        hardnesses.put(Material.HOPPER, 3F);
        hardnesses.put(Material.IRON_ORE, 3F);
        hardnesses.put(Material.LAPIS_BLOCK, 3F);
        hardnesses.put(Material.LAPIS_ORE, 3F);
        hardnesses.put(Material.QUARTZ_ORE, 3F);
        hardnesses.put(Material.REDSTONE_ORE, 3F);
        hardnesses.put(Material.TRAP_DOOR, 3F);
        hardnesses.put(Material.WOODEN_DOOR, 3F);

        hardnesses.put(Material.BURNING_FURNACE, 3.5F);
        hardnesses.put(Material.DISPENSER, 3.5F);
        hardnesses.put(Material.DROPPER, 3.5F);
        hardnesses.put(Material.FURNACE, 3.5F);

        hardnesses.put(Material.WEB, 4F);

        hardnesses.put(Material.ANVIL, 5F);
        hardnesses.put(Material.COAL_BLOCK, 5F);
        hardnesses.put(Material.DIAMOND_BLOCK, 5F);
        hardnesses.put(Material.EMERALD_BLOCK, 5F);
        hardnesses.put(Material.ENCHANTMENT_TABLE, 5F);
        hardnesses.put(Material.IRON_BLOCK, 5F);
        hardnesses.put(Material.IRON_DOOR_BLOCK, 5F);
        hardnesses.put(Material.IRON_FENCE, 5F);
        hardnesses.put(Material.IRON_TRAPDOOR, 5F);
        hardnesses.put(Material.MOB_SPAWNER, 5F);
        hardnesses.put(Material.REDSTONE_BLOCK, 5F);

        hardnesses.put(Material.ENDER_CHEST, 22.5F);

        hardnesses.put(Material.OBSIDIAN, 50F);
    }

    public static float of(Material material) {
        return hardnesses.getOrDefault(material, 0F);
    }

}
