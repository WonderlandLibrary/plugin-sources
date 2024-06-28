/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.aimassist;

import com.unknownmyname.check.AimCheck;
import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;

public class AimB
extends AimCheck {
    private /* synthetic */ boolean pitchLast;
    private /* synthetic */ float avgPitch;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ float avgYaw;

    static {
        AimB.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        int n;
        if (to.getPitch() > from.getPitch()) {
            n = " ".length();
            "".length();
            if (2 == 1) {
                throw null;
            }
        } else {
            n = "".length();
        }
        int pitchMove = n;
        int bl = n;
        if (playerData.getLastAttackTicks() <= (0x6D ^ 0x5F)) {
            float yawChange = Math.abs(to.getYaw() - from.getYaw());
            float pitchChange = Math.abs(to.getPitch() - from.getPitch());
            this.avgYaw = (yawChange + this.avgYaw * 2.0f) / 3.0f;
            if ((double)pitchChange > 0.001 && (double)pitchChange < 0.01 && (double)yawChange > 0.01 && pitchMove != this.pitchLast) {
                AlertsManager.getInstance().handleViolation(playerData, this, yawChange + I["  ".length()] + pitchChange + I["   ".length()] + this.avgYaw + I[0x10 ^ 0x14] + this.avgPitch);
                "".length();
                if (4 != 4) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 5.0, 0.05);
            }
            this.avgPitch = (pitchChange + this.avgPitch * 2.0f) / 3.0f;
        }
        this.pitchLast = pitchMove;
    }

    private static void I() {
        I = new String[0 ^ 5];
        AimB.I["".length()] = AimB.I("\u0010", "RRwFt");
        AimB.I[" ".length()] = AimB.l("9s6mkJLUH48C2zmOvoxnlQ==", "lTiNm");
        AimB.I["  ".length()] = AimB.lI("9NS3JZsVtiQ=", "LtWAq");
        AimB.I["   ".length()] = AimB.l("m0wDmH0zQFo=", "KqDrN");
        AimB.I[109 ^ 105] = AimB.I("v", "VzmfV");
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

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x72 ^ 0x7A), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
            if (2 != 3) continue;
            throw null;
        }
        return sb.toString();
    }

    public AimB() {
        super(Check.CheckType.AIM_ASSISTB, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.avgYaw = 0.0f;
        this.avgPitch = 0.0f;
        this.violations = -5.0;
    }
}

