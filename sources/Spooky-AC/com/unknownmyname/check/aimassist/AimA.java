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

public class AimA
extends AimCheck {
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        float f;
        float yawChange = Math.abs(from.getYaw() - to.getYaw());
        if (yawChange >= 1.0f && yawChange % 0.1f == 0.0f) {
            if (yawChange % 1.0f == 0.0f) {
                this.violations += 1.0;
            }
            if (yawChange % 10.0f == 0.0f) {
                this.violations += 1.0;
            }
            if (yawChange % 30.0f == 0.0f) {
                this.violations += 1.0;
            }
            AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()] + yawChange, 1.0);
        }
        float pitchChange = Math.abs(from.getPitch() - to.getPitch());
        if (f >= 1.0f && pitchChange % 0.1f == 0.0f) {
            if (pitchChange % 1.0f == 0.0f) {
                this.violations += 1.0;
            }
            if (pitchChange % 10.0f == 0.0f) {
                this.violations += 1.0;
            }
            if (pitchChange % 30.0f == 0.0f) {
                this.violations += 1.0;
            }
            AlertsManager.getInstance().handleViolation(playerData, this, I["   ".length()] + pitchChange, 1.0);
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
            if (1 != -1) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        AimA.I();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x8E ^ 0x86), "DES");
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

    public AimA() {
        super(Check.CheckType.AIM_ASSISTA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.setMaxViolation(0x6D ^ 0x68);
    }

    private static void I() {
        I = new String[0xAB ^ 0xAF];
        AimA.I["".length()] = AimA.I("jUpLcIzkO+4=", "jgfOR");
        AimA.I[" ".length()] = AimA.l("gDjT1QF2piyQ5NAnFG7f0w==", "cegCs");
        AimA.I["  ".length()] = AimA.lI("\f+.y", "UJYYE");
        AimA.I["   ".length()] = AimA.I("+sX4MjdIPyg=", "HxxbI");
    }
}

