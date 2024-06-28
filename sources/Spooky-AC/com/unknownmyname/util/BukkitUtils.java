/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.unknownmyname.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BukkitUtils {
    private static final /* synthetic */ String[] I;

    public static int getPotionLevel(Player player, PotionEffectType type) {
        PotionEffect potionEffect;
        ArrayList potionEffectList = new ArrayList(player.getActivePotionEffects());
        Iterator var3 = potionEffectList.iterator();
        do {
            if (var3.hasNext()) {
                "".length();
                if (4 == 4) continue;
                throw null;
            }
            return "".length();
        } while ((potionEffect = (PotionEffect)var3.next()).getType().getId() != type.getId());
        return potionEffect.getAmplifier() + " ".length();
    }

    static {
        BukkitUtils.I();
    }

    public static float wrapAngleTo180_float(float value) {
        float f;
        value %= 360.0f;
        if (f >= 180.0f) {
            value -= 360.0f;
        }
        if (value < -180.0f) {
            value += 360.0f;
        }
        return value;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void log(Player player, String log) {
        String fileName = I[" ".length()] + player.getName().toLowerCase() + I["  ".length()];
        File file = new File(fileName.replace(fileName.split(I["   ".length()])[fileName.split(I[0x98 ^ 0x9C]).length - " ".length()], I[0xA2 ^ 0xA7]));
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            Throwable t = null;
            try {
                try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(I[0xA ^ 0xC] + player.getName().toLowerCase() + I[0x58 ^ 0x5F], " ".length() != 0)));){
                    pw.println(log);
                    "".length();
                    if (-1 < -1) {
                        throw null;
                    }
                }
                if (pw == null) {
                }
                pw.close();
                "".length();
                if (4 < 2) {
                    throw null;
                }
            }
            finally {
                if (t == null) return;
                Throwable t2 = null;
                if (t == t2) return;
                t.addSuppressed(t2);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(I[5 ^ 0xD] + player.getName() + I[0x79 ^ 0x70]);
        }
    }

    public static double getDistance(double p1, double p2, double p3, double p4) {
        double delta1 = p3 - p1;
        double delta2 = p4 - p2;
        return Math.sqrt(delta1 * delta1 + delta2 * delta2);
    }

    public static double getDirection(Location from, Location to) {
        if (from == null || to == null) {
            return 0.0;
        }
        double difX = to.getX() - from.getX();
        double difZ = to.getZ() - from.getZ();
        return BukkitUtils.wrapAngleTo180_float((float)(Math.atan2(difZ, difX) * 180.0 / 3.141592653589793) - 90.0f);
    }

    private static String I(String obj, String key) {
        StringBuilder sb = new StringBuilder();
        char[] keyChars = key.toCharArray();
        int i = "".length();
        char[] arrc = obj.toCharArray();
        int n = arrc.length;
        for (int j = "".length(); j < n; ++j) {
            char c = arrc[j];
            sb.append((char)(c ^ keyChars[i % keyChars.length]));
            ++i;
            "".length();
            if (0 < 4) continue;
            throw null;
        }
        return sb.toString();
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x42 ^ 0x4A), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static float fixRotation(float p_70663_1_, float p_70663_2_, float p_70663_3_) {
        float var4 = BukkitUtils.wrapAngleTo180_float(p_70663_2_ - p_70663_1_);
        if (var4 > p_70663_3_) {
            var4 = p_70663_3_;
        }
        if (var4 < -p_70663_3_) {
            var4 = -p_70663_3_;
        }
        return p_70663_1_ + var4;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getFromFile(String fileName) {
        try {
            StringBuilder getHotKey = new StringBuilder();
            FileReader getFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(getFile);
            "".length();
            if (!true) {
                throw null;
            }
            do {
                String line;
                if ((line = bufferReader.readLine()) == null) {
                    bufferReader.close();
                    return getHotKey.toString();
                }
                getHotKey.append(line);
            } while (true);
        }
        catch (Exception e) {
            return I["".length()];
        }
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), "Blowfish");
            Cipher des = Cipher.getInstance("Blowfish");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void I() {
        I = new String[0x61 ^ 0x6B];
        BukkitUtils.I["".length()] = BukkitUtils.I("ze", "WTQrV");
        BukkitUtils.I[" ".length()] = BukkitUtils.l("ipmS6MIC1yVffrBEnRoC6+HXfgmzgoIG", "fGvXR");
        BukkitUtils.I["  ".length()] = BukkitUtils.lI("xMZdPNnTfxE=", "nbFOy");
        BukkitUtils.I["   ".length()] = BukkitUtils.I("k", "DEdHF");
        BukkitUtils.I[108 ^ 104] = BukkitUtils.lI("RQAfbcCMo0Y=", "MBPrM");
        BukkitUtils.I[119 ^ 114] = BukkitUtils.lI("qogeAjKqRnQ=", "VGjyG");
        BukkitUtils.I[19 ^ 21] = BukkitUtils.l("rkDt8v3kG9dvVpeDeVLN31bvndEHZ+j4", "RqDaH");
        BukkitUtils.I[34 ^ 37] = BukkitUtils.lI("1ZyOtRz1aQE=", "nNakr");
        BukkitUtils.I[124 ^ 116] = BukkitUtils.l("lDLyooYq/nb66Bc6jN9QKUdVp4R1tkB6C8Z9T/SlM69H9baSurEZk4sWbEo1PwZh", "toIuW");
        BukkitUtils.I[93 ^ 84] = BukkitUtils.l("CWdoL0NXRKE=", "KvjLv");
    }
}

