/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.reach;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.ReachCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.ReachData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;

public class ReachB
extends ReachCheck {
    private static final /* synthetic */ ThreadLocal<DecimalFormat> REACH_FORMAT;
    private static final /* synthetic */ String[] I;

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x12 ^ 0x1A), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void I() {
        I = new String[0xC2 ^ 0xC4];
        ReachB.I["".length()] = ReachB.I("FkBhmsn4vUQ=", "STcLJ");
        ReachB.I[" ".length()] = ReachB.l("9T1K5r7eD4U=", "hPuAh");
        ReachB.I["  ".length()] = ReachB.I("239FeB8X5iQ=", "AmPjA");
        ReachB.I["   ".length()] = ReachB.l("YXhkwclQHZw=", "tyVGr");
        ReachB.I[123 ^ 127] = ReachB.l("winAjPNONX8=", "zjBnD");
        ReachB.I[139 ^ 142] = ReachB.I("OkCYThNSbKs=", "VGpwQ");
    }

    public ReachB() {
        super(Check.CheckType.REACHB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.setMaxViolation(0x4D ^ 0x42);
        this.violations = -1.5;
    }

    @Override
    public void handle(Player player, PlayerData playerData, ReachData reachData, long timestamp) {
        double reach = reachData.getReach();
        double extra = reachData.getExtra();
        double vertical = reachData.getVertical();
        double movement = reachData.getMovement();
        double horizontal = reachData.getHorizontal();
        DecimalFormat format = REACH_FORMAT.get();
        double offset = Math.abs((double)playerData.getPing() * 1.0E-4 + 0.3);
        if (reach > 3.0 + offset) {
            double d;
            if (movement > 0.0) {
                d = 2.5;
                "".length();
                if (2 != 2) {
                    throw null;
                }
            } else {
                d = 2.25;
            }
            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(format.format(reach)) + I["  ".length()] + format.format(horizontal) + I["   ".length()] + format.format(vertical) + I[0x10 ^ 0x14] + format.format(extra) + I[0x3E ^ 0x3B] + format.format(movement), reach - d);
            "".length();
            if (-1 >= 4) {
                throw null;
            }
        } else {
            this.violations -= Math.min(this.violations + 1.5, 0.01);
        }
    }

    static {
        ReachB.I();
        REACH_FORMAT = new ThreadLocal<DecimalFormat>(){
            private static final /* synthetic */ String[] I;

            private static String I(String obj, String key) {
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

            static {
                1.I();
            }

            private static void I() {
                I = new String[" ".length()];
                1.I["".length()] = 1.I("bBETgov87SM=", "WppaP");
            }

            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat(I["".length()]);
            }
        };
    }

    private static String I(String obj, String key) {
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

}

