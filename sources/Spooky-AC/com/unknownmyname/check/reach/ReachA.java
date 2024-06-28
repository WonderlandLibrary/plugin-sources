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

public class ReachA
extends ReachCheck {
    private static final /* synthetic */ ThreadLocal<DecimalFormat> REACH_FORMAT;
    private static final /* synthetic */ String[] I;

    private static String l(String obj, String key) {
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
            if (true) continue;
            throw null;
        }
        return sb.toString();
    }

    private static String lI(String obj, String key) {
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

    @Override
    public void handle(Player player, PlayerData playerData, ReachData reachData, long timestamp) {
        double reach = reachData.getReach();
        double extra = reachData.getExtra();
        double vertical = reachData.getVertical();
        double movement = reachData.getMovement();
        double horizontal = reachData.getHorizontal();
        DecimalFormat format = REACH_FORMAT.get();
        if (reach > 3.0) {
            double d;
            if (movement > 0.0) {
                d = 2.5;
                "".length();
                if (-1 >= 3) {
                    throw null;
                }
            } else {
                d = 2.25;
            }
            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(format.format(reach)) + I["  ".length()] + format.format(horizontal) + I["   ".length()] + format.format(vertical) + I[0x3E ^ 0x3A] + format.format(extra) + I[0x3B ^ 0x3E] + format.format(movement), reach - d);
            "".length();
            if (3 <= 2) {
                throw null;
            }
        } else {
            this.violations -= Math.min(this.violations + 1.2, 0.01);
        }
    }

    public ReachA() {
        super(Check.CheckType.REACHA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.setMaxViolation(0x14 ^ 0x19);
        this.violations = -1.2;
    }

    private static void I() {
        I = new String[7 ^ 1];
        ReachA.I["".length()] = ReachA.I("0CecnL7YZnA=", "oTiZk");
        ReachA.I[" ".length()] = ReachA.l("\u001d\f\u0006\u000b8", "OighP");
        ReachA.I["  ".length()] = ReachA.lI("Sq5nJd69gX8=", "JOHiZ");
        ReachA.I["   ".length()] = ReachA.lI("r34DvcWqmis=", "QdtIK");
        ReachA.I[164 ^ 160] = ReachA.lI("eyMi3DL0mQI=", "GXusS");
        ReachA.I[101 ^ 96] = ReachA.lI("LZ+giAmScS0=", "bSGtT");
    }

    static {
        ReachA.I();
        REACH_FORMAT = new ThreadLocal<DecimalFormat>(){
            private static final /* synthetic */ String[] I;

            private static void I() {
                I = new String[" ".length()];
                1.I["".length()] = 1.I("jCIsd", "ImjPG");
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
                    if (0 < 2) continue;
                    throw null;
                }
                return sb.toString();
            }

            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat(I["".length()]);
            }

            static {
                1.I();
            }
        };
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x3C ^ 0x34), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

