package io.github.phantamanta44.mcrail.railtech.util;

import io.github.phantamanta44.mcrail.railflux.IEnergyConsumer;
import io.github.phantamanta44.mcrail.util.BlockPos;
import io.github.phantamanta44.mcrail.util.SignUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class EnergyUtils {

    public static int distributeAdj(BlockPos pos, int amount) {
        return distribute(SignUtils.adjacent(pos)
                .filter(se -> se instanceof IEnergyConsumer)
                .map(se -> (IEnergyConsumer)se)
                .collect(Collectors.toList()), amount);
    }

    public static int distribute(Collection<IEnergyConsumer> cons, int amount) {
        for (int i = 0; !cons.isEmpty() && i < 3; i++) {
            int rate = (int)Math.floor((float)amount / (float)cons.size());
            Iterator<IEnergyConsumer> iter = cons.iterator();
            while (iter.hasNext()) {
                IEnergyConsumer con = iter.next();
                int transferred = con.offerEnergy(rate);
                if (transferred < 1)
                    iter.remove();
                amount -= transferred;
            }
        }
        return 80 - amount;
    }

}
