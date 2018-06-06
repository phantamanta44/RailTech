package io.github.phantamanta44.mcrail.railtech.machine.tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.phantamanta44.mcrail.railflux.IEnergyProvider;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.IMachineRecipe;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.RTRecipes;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.input.IMachineInput;
import io.github.phantamanta44.mcrail.railtech.machine.recipe.output.IMachineOutput;
import io.github.phantamanta44.mcrail.util.AdapterUtils;
import io.github.phantamanta44.mcrail.util.ItemUtils;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public abstract class TileRecipeMachine<T, I extends IMachineInput<T>, O extends IMachineOutput, R extends IMachineRecipe<T, I, O>>
        extends TileMachine {

    protected final ItemStack[] inv;

    private final Class<R> recipeType;
    private final int workNeeded;
    private final int energyPerProcess;
    private int work;

    public TileRecipeMachine(Block block, String id, int invSize, Class<R> recipeType,
                             int ticksNeeded, int energyPerProcess, int energyMax, int energyRate) {
        super(block, id, energyMax, energyRate);
        this.recipeType = recipeType;
        this.workNeeded = ticksNeeded * 100;
        this.energyPerProcess = energyPerProcess;
        this.inv = new ItemStack[invSize + 2];
        this.work = 0;
        // TODO install components
    }

    public ItemStack[] getInventory() {
        return inv;
    }

    @Override
    public void modifyDrops(Collection<ItemStack> drops) {
        super.modifyDrops(drops);
        Arrays.stream(inv)
                .filter(Objects::nonNull)
                .forEach(drops::add);
    }

    public int getEnergyPerProcess() {
        return machineCore.modifyEnergyConsumption(energyPerProcess);
    }

    public int getWorkRate() {
        return machineCore.modifyWorkRate(100);
    }

    public float getProcessingProgress() {
        return (float)work / (float)workNeeded;
    }

    public void reset() {
        // TODO reset machine properties
    }

    @Override
    public void tick() {
        super.tick();
        if (canWork()) {
            R recipe = RTRecipes.forType(recipeType).findRecipe(getInput());
            if (recipe != null) {
                O output = recipe.mapToOutput(getInput());
                if (canAcceptOutput(output)) {
                    int energyNeeded = (int)Math.ceil((float)getEnergyPerProcess() * getWorkRate() / workNeeded);
                    if (energyStored() > energyNeeded) {
                        requestEnergyRaw(energyNeeded);
                        if ((work += getWorkRate()) > workNeeded) {
                            work = 0;
                            process(recipe, output);
                        } else {
                            doWork();
                        }
                    }
                }
            } else {
                work = 0;
            }
        }
        if (ItemUtils.isNotNully(inv[0])) {
            IEnergyProvider provider = AdapterUtils.adapt(IEnergyProvider.class, inv[0]);
            if (provider != null)
                offerEnergyRaw(provider.requestEnergy(Math.min(getEnergyRateIn(), energyCapacity() - energyStored())));
        }
        if (ItemUtils.isNotNully(inv[1]) && machineCore.offerUpgrade(inv[1])) {
            if (inv[1].getAmount() == 1)
                inv[1] = null;
            else
                inv[1].setAmount(inv[1].getAmount() - 1);
        }
    }

    protected abstract T getInput();

    protected abstract boolean canAcceptOutput(O output);

    protected abstract void doWork();

    protected abstract void process(R recipe, O output);

    @Override
    public void deserialize(JsonObject dto) {
        super.deserialize(dto);
        this.work = dto.get("work").getAsInt();
        JsonArray invDto = dto.get("inv").getAsJsonArray();
        for (int i = 0; i < invDto.size(); i++)
            inv[i] = JsonUtils.deserItemStack(invDto.get(i));
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = super.serialize();
        dto.addProperty("work", work);
        dto.add("inv", Arrays.stream(inv)
                .map(JsonUtils::serItemStack)
                .collect(JsonUtils.arrayCollector()));
        return dto;
    }

}
