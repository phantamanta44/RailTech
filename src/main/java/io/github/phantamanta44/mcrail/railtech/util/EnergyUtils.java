package io.github.phantamanta44.mcrail.railtech.util;

import io.github.phantamanta44.mcrail.railflux.IEnergyConsumer;
import io.github.phantamanta44.mcrail.railflux.IEnergyProvider;
import io.github.phantamanta44.mcrail.util.BlockPos;
import io.github.phantamanta44.mcrail.util.TileUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class EnergyUtils {

    public static int distributeAdj(BlockPos pos, int amount) {
        return distribute(TileUtils.adjacent(pos)
                .filter(tile -> tile instanceof IEnergyConsumer)
                .map(tile -> (IEnergyConsumer)tile)
                .collect(Collectors.toList()), amount);
    }
    
    public static int distribute(Collection<? extends IEnergyConsumer> cons, int amount) {
        return (int)distribute(cons, (long)amount);
    }

    public static long distribute(Collection<? extends IEnergyConsumer> cons, long amount) {
        long toDis = amount;
        for (int i = 0; !cons.isEmpty() && i < 3; i++) {
            int rate = (int)Math.floor((float)toDis / (float)cons.size());
            Iterator<? extends IEnergyConsumer> iter = cons.iterator();
            while (iter.hasNext()) {
                IEnergyConsumer con = iter.next();
                int transferred = con.offerEnergy(rate);
                if (transferred < 1)
                    iter.remove();
                toDis -= transferred;
            }
        }
        return amount - toDis;
    }

    public static int requestAdj(BlockPos pos, int amount) {
        return request(TileUtils.adjacent(pos)
                .filter(tile -> tile instanceof IEnergyProvider)
                .map(tile -> (IEnergyProvider)tile)
                .collect(Collectors.toList()), amount);
    }

    public static int request(Collection<? extends IEnergyProvider> provs, int amount) {
        return (int)request(provs, (long)amount);
    }

    public static long request(Collection<? extends IEnergyProvider> provs, long amount) {
        long toReq = amount;
        for (int i = 0; !provs.isEmpty() && i < 3; i++) {
            int rate = (int)Math.floor((float)toReq / (float)provs.size());
            Iterator<? extends IEnergyProvider> iter = provs.iterator();
            while (iter.hasNext()) {
                IEnergyProvider prov = iter.next();
                int transferred = prov.requestEnergy(rate);
                if (transferred < 1)
                    iter.remove();
                toReq -= transferred;
            }
        }
        return amount - toReq;
    }
    
}
