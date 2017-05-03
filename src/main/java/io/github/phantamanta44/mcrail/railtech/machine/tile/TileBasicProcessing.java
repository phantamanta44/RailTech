package io.github.phantamanta44.mcrail.railtech.machine.tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergyProvider;
import io.github.phantamanta44.mcrail.railtech.common.UpgradeType;
import io.github.phantamanta44.mcrail.railtech.common.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.common.recipe.RecipeType;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import io.github.phantamanta44.mcrail.railtech.machine.gui.GuiBasicProcessing;
import io.github.phantamanta44.mcrail.railtech.util.Pair;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public abstract class TileBasicProcessing<T> extends TileEnergized {

    protected final String name;
    protected final RecipeType<ItemStack, T> recipeType;
    protected final int basePower;
    protected final int baseWorkNeeded;
    protected final ItemStack[] inv;
    protected int work;
    protected int speedUpgrades;

    public TileBasicProcessing(Block block, String id, String name, RecipeType<ItemStack, T> recipeType, int power, int workTicks, int bufferSize) {
        super(block, id, bufferSize);
        this.name = name;
        this.recipeType = recipeType;
        this.basePower = power;
        this.baseWorkNeeded = workTicks;
        this.inv = new ItemStack[6];
        this.work = this.speedUpgrades = 0;
    }

    @Override
    public void tick() {
        IMachineRecipe<ItemStack, T> recipe = RTRecipes.recipeFor(recipeType, inv[0]);
        if (recipe != null && recipe.canProcess(inv[0], outputFor(recipe, inv))) {
            int power = power();
            if (canProvide(power)) {
                requestEnergy(power);
                if (++work >= workNeeded()) {
                    work = 0;
                    addOutput(recipe.output(inv[0]), inv);
                    if (inv[0].getAmount() > 1)
                        inv[0].setAmount(inv[0].getAmount() - 1);
                    else
                        inv[0] = null;
                }
            }
            lines().b(String.format("Processing... (%.0f%%)", completion() * 100));
        } else {
            work = 0;
            lines().b("No Processing!");
        }
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
        if (speedUpgrades < 8 && ItemUtils.isNotNully(inv[5]) && ItemUtils.instOf("railtech:mac-upgradeSpeed", inv[5])) {
            if (inv[5].getAmount() > 1)
                inv[5].setAmount(inv[5].getAmount() - 1);
            else
                inv[5] = null;
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

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
            new GuiBasicProcessing<T>(name, event.getPlayer(), this);
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        Arrays.stream(inv)
                .filter(Objects::nonNull)
                .forEach(drops::add);
    }

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        work = dto.get("work").getAsInt();
        speedUpgrades = dto.get("speedUpgrades").getAsInt();
        JsonArray invDto = dto.get("inv").getAsJsonArray();
        for (int i = 0; i < invDto.size(); i++)
            inv[i] = JsonUtils.deserItemStack(invDto.get(i));
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.addProperty("work", work);
        dto.addProperty("speedUpgrades", speedUpgrades);
        dto.add("inv", Arrays.stream(inv).map(JsonUtils::serItemStack).collect(JsonUtils.arrayCollector()));
        return dto;
    }

    protected int power() {
        return (int)Math.floor(basePower * Math.pow(1.33, 2 * speedUpgrades));
    }

    protected int workNeeded() {
        return (int)Math.floor(baseWorkNeeded * Math.pow(0.75, speedUpgrades));
    }

    public float completion() {
        return (float)work / (float)workNeeded();
    }

    public ItemStack[] inv() {
        return inv;
    }

    public RecipeType<ItemStack, T> recipeType() {
        return recipeType;
    }

    public int upgrades(UpgradeType type) {
        switch (type) {
            case SPEED:
                return speedUpgrades;
            default:
                return 0;
        }
    }

    public void offsetUpgrades(UpgradeType type, int qty) {
        switch (type) {
            case SPEED:
                speedUpgrades += qty;
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private static <I, O> O outputFor(IMachineRecipe<ItemStack, I> recipe, ItemStack[] inv) {
        if (recipe.type().outputType() == ItemStack.class)
            return (O)inv[1];
        else if (recipe.type().outputType() == Pair.class)
            return (O)Pair.of(inv[1], inv[2]);
        return null;
    }

    @SuppressWarnings("unchecked")
    private static void addOutput(Object output, ItemStack[] inv) {
        if (output instanceof ItemStack) {
            if (ItemUtils.isNully(inv[1]))
                inv[1] = (ItemStack)output;
            else
                inv[1].setAmount(inv[1].getAmount() + ((ItemStack)output).getAmount());
        } else if (output instanceof Pair) {
            Pair<ItemStack, ItemStack> pair = (Pair<ItemStack, ItemStack>)output;
            if (ItemUtils.isNully(inv[1]))
                inv[1] = pair.getA();
            else
                inv[1].setAmount(inv[1].getAmount() + pair.getA().getAmount());
            if (ItemUtils.isNotNully(pair.getB())) {
                if (ItemUtils.isNully(inv[2]))
                    inv[2] = pair.getB();
                else
                    inv[2].setAmount(inv[2].getAmount() + pair.getB().getAmount());
            }
        }
    }

}
