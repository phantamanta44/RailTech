package io.github.phantamanta44.mcrail.railtech.machine.tile;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railtech.common.component.MachineCore;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergizedRated;
import io.github.phantamanta44.mcrail.railtech.util.Tier;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class TileMachine extends TileEnergizedRated {

    public final MachineCore machineCore;

    protected final int tier;

    public TileMachine(Block block, String id, int tier) {
        super(block, id, Tier.mEnergyMax[tier], Tier.mRateIn[tier], 0);
        this.tier = tier;
        this.machineCore = new MachineCore(this);
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
        super.tick();
        machineCore.tick();
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (!machineCore.checkInteract(event))
            event.setCancelled(true);
        else
            doInteract(event);
    }

    protected void doInteract(PlayerInteractEvent event) {
        // NO-OP
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
