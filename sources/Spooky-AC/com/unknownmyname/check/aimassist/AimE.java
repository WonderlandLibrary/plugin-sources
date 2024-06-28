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
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;

public class AimE
extends AimCheck {
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Integer lastNum;

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
        AimE.I();
    }

    private static void I() {
        I = new String[0xBE ^ 0xBA];
        AimE.I["".length()] = AimE.I("DLO6fLPB+x4=", "jwGYi");
        AimE.I[" ".length()] = AimE.l("PNUYDQciQsO1xDu/3NzmZg==", "ACaqZ");
        AimE.I["  ".length()] = AimE.I("oRg9eevzCjo=", "KXbMV");
        AimE.I["   ".length()] = AimE.l("KQ2skkNDMLY=", "DloFt");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xAC ^ 0xA4), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AimE() {
        super(Check.CheckType.AIM_ASSISTE, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastNum = null;
        this.threshold = "".length();
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (playerData.getLastAttackTicks() <= 81 + 69 - -128 + 22) {
            int yawChange = MathUtil.toInt(Math.abs(to.getYaw() - from.getYaw()));
            int pitchChange = MathUtil.toInt(Math.abs(to.getPitch() - from.getPitch()));
            if (pitchChange != 0 && yawChange != 0) {
                int diff = Math.abs(yawChange - pitchChange);
                if (this.lastNum != null) {
                    if (diff == this.lastNum) {
                        int n = this.threshold;
                        this.threshold = n + " ".length();
                        if (n > "  ".length()) {
                            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(diff) + I["  ".length()] + yawChange + I["   ".length()] + pitchChange);
                            this.threshold = "".length();
                            "".length();
                            if (4 < -1) {
                                throw null;
                            }
                        }
                    } else {
                        this.threshold = "".length();
                    }
                    this.violations -= Math.min(this.violations + 1.0, 0.001);
                }
                this.lastNum = diff;
            }
        }
    }
}

