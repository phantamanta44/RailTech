package io.github.phantamanta44.mcrail.railtech.energetics.tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergyConsumer;
import io.github.phantamanta44.mcrail.railflux.IEnergyProvider;
import io.github.phantamanta44.mcrail.railtech.common.component.MachineCore;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentRedstone;
import io.github.phantamanta44.mcrail.railtech.common.component.impl.MachineComponentSecurity;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergizedRated;
import io.github.phantamanta44.mcrail.railtech.energetics.gui.GuiBattery;
import io.github.phantamanta44.mcrail.railtech.util.EnergyUtils;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class TileBattery extends TileEnergizedRated {

    public final String name;
    public final MachineCore machineCore;

    private final ItemStack[] inv;

    public TileBattery(Block block, String id, String name, int capacity, int transferRate) {
        super(block, id, capacity, transferRate, transferRate);
        this.name = name;
        this.inv = new ItemStack[3];
        this.machineCore = new MachineCore(this)
                .install(new MachineComponentRedstone(this))
                .install(new MachineComponentSecurity(this));
    }

    @Override
    public void init() {
        // NO-OP
    }

    public ItemStack[] getInventory() {
        return inv;
    }

    @Override
    public void tick() {
        machineCore.tick();
        if (machineCore.checkWork()) {
            requestEnergyRaw(EnergyUtils.distributeAdj(pos(), Math.min(energyStored(), getEnergyRateOut())));
        }
        if (ItemUtils.isNotNully(inv[0])) {
            IEnergyProvider provider = AdapterUtils.adapt(IEnergyProvider.class, inv[0]);
            if (provider != null)
                offerEnergyRaw(provider.requestEnergy(Math.min(getEnergyRateIn(), energyCapacity() - energyStored())));
        }
        if (ItemUtils.isNotNully(inv[1])) {
            IEnergyConsumer consumer = AdapterUtils.adapt(IEnergyConsumer.class, inv[1]);
            if (consumer != null)
                requestEnergyRaw(consumer.offerEnergy(Math.min(getEnergyRateOut(), energyStored())));
        }
        if (ItemUtils.isNotNully(inv[2]) && machineCore.offerUpgrade(inv[2])) {
            if (inv[2].getAmount() == 1)
                inv[2] = null;
            else
                inv[2].setAmount(inv[2].getAmount() - 1);
        }
    }

    @Override
    public boolean onInteract(PlayerInteractEvent event) {
        if (!machineCore.checkInteract(event))
            return false;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().isSneaking())
                return true;
            new GuiBattery(event.getPlayer(), this);
            return false;
        }
        return true;
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
        machineCore.reset();
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
