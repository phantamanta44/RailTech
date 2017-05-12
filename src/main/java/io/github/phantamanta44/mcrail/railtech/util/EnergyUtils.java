package io.github.phantamanta44.mcrail.railtech.util;

import io.github.phantamanta44.mcrail.railflux.IEnergyConsumer;
import io.github.phantamanta44.mcrail.railflux.IEnergyProvider;
import io.github.phantamanta44.mcrail.util.BlockPos;
import io.github.phantamanta44.mcrail.util.SignUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class EnergyUtils {

    private static final String[] SI_PREF = new String[] {
            "", "k", "M", "G", "T", "P"
    };

    public static int distributeAdj(BlockPos pos, int amount) {
        return distribute(SignUtils.adjacent(pos)
                .filter(se -> se instanceof IEnergyConsumer)
                .map(se -> (IEnergyConsumer)se)
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
        return request(SignUtils.adjacent(pos)
                .filter(se -> se instanceof IEnergyProvider)
                .map(se -> (IEnergyProvider)se)
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

    public static String format(long amount, String unit) {
        int power = (int)Math.floor(Math.log10(amount) / 3D);
        return String.format("%.2f %s%s",
                amount / Math.pow(10, power * 3),
                SI_PREF[Math.min(Math.max(power, 0), SI_PREF.length)],
                unit);
    }
    
}
