package me.frep.vulcan.spigot.util;

import org.bukkit.util.NumberConversions;
import me.frep.vulcan.spigot.data.PlayerData;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.Comparator;
import me.frep.vulcan.spigot.util.type.Tuple;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.Collection;
import java.text.DecimalFormat;

public final class MathUtil
{
    public static final double EXPANDER;
    
    public static double hypot(final double x, final double z) {
        return Math.sqrt(x * x + z * z);
    }
    
    public static double magnitude(final double x, final double y, final double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }
    
    public static String trim(final double x) {
        return new DecimalFormat("#.##").format(x);
    }
    
    public static float distanceBetweenAngles(final float alpha, final float beta) {
        final float alphaX = alpha % 360.0f;
        final float betaX = beta % 360.0f;
        final float delta = Math.abs(alphaX - betaX);
        return (float)Math.abs(Math.min(360.0 - delta, delta));
    }
    
    public static double getVariance(final Collection<? extends Number> data) {
        int count = 0;
        double sum = 0.0;
        double variance = 0.0;
        for (final Number number : data) {
            sum += number.doubleValue();
            ++count;
        }
        final double average = sum / count;
        for (final Number number : data) {
            variance += Math.pow(number.doubleValue() - average, 2.0);
        }
        return variance;
    }
    
    public static double getStandardDeviation(final Collection<? extends Number> data) {
        final double variance = getVariance(data);
        return Math.sqrt(variance);
    }
    
    public static double getSkewness(final Collection<? extends Number> data) {
        double sum = 0.0;
        int count = 0;
        final List<Double> numbers = Lists.newArrayList();
        for (final Number number : data) {
            sum += number.doubleValue();
            ++count;
            numbers.add(number.doubleValue());
        }
        Collections.sort(numbers);
        final double mean = sum / count;
        final double median = (count % 2 != 0) ? numbers.get(count / 2) : ((numbers.get((count - 1) / 2) + numbers.get(count / 2)) / 2.0);
        final double variance = getVariance(data);
        return 3.0 * (mean - median) / variance;
    }
    
    public static double getAverage(final Collection<? extends Number> data) {
        if (data == null || data.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (final Number number : data) {
            sum += number.doubleValue();
        }
        return sum / data.size();
    }
    
    public static double getKurtosis(final Collection<? extends Number> data) {
        double sum = 0.0;
        int count = 0;
        for (final Number number : data) {
            sum += number.doubleValue();
            ++count;
        }
        if (count < 3.0) {
            return 0.0;
        }
        final double efficiencyFirst = count * (count + 1.0) / ((count - 1.0) * (count - 2.0) * (count - 3.0));
        final double efficiencySecond = 3.0 * Math.pow(count - 1.0, 2.0) / ((count - 2.0) * (count - 3.0));
        final double average = sum / count;
        double variance = 0.0;
        double varianceSquared = 0.0;
        for (final Number number2 : data) {
            variance += Math.pow(average - number2.doubleValue(), 2.0);
            varianceSquared += Math.pow(average - number2.doubleValue(), 4.0);
        }
        return efficiencyFirst * (varianceSquared / Math.pow(variance / sum, 2.0)) - efficiencySecond;
    }
    public static double round(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static int getMode(final Collection<? extends Number> array) {
        int mode = (int)array.toArray()[0];
        int maxCount = 0;
        for (final Number value : array) {
            int count = 1;
            for (final Number i : array) {
                if (i.equals(value)) {
                    ++count;
                }
                if (count > maxCount) {
                    mode = (int)value;
                    maxCount = count;
                }
            }
        }
        return mode;
    }
    
    private static double getMedian(final List<Double> data) {
        if (data.size() % 2 == 0) {
            return (data.get(data.size() / 2) + data.get(data.size() / 2 - 1)) / 2.0;
        }
        return data.get(data.size() / 2);
    }
    
    public static boolean isExponentiallySmall(final Number number) {
        return number.doubleValue() < 1.0 && Double.toString(number.doubleValue()).contains("E");
    }
    
    public static long getGcd(final long current, final long previous) {
        return (previous <= 16384L) ? current : getGcd(previous, current % previous);
    }
    
    public static double getGcd(final double a, final double b) {
        if (a < b) {
            return getGcd(b, a);
        }
        if (Math.abs(b) < 0.001) {
            return a;
        }
        return getGcd(b, a - Math.floor(a / b) * b);
    }
    
    public static double angleOf(final double minX, final double minZ, final double maxX, final double maxZ) {
        final double deltaY = minZ - maxZ;
        final double deltaX = maxX - minX;
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0.0) ? (360.0 + result) : result;
    }
    
    public static double getDistanceBetweenAngles360(final double alpha, final double beta) {
        final double abs = Math.abs(alpha % 360.0 - beta % 360.0);
        return Math.abs(Math.min(360.0 - abs, abs));
    }
    
    public static double getDistanceBetweenAngles360Raw(final double alpha, final double beta) {
        return Math.abs(alpha % 360.0 - beta % 360.0);
    }
    
    public static double getCps(final Collection<? extends Number> data) {
        return 20.0 / getAverage(data) * 50.0;
    }
    
    public static int getDuplicates(final Collection<? extends Number> data) {
        return (int)(data.size() - data.stream().distinct().count());
    }
    
    public static int getDistinct(final Collection<? extends Number> data) {
        return (int)data.stream().distinct().count();
    }
    
    public static int getPingInTicks(final PlayerData data) {
        return NumberConversions.floor(data.getConnectionProcessor().getTransactionPing() / 50.0) + 3;
    }
    
    private MathUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    static {
        EXPANDER = Math.pow(2.0, 24.0);
    }
}
