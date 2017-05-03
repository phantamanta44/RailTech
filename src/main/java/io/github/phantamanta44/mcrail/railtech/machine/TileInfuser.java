package io.github.phantamanta44.mcrail.railtech.machine;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergyProvider;
import io.github.phantamanta44.mcrail.railtech.common.recipe.impl.InfuserRecipe;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class TileInfuser extends TileEnergized {

    private static final int BASE_PWR = 20;
    private static final int BASE_WRK = 200;

    private final ItemStack[] inv;
    private int speedUpgrades;
    private InfuserRecipe.Type resource;
    private int resAmt;
    private int work;

    public TileInfuser(Block block) {
        super(block, "railtech:mac-infuser", 20000);
        this.inv = new ItemStack[5];
        this.speedUpgrades = this.resAmt = this.work = 0;
        this.resource = null;
    }

    @Override
    public void init() {
        // NO-OP
    }

    @Override
    public void tick() {
        // TODO Implement
        int energyEmpty = energyCapacity() - energyStored();
        if (energyEmpty > 0 && ItemUtils.isNotNully(inv[3])) {
            if (ItemUtils.matching(Material.REDSTONE).test(inv[3])) {
                consumeForEnergy(4);
            } else if (ItemUtils.matching(Material.REDSTONE_BLOCK).test(inv[3])) {
                consumeForEnergy(36);
            } else {
                IEnergyProvider chargeItem = AdapterUtils.adapt(IEnergyProvider.class, inv[3]);
                if (chargeItem != null)
                    offerEnergy(chargeItem.requestEnergy(energyEmpty));
            }
        }
        if (speedUpgrades < 8 && ItemUtils.isNotNully(inv[4]) && ItemUtils.instOf("railtech:mac-upgradeSpeed", inv[4])) {
            if (inv[4].getAmount() > 1)
                inv[4].setAmount(inv[4].getAmount() - 1);
            else
                inv[4] = null;
            speedUpgrades++;
        }
        lines().a(String.format("%d / %d RJ", energy, energyMax));
    }

    private void consumeForEnergy(int value) {
        int consumed = (int)Math.floor(
                Math.min(inv[3].getAmount() * value, energyCapacity() - energyStored()) / 10F);
        if (inv[3].getAmount() >= consumed)
            inv[3] = null;
        else
            inv[3].setAmount(inv[3].getAmount() - consumed);
        offerEnergy(consumed * value);
    }

    protected int power() {
        return (int)Math.floor(BASE_PWR * Math.pow(1.33, 2 * speedUpgrades));
    }

    protected int workNeeded() {
        return (int)Math.floor(BASE_WRK * Math.pow(0.75, speedUpgrades));
    }

    protected float completion() {
        return (float)work / (float)workNeeded();
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
            assert(true); // TODO Open gui
    }

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        work = dto.get("work").getAsInt();
        speedUpgrades = dto.get("speedUpgrades").getAsInt();
        if (dto.has("resource")) {
            resource = InfuserRecipe.Type.valueOf(dto.get("resource").getAsString());
            resAmt = dto.get("resAmt").getAsInt();
        }
        JsonArray invDto = dto.get("inv").getAsJsonArray();
        for (int i = 0; i < invDto.size(); i++)
            inv[i] = JsonUtils.deserItemStack(invDto.get(i));
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.addProperty("work", work);
        dto.addProperty("speedUpgrades", speedUpgrades);
        if (resource != null) {
            dto.addProperty("resource", resource.toString());
            dto.addProperty("resAmt", resAmt);
        }
        dto.add("inv", Arrays.stream(inv).map(JsonUtils::serItemStack).collect(JsonUtils.arrayCollector()));
        return dto;
    }

}
