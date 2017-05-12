package io.github.phantamanta44.mcrail.railtech.common.energy;

import io.github.phantamanta44.mcrail.railtech.RTMain;
import io.github.phantamanta44.mcrail.railtech.common.tile.energy.IMachinePneumatic;
import io.github.phantamanta44.mcrail.util.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class EnergyType<T extends INetworkable> {
    
    public static final EnergyType<?> STEAM = new EnergyType<>("steam", "L");
    public static final EnergyType<IMachinePneumatic> PNEU = new EnergyType<IMachinePneumatic>("pneu", "Pa") {
        @Override
        public void tick(IMachinePneumatic tile, BlockPos pos) {
            tile.ruptureTest((int)Math.floor(RTMain.INSTANCE.netMan().get(id, pos).averageEnergy()));
        }
    };
    public static final EnergyType<?> ELEC = new EnergyType<>("elec", "RJ");
    
    private static final Map<String, EnergyType> types = new HashMap<>();
    
    public final String id, unit;
    
    private EnergyType(String id, String unit) {
        this.id = id;
        this.unit = unit;
        types.put(id, this);
    }
    
    public void tick(T tile, BlockPos pos) {
        // NO-OP
    }
    
    public static EnergyType byId(String id) {
        return types.get(id);
    }
    
}
