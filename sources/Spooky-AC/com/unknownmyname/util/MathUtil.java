/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.MathHelper
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.util;

import com.google.common.base.Objects;
import com.unknownmyname.data.PlayerLocation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MathHelper;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MathUtil {
    public static float relEntityRoundLook(float d) {
        return MathHelper.d((float)(d * 256.0f / 360.0f));
    }

    public static int gcd(int number1, int number2) {
        return BigInteger.valueOf(number1).gcd(BigInteger.valueOf(number2)).intValue();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double deviationSquared(Iterable<? extends Number> numbers) {
        total = 0.0;
        i = "".length();
        var5_3 = numbers.iterator();
        "".length();
        if (!false) ** GOTO lbl11
        throw null;
lbl-1000: // 1 sources:
        {
            number = var5_3.next();
            total += number.doubleValue();
            ++i;
lbl11: // 2 sources:
            ** while (var5_3.hasNext())
        }
lbl12: // 1 sources:
        average = total / (double)i;
        deviation = 0.0;
        var9_7 = numbers.iterator();
        "".length();
        if (2 >= 0) ** GOTO lbl21
        throw null;
lbl-1000: // 1 sources:
        {
            number = var9_7.next();
            deviation += Math.pow(number.doubleValue() - average, 2.0);
lbl21: // 2 sources:
            ** while (var9_7.hasNext())
        }
lbl22: // 1 sources:
        return deviation / (double)(i - " ".length());
    }

    public static boolean onGround(double y) {
        if (y % 0.015625 == 0.0) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double totalAbs(Iterable<? extends Number> numbers) {
        total = 0.0;
        var4_2 = numbers.iterator();
        "".length();
        if (1 != 2) ** GOTO lbl9
        throw null;
lbl-1000: // 1 sources:
        {
            number = var4_2.next();
            total += Math.abs(number.doubleValue());
lbl9: // 2 sources:
            ** while (var4_2.hasNext())
        }
lbl10: // 1 sources:
        return total;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double average(Iterable<? extends Number> numbers) {
        total = 0.0;
        i = "".length();
        var5_3 = numbers.iterator();
        "".length();
        if (0 != 2) ** GOTO lbl11
        throw null;
lbl-1000: // 1 sources:
        {
            number = var5_3.next();
            total += number.doubleValue();
            ++i;
lbl11: // 2 sources:
            ** while (var5_3.hasNext())
        }
lbl12: // 1 sources:
        return total / (double)i;
    }

    public static double deviation(Iterable<? extends Number> numbers) {
        return Math.sqrt(MathUtil.deviationSquared(numbers));
    }

    public static float[] getRotationFromPosition(PlayerLocation playerLocation, PlayerLocation targetLocation) {
        double xDiff = targetLocation.getX() - playerLocation.getX();
        double zDiff = targetLocation.getZ() - playerLocation.getZ();
        double yDiff = targetLocation.getY() + 0.81 - playerLocation.getY() - 1.2;
        float dist = (float)Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0 / 3.141592653589793) - 90.0f;
        float pitch = (float)(-Math.atan2(yDiff, dist) * 180.0 / 3.141592653589793);
        float[] arrf = new float["  ".length()];
        arrf["".length()] = yaw;
        arrf[" ".length()] = pitch;
        return arrf;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double lowest(Iterable<? extends Number> numbers) {
        lowest = null;
        var2 = numbers.iterator();
        "".length();
        if (2 >= 2) ** GOTO lbl14
        throw null;
lbl-1000: // 1 sources:
        {
            number = var2.next();
            if (lowest != null && number.doubleValue() >= lowest) {
                "".length();
                if (1 >= -1) continue;
                throw null;
            }
            lowest = number.doubleValue();
lbl14: // 3 sources:
            ** while (var2.hasNext())
        }
lbl15: // 1 sources:
        return (Double)Objects.firstNonNull(lowest, (Object)0.0);
    }

    public static double relEntityRoundPos(double d) {
        return (double)MathHelper.floor((double)(d * 32.0)) / 32.0;
    }

    public static int getPing(Player p) {
        return ((CraftPlayer)p).getHandle().ping;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double varianceSquared(Number value, Iterable<? extends Number> numbers) {
        variance = 0.0;
        i = "".length();
        var6_4 = numbers.iterator();
        "".length();
        if (2 > 0) ** GOTO lbl11
        throw null;
lbl-1000: // 1 sources:
        {
            number = var6_4.next();
            variance += Math.pow(number.doubleValue() - value.doubleValue(), 2.0);
            ++i;
lbl11: // 2 sources:
            ** while (var6_4.hasNext())
        }
lbl12: // 1 sources:
        return variance / (double)(i - " ".length());
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double hypotSquared(double ... values) {
        total = 0.0;
        var3 = values;
        var4 = values.length;
        var5 = "".length();
        "".length();
        if (false < true) ** GOTO lbl12
        throw null;
lbl-1000: // 1 sources:
        {
            value = var3[var5];
            total += Math.pow(value, 2.0);
            ++var5;
lbl12: // 2 sources:
            ** while (var5 < var4)
        }
lbl13: // 1 sources:
        return total;
    }

    public static double highest(Number ... numbers) {
        return MathUtil.highest(Arrays.asList(numbers));
    }

    public static double getDistanceBetweenAngles(float angle1, float angle2) {
        float distance = Math.abs(angle1 - angle2) % 360.0f;
        if (distance > 180.0f) {
            distance = 360.0f - distance;
        }
        return distance;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double total(Iterable<? extends Number> numbers) {
        total = 0.0;
        var4_2 = numbers.iterator();
        "".length();
        if (true >= true) ** GOTO lbl9
        throw null;
lbl-1000: // 1 sources:
        {
            number = var4_2.next();
            total += number.doubleValue();
lbl9: // 2 sources:
            ** while (var4_2.hasNext())
        }
lbl10: // 1 sources:
        return total;
    }

    public static int toInt(float number) {
        return (int)((double)new BigDecimal(number).setScale(0xA9 ^ 0xAC, RoundingMode.UP).floatValue() * 10000.0);
    }

    public static double variance(Number value, Iterable<? extends Number> numbers) {
        return Math.sqrt(MathUtil.varianceSquared(value, numbers));
    }

    public static double hypot(double ... values) {
        return Math.sqrt(MathUtil.hypotSquared(values));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double highest(Iterable<? extends Number> numbers) {
        highest = null;
        var2 = numbers.iterator();
        "".length();
        if (4 > 1) ** GOTO lbl14
        throw null;
lbl-1000: // 1 sources:
        {
            number = var2.next();
            if (highest != null && number.doubleValue() <= highest) {
                "".length();
                if (4 > 3) continue;
                throw null;
            }
            highest = number.doubleValue();
lbl14: // 3 sources:
            ** while (var2.hasNext())
        }
lbl15: // 1 sources:
        return (Double)Objects.firstNonNull(highest, (Object)0.0);
    }

    public static double getDistanceBetweenAngles360(double angle1, double angle2) {
        double distance = Math.abs(angle1 % 360.0 - angle2 % 360.0);
        distance = Math.min(360.0 - distance, distance);
        return Math.abs(distance);
    }

    public static int gcd(long limit, int a, int b) {
        int n;
        if ((long)b <= limit) {
            n = a;
            "".length();
            if (true <= false) {
                throw null;
            }
        } else {
            n = MathUtil.gcd(limit, b, a % b);
        }
        return n;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static Double getMinimumAngle(PlayerLocation playerLocation, PlayerLocation ... targetLocations) {
        angle = null;
        var3 = targetLocations;
        var4 = targetLocations.length;
        var5 = "".length();
        "".length();
        if (4 != 1) ** GOTO lbl22
        throw null;
lbl-1000: // 1 sources:
        {
            targetLocation = var3[var5];
            xDiff = targetLocation.getX() - playerLocation.getX();
            zDiff = targetLocation.getZ() - playerLocation.getZ();
            yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0 / 3.141592653589793) - 90.0f;
            yawDiff = MathUtil.getDistanceBetweenAngles360(playerLocation.getYaw(), yaw);
            if (angle != null && angle <= yawDiff) {
                "".length();
                if (0 >= 4) {
                    throw null;
                }
            } else {
                angle = yawDiff;
            }
            ++var5;
lbl22: // 2 sources:
            ** while (var5 < var4)
        }
lbl23: // 1 sources:
        return angle;
    }
}

