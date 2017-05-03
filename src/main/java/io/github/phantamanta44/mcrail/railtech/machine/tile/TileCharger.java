package io.github.phantamanta44.mcrail.railtech.machine.tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergyConsumer;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import io.github.phantamanta44.mcrail.railtech.machine.gui.GuiCharger;
import io.github.phantamanta44.mcrail.railtech.util.EnergyUtils;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class TileCharger extends TileEnergized {

    private ItemStack[] inv;

    public TileCharger(Block block) {
        super(block, "railtech:mac-charger", 500000);
        this.inv = new ItemStack[5];
    }

    public ItemStack[] getInventory() {
        return inv;
    }

    @Override
    public void init() {
        // NO-OP
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.add("inv", Arrays.stream(inv).map(JsonUtils::serItemStack).collect(JsonUtils.arrayCollector()));
        return dto;
    }

    @Override
    public void deserialize(JsonObject dto) {
        JsonArray invDto = dto.get("inv").getAsJsonArray();
        for (int i = 0; i < invDto.size(); i++)
            inv[i] = JsonUtils.deserItemStack(invDto.get(i));
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        Arrays.stream(inv)
                .filter(Objects::nonNull)
                .forEach(drops::add);
    }

    @Override
    public void tick() {
        if (energy > 0) {
            energy -= EnergyUtils.distribute(Arrays.stream(inv)
                    .filter(Objects::nonNull)
                    .map(i -> AdapterUtils.adapt(IEnergyConsumer.class, i))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()),
                    Math.min(energy, (int)Math.floor(7920 * (float)energy / (float)energyMax) + 80));
        }
        lines().a(String.format("%d / %d RJ (%.1f%%)", energy, energyMax, 100 * (float)energy / (float)energyMax));
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
            new GuiCharger(this, event.getPlayer());
    }

    @Override
    public int requestEnergy(int amount) {
        return 0;
    }

    @Override
    public boolean canProvide(int amount) {
        return false;
    }

}