package com.elevatemc.anticheat.util.mcp;

import java.text.DecimalFormat;

public class TimeUtil {
    public static double convert(final long time, final int trim, TimeUnits type) {
        if (type == TimeUnits.FIT) {
            if (time < 60000L) {
                type = TimeUnits.SECONDS;
            }
            else if (time < 3600000L) {
                type = TimeUnits.MINUTES;
            }
            else if (time < 86400000L) {
                type = TimeUnits.HOURS;
            }
            else {
                type = TimeUnits.DAYS;
            }
        }
        if (type == TimeUnits.DAYS) {
            return trim(trim, time / 8.64E7);
        }
        if (type == TimeUnits.HOURS) {
            return trim(trim, time / 3600000.0);
        }
        if (type == TimeUnits.MINUTES) {
            return trim(trim, time / 60000.0);
        }
        if (type == TimeUnits.SECONDS) {
            return trim(trim, time / 1000.0);
        }
        return trim(trim, (double)time);
    }

    public static long elapsed(final long start) {
        return System.currentTimeMillis() - start;
    }

    public static double trim(final int degree, final double d) {
        String format = "#.#";
        for (int i = 1; i < degree; ++i) {
            format += "#";
        }
        final DecimalFormat twoDForm = new DecimalFormat(format);
        return Double.parseDouble(twoDForm.format(d).replace(",", "."));
    }

    public enum TimeUnits
    {
        FIT,
        DAYS,
        HOURS,
        MINUTES,
        SECONDS,
        MILLISECONDS;
    }


}
