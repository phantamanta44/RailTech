package io.github.phantamanta44.mcrail.railtech.resource;

import io.github.phantamanta44.mcrail.Rail;
import org.bukkit.Material;

public class ResourceModule {

    public static void init() {
        Rail.itemRegistry().register("railtech:res-ingotCopper", new ItemResource(Material.CLAY_BRICK, "Copper Ingot"));
        Rail.itemRegistry().register("railtech:res-ingotSteel", new ItemResource(Material.IRON_INGOT, "Steel Ingot"));
        Rail.itemRegistry().register("railtech:res-ingotTin", new ItemResource(Material.IRON_INGOT, "Tin Ingot"));
        Rail.itemRegistry().register("railtech:res-ingotTitanium", new ItemResource(Material.IRON_INGOT, "Titanium Ingot"));
    }

}
