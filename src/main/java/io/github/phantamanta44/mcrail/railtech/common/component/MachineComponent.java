package io.github.phantamanta44.mcrail.railtech.common.component;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.tile.RailTile;

public abstract class MachineComponent implements IMachineModifier {

    protected final RailTile tile;

    private final String id;

    public MachineComponent(RailTile tile, String id) {
        this.tile = tile;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void deserialize(JsonObject dto);

    public abstract JsonObject serialize();

}
