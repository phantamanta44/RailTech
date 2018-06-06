package io.github.phantamanta44.mcrail.railtech.common.component;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.tile.RailTile;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MachineCore implements IMachineModifier {

    private final RailTile tile;
    private final Map<String, MachineComponent> components;

    public MachineCore(RailTile tile) {
        this.tile = tile;
        this.components = new HashMap<>();
    }

    public MachineCore install(MachineComponent component) {
        this.components.put(component.getId(), component);
        return this;
    }

    public MachineComponent get(String id) {
        return components.get(id);
    }

    public boolean offerUpgrade(ItemStack stack) {
        for (MachineComponent component : components.values()) {
            if (component.acceptUpgrade(stack))
                return true;
        }
        return false;
    }

    public void reset() {
        components.values().forEach(MachineComponent::reset);
    }

    @Override
    public int modifyEnergyRateIn(int rate) {
        for (MachineComponent component : components.values())
            rate = component.modifyEnergyRateIn(rate);
        return rate;
    }

    @Override
    public int modifyEnergyRateOut(int rate) {
        for (MachineComponent component : components.values())
            rate = component.modifyEnergyRateOut(rate);
        return rate;
    }

    @Override
    public int modifyEnergyCapacity(int capacity) {
        for (MachineComponent component : components.values())
            capacity = component.modifyEnergyCapacity(capacity);
        return capacity;
    }

    @Override
    public int modifyWorkRate(int work) {
        for (MachineComponent component : components.values())
            work = component.modifyWorkRate(work);
        return work;
    }

    @Override
    public int modifyEnergyConsumption(int consumption) {
        for (MachineComponent component : components.values())
            consumption = component.modifyEnergyConsumption(consumption);
        return consumption;
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        components.forEach((id, c) -> c.modifyDrops(drops));
    }

    @Override
    public void tick() {
        components.forEach((id, c) -> c.tick());
    }

    @Override
    public boolean checkInteract(PlayerInteractEvent event) {
        return components.values().stream().allMatch(c -> c.checkInteract(event));
    }

    @Override
    public boolean checkWork() {
        return components.values().stream().allMatch(IMachineModifier::checkWork);
    }

    public void deserialize(JsonObject dto) {
        dto.entrySet().forEach(e -> components.get(e.getKey()).deserialize(e.getValue().getAsJsonObject()));
    }

    public JsonObject serialize() {
        JsonObject dto = new JsonObject();
        components.forEach((id, c) -> dto.add(id, c.serialize()));
        return dto;
    }

}
