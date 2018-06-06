package io.github.phantamanta44.mcrail.railtech.machine.tile;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railtech.common.component.MachineCore;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentSecurity;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergizedRated;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class TileMachine extends TileEnergizedRated {

    public final MachineCore machineCore;

    public TileMachine(Block block, String id, int energyMax, int energyRate) {
        super(block, id, energyMax, energyRate, 0);
        this.machineCore = new MachineCore(this)
                .install(new MachineComponentRedstone(this))
                .install(new MachineComponentSecurity(this));
    }

    @Override
    public void init() {
        // NO-OP
    }

    @Override
    public int getEnergyRateIn() {
        return machineCore.modifyEnergyRateIn(super.getEnergyRateIn());
    }

    @Override
    public int getEnergyRateOut() {
        return machineCore.modifyEnergyRateOut(super.getEnergyRateOut());
    }

    @Override
    public int energyCapacity() {
        return machineCore.modifyEnergyCapacity(super.energyCapacity());
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        super.modifyDrops(drops);
        machineCore.modifyDrops(drops);
    }

    @Override
    public void tick() {
        machineCore.tick();
    }

    @Override
    public boolean onInteract(PlayerInteractEvent event) {
        if (!machineCore.checkInteract(event))
            return false;
        return doInteract(event);
    }

    protected boolean doInteract(PlayerInteractEvent event) {
        return true;
    }

    protected boolean canWork() {
        return machineCore.checkWork();
    }

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        machineCore.deserialize(dto.get("components").getAsJsonObject());
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.add("components", machineCore.serialize());
        return dto;
    }

}
