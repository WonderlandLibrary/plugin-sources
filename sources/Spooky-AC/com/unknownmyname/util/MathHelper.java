/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.util;

import java.util.Random;
import java.util.UUID;

public class MathHelper {
    private static final /* synthetic */ double field_181163_d;
    public static /* synthetic */ boolean fastMath;
    public static final /* synthetic */ float deg2Rad = 0.017453292f;
    public static final /* synthetic */ float PId2 = 1.5707964f;
    public static final /* synthetic */ float PI2 = 6.2831855f;
    private static final /* synthetic */ int[] multiplyDeBruijnBitPosition;
    private static final /* synthetic */ float[] SIN_TABLE_FAST;
    private static final /* synthetic */ float[] SIN_TABLE;
    private static final /* synthetic */ double[] field_181164_e;
    public static final /* synthetic */ float PI = 3.1415927f;
    public static final /* synthetic */ float field_180189_a;
    private static final /* synthetic */ double[] field_181165_f;

    public static int parseIntWithDefault(String p_82715_0_, int p_82715_1_) {
        try {
            return Integer.parseInt(p_82715_0_);
        }
        catch (Throwable var3) {
            return p_82715_1_;
        }
    }

    public static int calculateLogBaseTwo(int p_151239_0_) {
        int n;
        if (MathHelper.isPowerOfTwo(p_151239_0_)) {
            n = "".length();
            "".length();
            if (false) {
                throw null;
            }
        } else {
            n = " ".length();
        }
        return MathHelper.calculateLogBaseTwoDeBruijn(p_151239_0_) - n;
    }

    public static double abs_max(double p_76132_0_, double p_76132_2_) {
        double d;
        if (p_76132_0_ < 0.0) {
            p_76132_0_ = -p_76132_0_;
        }
        if (p_76132_2_ < 0.0) {
            p_76132_2_ = -p_76132_2_;
        }
        if (p_76132_0_ > p_76132_2_) {
            d = p_76132_0_;
            "".length();
            if (4 <= 2) {
                throw null;
            }
        } else {
            d = p_76132_2_;
        }
        return d;
    }

    public static double func_181161_i(double p_181161_0_) {
        double d0 = 0.5 * p_181161_0_;
        long i = Double.doubleToRawLongBits(p_181161_0_);
        i = 6910469410427058090L - (i >> " ".length());
        p_181161_0_ = Double.longBitsToDouble(i);
        p_181161_0_ *= 1.5 - d0 * p_181161_0_ * p_181161_0_;
        return p_181161_0_;
    }

    public static long floor_double_long(double p_76124_0_) {
        long l;
        long var2 = (long)p_76124_0_;
        if (p_76124_0_ < (double)var2) {
            l = var2 - 1L;
            "".length();
            if (4 <= -1) {
                throw null;
            }
        } else {
            l = var2;
        }
        return l;
    }

    public static float wrapAngleTo180_float(float p_76142_0_) {
        float f;
        p_76142_0_ %= 360.0f;
        if (f >= 180.0f) {
            p_76142_0_ -= 360.0f;
        }
        if (p_76142_0_ < -180.0f) {
            p_76142_0_ += 360.0f;
        }
        return p_76142_0_;
    }

    public static double clamp_double(double p_151237_0_, double p_151237_2_, double p_151237_4_) {
        double d;
        if (p_151237_0_ < p_151237_2_) {
            d = p_151237_2_;
            "".length();
            if (false >= true) {
                throw null;
            }
        } else if (p_151237_0_ > p_151237_4_) {
            d = p_151237_4_;
            "".length();
            if (4 != 4) {
                throw null;
            }
        } else {
            d = p_151237_0_;
        }
        return d;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    static {
        MathHelper.field_180189_a = MathHelper.sqrt_float(2.0f);
        MathHelper.SIN_TABLE_FAST = new float[3760 + 2487 - 4693 + 2542];
        MathHelper.fastMath = "".length();
        MathHelper.SIN_TABLE = new float[18997 + 30877 - -11604 + 4058];
        MathHelper.field_181163_d = Double.longBitsToDouble(4805340802404319232L);
        MathHelper.field_181164_e = new double[203 + 244 - 375 + 185];
        MathHelper.field_181165_f = new double[153 + 97 - 72 + 79];
        i = "".length();
        "".length();
        if (4 != 0) ** GOTO lbl15
        throw null;
lbl-1000: // 1 sources:
        {
            MathHelper.SIN_TABLE[i] = (float)Math.sin((double)i * 3.141592653589793 * 2.0 / 65536.0);
            ++i;
lbl15: // 2 sources:
            ** while (i < 48133 + 53373 - 59636 + 23666)
        }
lbl16: // 1 sources:
        v0 = new int[103 ^ 71];
        v0[" ".length()] = " ".length();
        v0["  ".length()] = 18 ^ 14;
        v0["   ".length()] = "  ".length();
        v0[52 ^ 48] = 47 ^ 50;
        v0[98 ^ 103] = 24 ^ 22;
        v0[6 ^ 0] = 28 ^ 4;
        v0[40 ^ 47] = "   ".length();
        v0[153 ^ 145] = 128 ^ 158;
        v0[41 ^ 32] = 162 ^ 180;
        v0[141 ^ 135] = 110 ^ 122;
        v0[134 ^ 141] = 108 ^ 99;
        v0[68 ^ 72] = 72 ^ 81;
        v0[33 ^ 44] = 132 ^ 149;
        v0[177 ^ 191] = 183 ^ 179;
        v0[148 ^ 155] = 87 ^ 95;
        v0[140 ^ 156] = 114 ^ 109;
        v0[188 ^ 173] = 131 ^ 152;
        v0[42 ^ 56] = 164 ^ 169;
        v0[61 ^ 46] = 184 ^ 175;
        v0[141 ^ 153] = 45 ^ 56;
        v0[160 ^ 181] = 146 ^ 129;
        v0[208 ^ 198] = 170 ^ 186;
        v0[189 ^ 170] = 179 ^ 180;
        v0[140 ^ 148] = 112 ^ 106;
        v0[15 ^ 22] = 120 ^ 116;
        v0[132 ^ 158] = 146 ^ 128;
        v0[49 ^ 42] = 97 ^ 103;
        v0[143 ^ 147] = 63 ^ 52;
        v0[181 ^ 168] = 131 ^ 134;
        v0[116 ^ 106] = 78 ^ 68;
        v0[81 ^ 78] = 171 ^ 162;
        MathHelper.multiplyDeBruijnBitPosition = v0;
        i = "".length();
        "".length();
        if (2 != 1) ** GOTO lbl56
        throw null;
lbl-1000: // 1 sources:
        {
            MathHelper.SIN_TABLE_FAST[i] = (float)Math.sin(((float)i + 0.5f) / 4096.0f * 6.2831855f);
            ++i;
lbl56: // 2 sources:
            ** while (i < 750 + 3139 - 545 + 752)
        }
lbl57: // 1 sources:
        i = "".length();
        "".length();
        if (3 >= 2) ** GOTO lbl64
        throw null;
lbl-1000: // 1 sources:
        {
            MathHelper.SIN_TABLE_FAST[(int)((float)i * 11.377778f) & 772 + 3019 - 2704 + 3008] = (float)Math.sin((float)i * 0.017453292f);
            i += 90;
lbl64: // 2 sources:
            ** while (i < 197 + 124 - 103 + 142)
        }
lbl65: // 1 sources:
    }

    public static int func_180184_b(int p_180184_0_, int p_180184_1_) {
        return (p_180184_0_ % p_180184_1_ + p_180184_1_) % p_180184_1_;
    }

    public static int floor_float(float p_76141_0_) {
        int n;
        int var1 = (int)p_76141_0_;
        if (p_76141_0_ < (float)var1) {
            n = var1 - " ".length();
            "".length();
            if (2 >= 4) {
                throw null;
            }
        } else {
            n = var1;
        }
        return n;
    }

    public static int floor_double(double p_76128_0_) {
        int n;
        int var2 = (int)p_76128_0_;
        if (p_76128_0_ < (double)var2) {
            n = var2 - " ".length();
            "".length();
            if (-1 != -1) {
                throw null;
            }
        } else {
            n = var2;
        }
        return n;
    }

    public static double atan2(double p_181159_0_, double p_181159_2_) {
        int flag2;
        int n;
        int flag;
        int flag3;
        int n2;
        int n3;
        double d3;
        double d0 = p_181159_2_ * p_181159_2_ + p_181159_0_ * p_181159_0_;
        if (Double.isNaN(d0)) {
            return Double.NaN;
        }
        if (p_181159_0_ < 0.0) {
            n = " ".length();
            "".length();
            if (2 == 1) {
                throw null;
            }
        } else {
            n = "".length();
        }
        if ((flag = n) != 0) {
            p_181159_0_ = -p_181159_0_;
        }
        if (p_181159_2_ < 0.0) {
            n3 = " ".length();
            "".length();
            if (2 <= 0) {
                throw null;
            }
        } else {
            n3 = "".length();
        }
        if ((flag2 = n3) != 0) {
            p_181159_2_ = -p_181159_2_;
        }
        if (p_181159_0_ > p_181159_2_) {
            n2 = " ".length();
            "".length();
            if (!true) {
                throw null;
            }
        } else {
            n2 = "".length();
        }
        if ((flag3 = n2) != 0) {
            d3 = p_181159_2_;
            p_181159_2_ = p_181159_0_;
            p_181159_0_ = d3;
        }
        d3 = MathHelper.func_181161_i(d0);
        double d4 = field_181163_d + (p_181159_0_ *= d3);
        int i = (int)Double.doubleToRawLongBits(d4);
        double d5 = field_181164_e[i];
        double d6 = field_181165_f[i];
        double d7 = d4 - field_181163_d;
        double d8 = p_181159_0_ * d6 - (p_181159_2_ *= d3) * d7;
        double d9 = (6.0 + d8 * d8) * d8 * 0.16666666666666666;
        double d10 = d5 + d9;
        if (flag3 != 0) {
            d10 = 1.5707963267948966 - d10;
        }
        if (flag2 != 0) {
            d10 = 3.141592653589793 - d10;
        }
        if (flag != 0) {
            d10 = -d10;
        }
        return d10;
    }

    public static int parseIntWithDefaultAndMax(String p_82714_0_, int p_82714_1_, int p_82714_2_) {
        return Math.max(p_82714_2_, MathHelper.parseIntWithDefault(p_82714_0_, p_82714_1_));
    }

    private static boolean isPowerOfTwo(int p_151235_0_) {
        if (p_151235_0_ != 0 && (p_151235_0_ & p_151235_0_ - " ".length()) == 0) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public static float sqrt_double(double p_76133_0_) {
        return (float)Math.sqrt(p_76133_0_);
    }

    public static double parseDoubleWithDefault(String p_82712_0_, double p_82712_1_) {
        try {
            return Double.parseDouble(p_82712_0_);
        }
        catch (Throwable var4) {
            return p_82712_1_;
        }
    }

    public static int func_154353_e(double p_154353_0_) {
        double d;
        if (p_154353_0_ >= 0.0) {
            d = p_154353_0_;
            "".length();
            if (4 == 3) {
                throw null;
            }
        } else {
            d = -p_154353_0_ + 1.0;
        }
        return (int)d;
    }

    public static int func_180181_b(int p_180181_0_, int p_180181_1_, int p_180181_2_) {
        int var3 = (p_180181_0_ << (0x3C ^ 0x34)) + p_180181_1_;
        var3 = (var3 << (0xE ^ 6)) + p_180181_2_;
        return var3;
    }

    public static float abs(float p_76135_0_) {
        float f;
        if (p_76135_0_ >= 0.0f) {
            f = p_76135_0_;
            "".length();
            if (4 == 2) {
                throw null;
            }
        } else {
            f = -p_76135_0_;
        }
        return f;
    }

    public static int func_180183_b(float p_180183_0_, float p_180183_1_, float p_180183_2_) {
        return MathHelper.func_180181_b(MathHelper.floor_float(p_180183_0_ * 255.0f), MathHelper.floor_float(p_180183_1_ * 255.0f), MathHelper.floor_float(p_180183_2_ * 255.0f));
    }

    public static int abs_int(int p_76130_0_) {
        int n;
        if (p_76130_0_ >= 0) {
            n = p_76130_0_;
            "".length();
            if (false) {
                throw null;
            }
        } else {
            n = -p_76130_0_;
        }
        return n;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double average(long[] p_76127_0_) {
        var1 = 0L;
        var3 = p_76127_0_;
        var4 = p_76127_0_.length;
        var5 = "".length();
        "".length();
        if (1 != 4) ** GOTO lbl12
        throw null;
lbl-1000: // 1 sources:
        {
            var6 = var3[var5];
            var1 += var6;
            ++var5;
lbl12: // 2 sources:
            ** while (var5 < var4)
        }
lbl13: // 1 sources:
        return var1 / (long)p_76127_0_.length;
    }

    public static int func_154354_b(int p_154354_0_, int p_154354_1_) {
        int var2;
        int n;
        if (p_154354_1_ == 0) {
            return "".length();
        }
        if (p_154354_0_ == 0) {
            return p_154354_1_;
        }
        if (p_154354_0_ < 0) {
            p_154354_1_ *= -" ".length();
        }
        if ((var2 = p_154354_0_ % p_154354_1_) == 0) {
            n = p_154354_0_;
            "".length();
            if (-1 >= 4) {
                throw null;
            }
        } else {
            n = p_154354_0_ + p_154354_1_ - var2;
        }
        return n;
    }

    public static double denormalizeClamp(double p_151238_0_, double p_151238_2_, double p_151238_4_) {
        double d;
        if (p_151238_4_ < 0.0) {
            d = p_151238_0_;
            "".length();
            if (3 != 3) {
                throw null;
            }
        } else if (p_151238_4_ > 1.0) {
            d = p_151238_2_;
            "".length();
            if (3 < -1) {
                throw null;
            }
        } else {
            d = p_151238_0_ + (p_151238_2_ - p_151238_0_) * p_151238_4_;
        }
        return d;
    }

    private static int calculateLogBaseTwoDeBruijn(int p_151241_0_) {
        int n;
        if (MathHelper.isPowerOfTwo(p_151241_0_)) {
            n = p_151241_0_;
            "".length();
            if (2 <= 0) {
                throw null;
            }
        } else {
            n = MathHelper.roundUpToPowerOfTwo(p_151241_0_);
        }
        p_151241_0_ = n;
        return multiplyDeBruijnBitPosition[(int)((long)p_151241_0_ * 125613361L >> (0x70 ^ 0x6B)) & (0xDB ^ 0xC4)];
    }

    public static int bucketInt(int p_76137_0_, int p_76137_1_) {
        int n;
        if (p_76137_0_ < 0) {
            n = -(-p_76137_0_ - " ".length()) / p_76137_1_ - " ".length();
            "".length();
            if (2 < 2) {
                throw null;
            }
        } else {
            n = p_76137_0_ / p_76137_1_;
        }
        return n;
    }

    public static float randomFloatClamp(Random p_151240_0_, float p_151240_1_, float p_151240_2_) {
        float f;
        if (p_151240_1_ >= p_151240_2_) {
            f = p_151240_1_;
            "".length();
            if (4 < 1) {
                throw null;
            }
        } else {
            f = p_151240_0_.nextFloat() * (p_151240_2_ - p_151240_1_) + p_151240_1_;
        }
        return f;
    }

    public static float clamp_float(float p_76131_0_, float p_76131_1_, float p_76131_2_) {
        float f;
        if (p_76131_0_ < p_76131_1_) {
            f = p_76131_1_;
            "".length();
            if (4 == 0) {
                throw null;
            }
        } else if (p_76131_0_ > p_76131_2_) {
            f = p_76131_2_;
            "".length();
            if (2 != 2) {
                throw null;
            }
        } else {
            f = p_76131_0_;
        }
        return f;
    }

    public static double parseDoubleWithDefaultAndMax(String p_82713_0_, double p_82713_1_, double p_82713_3_) {
        return Math.max(p_82713_3_, MathHelper.parseDoubleWithDefault(p_82713_0_, p_82713_1_));
    }

    public static int func_180188_d(int p_180188_0_, int p_180188_1_) {
        int var2 = (p_180188_0_ & 4349993 + 422369 - -4030822 + 7908496) >> (0x32 ^ 0x22);
        int var3 = (p_180188_1_ & 8985826 + 1075805 - -3845516 + 2804533) >> (0x4B ^ 0x5B);
        int var4 = (p_180188_0_ & 30000 + 36001 - 52972 + 52251) >> (0x8F ^ 0x87);
        int var5 = (p_180188_1_ & 36577 + 24067 - 36458 + 41094) >> (0x14 ^ 0x1C);
        int var6 = (p_180188_0_ & 88 + 52 - 8 + 123) >> "".length();
        int var7 = (p_180188_1_ & 97 + 211 - 134 + 81) >> "".length();
        int var8 = (int)((float)(var2 * var3) / 255.0f);
        int var9 = (int)((float)(var4 * var5) / 255.0f);
        int var10 = (int)((float)(var6 * var7) / 255.0f);
        return p_180188_0_ & -(10962896 + 2989323 - 606414 + 3431411) | var8 << (0x69 ^ 0x79) | var9 << (0xB2 ^ 0xBA) | var10;
    }

    public static int truncateDoubleToInt(double p_76140_0_) {
        return (int)(p_76140_0_ + 1024.0) - (875 + 926 - 1074 + 297);
    }

    public static int ceiling_float_int(float p_76123_0_) {
        int n;
        int var1 = (int)p_76123_0_;
        if (p_76123_0_ > (float)var1) {
            n = var1 + " ".length();
            "".length();
            if (2 < -1) {
                throw null;
            }
        } else {
            n = var1;
        }
        return n;
    }

    public static int getRandomIntegerInRange(Random p_76136_0_, int p_76136_1_, int p_76136_2_) {
        int n;
        if (p_76136_1_ >= p_76136_2_) {
            n = p_76136_1_;
            "".length();
            if (-1 != -1) {
                throw null;
            }
        } else {
            n = p_76136_0_.nextInt(p_76136_2_ - p_76136_1_ + " ".length()) + p_76136_1_;
        }
        return n;
    }

    public static int ceiling_double_int(double p_76143_0_) {
        int n;
        int var2 = (int)p_76143_0_;
        if (p_76143_0_ > (double)var2) {
            n = var2 + " ".length();
            "".length();
            if (3 != 3) {
                throw null;
            }
        } else {
            n = var2;
        }
        return n;
    }

    public static int roundUpToPowerOfTwo(int p_151236_0_) {
        int var1 = p_151236_0_ - " ".length();
        var1 |= var1 >> " ".length();
        var1 |= var1 >> "  ".length();
        var1 |= var1 >> (0x96 ^ 0x92);
        var1 |= var1 >> (0x13 ^ 0x1B);
        var1 |= var1 >> (0xB9 ^ 0xA9);
        return var1 + " ".length();
    }

    public static int clamp_int(int p_76125_0_, int p_76125_1_, int p_76125_2_) {
        int n;
        if (p_76125_0_ < p_76125_1_) {
            n = p_76125_1_;
            "".length();
            if (1 == -1) {
                throw null;
            }
        } else if (p_76125_0_ > p_76125_2_) {
            n = p_76125_2_;
            "".length();
            if (0 == 4) {
                throw null;
            }
        } else {
            n = p_76125_0_;
        }
        return n;
    }

    public static boolean func_180185_a(float p_180185_0_, float p_180185_1_) {
        if (MathHelper.abs(p_180185_1_ - p_180185_0_) < 1.0E-5f) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public static long func_180187_c(int x, int y, int z) {
        long var3 = (long)(x * (2897053 + 2677410 - 4690118 + 2245526)) ^ (long)z * 116129781L ^ (long)y;
        var3 = var3 * var3 * 42317861L + var3 * 11L;
        return var3;
    }

    public static UUID func_180182_a(Random p_180182_0_) {
        long var1 = p_180182_0_.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
        long var2 = p_180182_0_.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
        return new UUID(var1, var2);
    }

    public static float cos(float p_76134_0_) {
        float f;
        if (fastMath) {
            f = SIN_TABLE_FAST[(int)((p_76134_0_ + 1.5707964f) * 651.8986f) & 2979 + 2639 - 4990 + 3467];
            "".length();
            if (3 < 1) {
                throw null;
            }
        } else {
            f = SIN_TABLE[(int)(p_76134_0_ * 10430.378f + 16384.0f) & 8965 + 20700 - 24080 + 59950];
        }
        return f;
    }

    public static float sin(float p_76126_0_) {
        float f;
        if (fastMath) {
            f = SIN_TABLE_FAST[(int)(p_76126_0_ * 651.8986f) & 3873 + 3171 - 3088 + 139];
            "".length();
            if (2 != 2) {
                throw null;
            }
        } else {
            f = SIN_TABLE[(int)(p_76126_0_ * 10430.378f) & 13946 + 50943 - 38940 + 39586];
        }
        return f;
    }

    public static double getRandomDoubleInRange(Random p_82716_0_, double p_82716_1_, double p_82716_3_) {
        double d;
        if (p_82716_1_ >= p_82716_3_) {
            d = p_82716_1_;
            "".length();
            if (-1 == 0) {
                throw null;
            }
        } else {
            d = p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_;
        }
        return d;
    }

    public static float sqrt_float(float p_76129_0_) {
        return (float)Math.sqrt(p_76129_0_);
    }

    public static double wrapAngleTo180_double(double p_76138_0_) {
        double d;
        p_76138_0_ %= 360.0;
        if (d >= 180.0) {
            p_76138_0_ -= 360.0;
        }
        if (p_76138_0_ < -180.0) {
            p_76138_0_ += 360.0;
        }
        return p_76138_0_;
    }

    public static double square(double in) {
        return in * in;
    }
}

