package io.github.phantamanta44.mcrail.railtech.energetics.tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railtech.common.component.MachineCore;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergizedRated;
import io.github.phantamanta44.mcrail.railtech.util.EnergyUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class TileGenerator extends TileEnergizedRated {

    public final MachineCore machineCore;

    protected final ItemStack[] inv;

    public TileGenerator(Block block, String id, int invSize, int energyMax, int energyRate) {
        super(block, id, energyMax, 0, energyRate);
        this.inv = new ItemStack[invSize + 1];
        this.machineCore = new MachineCore(this)
                .install(new MachineComponentRedstone(this));
    }

    @Override
    public void init() {
        // NO-OP
    }

    public ItemStack[] getInventory() {
        return inv;
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
        Arrays.stream(inv)
                .filter(Objects::nonNull)
                .forEach(drops::add);
        machineCore.modifyDrops(drops);
    }

    public void reset() {
        // TODO reset generator properties
    }

    @Override
    public void tick() {
        machineCore.tick();
        requestEnergyRaw(EnergyUtils.distributeAdj(pos(), Math.min(energyStored(), getEnergyRateOut())));
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
        JsonArray invDto = dto.get("inv").getAsJsonArray();
        for (int i = 0; i < invDto.size(); i++)
            inv[i] = JsonUtils.deserItemStack(invDto.get(i));
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.add("components", machineCore.serialize());
        dto.add("inv", Arrays.stream(inv)
                .map(JsonUtils::serItemStack)
                .collect(JsonUtils.arrayCollector()));
        return dto;
    }

}
