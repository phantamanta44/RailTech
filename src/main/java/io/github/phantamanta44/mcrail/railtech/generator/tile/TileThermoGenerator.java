package io.github.phantamanta44.mcrail.railtech.generator.tile;

import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import io.github.phantamanta44.mcrail.railtech.generator.gui.GuiThermoGenerator;
import io.github.phantamanta44.mcrail.railtech.util.BurnTime;
import io.github.phantamanta44.mcrail.railtech.util.EnergyUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class TileThermoGenerator extends TileEnergized {

    private int burnTime;
    private ItemStack[] fuel;

    public TileThermoGenerator(Block block) {
        super(block, "railtech:gen-heat", 100000);
        this.burnTime = 0;
        this.fuel = new ItemStack[1];
    }

    public int getBurnTime() {
        return burnTime;
    }

    public ItemStack[] getFuelInv() {
        return fuel;
    }

    @Override
    public void init() {
        // NO-OP
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.addProperty("burnTime", burnTime);
        dto.add("fuel", JsonUtils.serItemStack(fuel[0]));
        return dto;
    }

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        this.burnTime = dto.get("burnTime").getAsInt();
        this.fuel[0] = JsonUtils.deserItemStack(dto.get("fuel"));
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        if (fuel[0] != null)
            drops.add(fuel[0]);
    }

    @Override
    public void tick() {
        if (burnTime > 0) {
            energy = Math.min(energy + 40, energyMax);
            burnTime--;
            if (Rail.currentTick() % 14 == 0)
                pos().world().playEffect(location().add(0.5, 0.5, 0.5), Effect.MOBSPAWNER_FLAMES, 0);
        } else {
            if (fuel[0] != null) {
                int potential = BurnTime.of(fuel[0].getType());
                if (potential > 0) {
                    if (fuel[0].getType() == Material.LAVA_BUCKET)
                        fuel[0].setType(Material.BUCKET);
                    else if (fuel[0].getAmount() > 1)
                        fuel[0].setAmount(fuel[0].getAmount() - 1);
                    else
                        fuel[0] = null;
                    burnTime = potential;
                }
            }
        }
        if (energy > 0)
            energy -= EnergyUtils.distributeAdj(pos(), Math.min(energy, 80));
        lines().a(String.format("%d / %d RJ (%.1f%%)", energy, energyMax, 100 * (float)energy / (float)energyMax));
        lines().b(burnTime > 0 ? String.format("%d tick(s)", burnTime) : "Nothing's burning!");
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
            new GuiThermoGenerator(this, event.getPlayer());
    }

    @Override
    public int offerEnergy(int amount) {
        return 0;
    }

    @Override
    public boolean canAccept(int amount) {
        return false;
    }

}
