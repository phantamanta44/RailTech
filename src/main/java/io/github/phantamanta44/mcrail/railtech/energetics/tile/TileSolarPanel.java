package io.github.phantamanta44.mcrail.railtech.energetics.tile;

import io.github.phantamanta44.mcrail.railtech.energetics.gui.GuiSolarPanel;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TileSolarPanel extends TileGenerator {

    public TileSolarPanel(Block block) {
        super(block, "railtech:enr-gen-solar", 0, 30000, 360);
    }

    @Override
    public void tick() {
        super.tick();
        if (canWork()) {
            float cycle = (float)(block().getWorld().getTime() + 1350) * 2.1893e-4F;
            offerEnergyRaw((int)Math.round(Math.sin(cycle) * getEnvironment().multiplier));
        }
    }

    @Override
    protected boolean doInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking()) {
            new GuiSolarPanel(event.getPlayer(), this);
            return false;
        }
        return true;
    }

    @Override
    protected boolean canWork() {
        return super.canWork() && getEnvironment().favourable;
    }

    public EnvironmentState getEnvironment() {
        World world = block().getWorld();
        if (world.getEnvironment() != World.Environment.NORMAL)
            return EnvironmentState.HOSTILE;
        if (world.getHighestBlockYAt(location()) - 1 > pos().y())
            return EnvironmentState.OBSTRUCTED;
        if (world.isThundering())
            return EnvironmentState.STORM;
        if (world.hasStorm())
            return EnvironmentState.RAINY;
        long time = world.getTime();
        if (time >= 13000 && time <= 22650)
            return EnvironmentState.NIGHT;
        return EnvironmentState.NORMAL;
    }

    public enum EnvironmentState {

        HOSTILE(false, 0),
        OBSTRUCTED(false, 0),
        NIGHT(false, 0),
        NORMAL(true, 4),
        RAINY(true, 3),
        STORM(true, 2);

        public final boolean favourable;
        public final int multiplier;

        EnvironmentState(boolean favourable, int multiplier) {
            this.favourable = favourable;
            this.multiplier = multiplier;
        }

    }

}
