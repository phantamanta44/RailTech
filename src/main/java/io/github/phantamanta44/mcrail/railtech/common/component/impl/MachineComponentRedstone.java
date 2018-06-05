package io.github.phantamanta44.mcrail.railtech.common.component.impl;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railtech.common.component.MachineComponent;
import io.github.phantamanta44.mcrail.tile.RailTile;

public class MachineComponentRedstone extends MachineComponent {

    private State state;

    public MachineComponentRedstone(RailTile tile) {
        super(tile, "redstone");
        this.state = State.IGNORE;
    }

    @Override
    public boolean checkWork() {
        if (state == State.IGNORE)
            return true;
        return tile.block().isBlockIndirectlyPowered() ? state == State.NORMAL : state == State.INVERT;
    }

    @Override
    public void deserialize(JsonObject dto) {
        state = State.valueOf(dto.get("state").getAsString());
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = new JsonObject();
        dto.addProperty("state", state.name());
        return dto;
    }

    public void cycleState() {
        state = State.values()[(state.ordinal() + 1) % 3];
    }

    public State getState() {
        return state;
    }

    public enum State {
        IGNORE, NORMAL, INVERT
    }

}
