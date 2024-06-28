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

public class AimD
extends AimCheck {
    private static final /* synthetic */ String[] I;

    private static void I() {
        I = new String["   ".length()];
        AimD.I["".length()] = AimD.I("MS+BHy11thE=", "gJljP");
        AimD.I[" ".length()] = AimD.l("KkmqhBKUlC3Pty2sQcrbsw==", "ZGaLd");
        AimD.I["  ".length()] = AimD.lI("C", "cfBEt");
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

    static {
        AimD.I();
    }

    public AimD() {
        super(Check.CheckType.AIM_ASSISTD, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.violations = -4.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (playerData.getLastAttackTicks() <= (0xC5 ^ 0xA1)) {
            float yawChange = Math.abs(to.getYaw() - from.getYaw());
            float pitchChange = Math.abs(to.getPitch() - from.getPitch());
            if (pitchChange > 0.0f && (double)pitchChange < 1.0E-5 && yawChange > 5.0f) {
                AlertsManager.getInstance().handleViolation(playerData, this, yawChange + I["  ".length()] + pitchChange);
                "".length();
                if (3 == 0) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 1.0, 0.005);
            }
        }
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x68 ^ 0x60), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String lI(String obj, String key) {
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
            if (4 == 4) continue;
            throw null;
        }
        return sb.toString();
    }
}

