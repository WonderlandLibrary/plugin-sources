package me.vagdedes.spartan.k.d;

import org.apache.commons.lang.StringUtils;
import java.util.Random;
import me.vagdedes.spartan.f.SpartanLocation;
import org.bukkit.Location;

public class MathUtils
{
    public MathUtils() {
        super();
    }
    
    public static double a(double abs) {
        final boolean b = abs < 0.0;
        if (b) {
            abs = Math.abs(abs);
        }
        abs *= 10.0;
        abs = (double)Math.round(abs);
        abs /= 10.0;
        return b ? (-abs) : abs;
    }
    
    public static boolean b(final double n, final double n2) {
        return n + n2 > 1.0 && n - n2 < 1.0;
    }
    
    public static double a(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n - n2;
        final double n8 = n3 - n4;
        final double n9 = n5 - n6;
        return Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
    }
    
    public static double getHorizontalDistance(final Location location, final Location location2) {
        final double n = location.getX() - location2.getX();
        final double n2 = location.getZ() - location2.getZ();
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static double b(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        final double n = spartanLocation.getX() - spartanLocation2.getX();
        final double n2 = spartanLocation.getZ() - spartanLocation2.getZ();
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static double b(final Location location, final Location location2) {
        final double n = location.getY() - location2.getY();
        return Math.sqrt(n * n);
    }
    
    public static double c(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        final double n = spartanLocation.getY() - spartanLocation2.getY();
        return Math.sqrt(n * n);
    }
    
    public static float b(float n) {
        n %= 360.0f;
        if (n >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    public static double b(double n) {
        n %= 360.0;
        if (n >= 180.0) {
            n -= 360.0;
        }
        if (n < -180.0) {
            n += 360.0;
        }
        return n;
    }
    
    public static int a(final int n, final int n2) {
        return new Random().nextInt(Math.abs(n2 - n)) + n;
    }
    
    public static double b(final double n, final double n2) {
        return n + (n2 - n) * new Random().nextDouble();
    }
    
    public static float b(final float n, final float n2) {
        return n + (n2 - n) * new Random().nextFloat();
    }
    
    public static boolean g(final double d) {
        return Math.abs(Math.getExponent(d)) == 1023;
    }
    
    public static double percentage(final double n, final double n2) {
        return (n2 <= 0.0) ? 100.0 : (n / n2 * 100.0);
    }
    
    public static boolean validNum(final String s) {
        return StringUtils.isNumeric(s) || (s.startsWith("-") && StringUtils.isNumeric(s.substring(1)));
    }
    
    public static boolean validDouble(String s) {
        if (s == null || !s.contains(".") || s.startsWith(".") || s.endsWith(".")) {
            return false;
        }
        s = s.replace(".", "");
        s = s.replace("e", "");
        s = s.replace("E", "");
        return StringUtils.isNumeric(s);
    }
}
