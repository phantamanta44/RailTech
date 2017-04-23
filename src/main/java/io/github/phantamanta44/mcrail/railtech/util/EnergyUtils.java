package io.github.phantamanta44.mcrail.railtech.util;

import io.github.phantamanta44.mcrail.railflux.IEnergyConsumer;
import io.github.phantamanta44.mcrail.railtech.common.tile.TileEnergized;
import io.github.phantamanta44.mcrail.util.SignUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EnergyUtils {

    public static int distribute(TileEnergized tile, int amount) {
        List<IEnergyConsumer> cons = SignUtils.adjacent(tile.pos())
                .filter(se -> se instanceof IEnergyConsumer)
                .map(se -> (IEnergyConsumer)se)
                .collect(Collectors.toList());
        int toDis = Math.min(80, tile.energyStored());
        for (int i = 0; !cons.isEmpty() && i < 3; i++) {
            int rate = (int)Math.floor((float)toDis / (float)cons.size());
            Iterator<IEnergyConsumer> iter = cons.iterator();
            while (iter.hasNext()) {
                IEnergyConsumer con = iter.next();
                int transferred = con.offerEnergy(rate);
                if (transferred < 1)
                    iter.remove();
                toDis -= transferred;
            }
        }
        return 80 - toDis;
    }

}
