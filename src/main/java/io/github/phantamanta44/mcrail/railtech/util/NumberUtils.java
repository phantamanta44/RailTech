package io.github.phantamanta44.mcrail.railtech.util;

public class NumberUtils {

    private static final String[] prefixes = new String[] {"", "k", "M", "G", "T", "P", "E"};

    public static String formatSI(int num, String unit) {
        if (num == 0)
            return num + " " + unit;
        int magnitude = (int)Math.floor(Math.log10(Math.abs(num)) / 3);
        if (magnitude == 0)
            return num + " " + unit;
        return String.format("%.2f %s%s", num / Math.pow(10, magnitude * 3), prefixes[magnitude], unit);
    }

    public static String formatSI(long num, String unit) {
        if (num == 0)
            return num + " " + unit;
        int magnitude = (int)Math.floor(Math.log10(Math.abs(num)) / 3);
        if (magnitude == 0)
            return num + " " + unit;
        return String.format("%.2f %s%s", num / Math.pow(10, magnitude * 3), prefixes[magnitude], unit);
    }

}
