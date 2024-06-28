/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.killaura;

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

public class KillAuraE
extends AimCheck {
    private /* synthetic */ int number;
    private static final /* synthetic */ String[] I;

    private static void I() {
        I = new String["  ".length()];
        KillAuraE.I["".length()] = KillAuraE.I("gl4ya8qm5ws=", "Wzbet");
        KillAuraE.I[" ".length()] = KillAuraE.I("FwXjpIVf8qcS33fE3gJSww==", "jFDeh");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x43 ^ 0x4B), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (playerData.getLastAttacked() != null && playerData.getLastAttackTicks() <= (0x89 ^ 0x8F) && playerData.getTotalTicks() > (0x17 ^ 0x73)) {
            PlayerLocation playerLocation = playerData.getLocation();
            PlayerLocation targetLocation = playerData.getLastAttacked().getLocation(playerData.getPingTicks());
            if (targetLocation != null) {
                double d;
                double angle = MathUtil.getDistanceBetweenAngles(playerLocation.getYaw(), MathUtil.getRotationFromPosition(playerLocation, targetLocation)["".length()]);
                angle = Math.min(180.0 - angle, angle);
                if (d < 15.0) {
                    this.number += " ".length();
                    "".length();
                    if (-1 != -1) {
                        throw null;
                    }
                } else {
                    if (angle > 30.0 && Math.abs(from.getYaw() - to.getYaw()) > 30.0f && this.number > (0xB0 ^ 0xB6) && playerData.getLastAttackTicks() <= "   ".length()) {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(angle), 1.0, " ".length() != 0);
                        "".length();
                        if (-1 >= 2) {
                            throw null;
                        }
                    } else {
                        this.violations -= Math.min(this.violations + 1.0, 0.05);
                    }
                    this.number = "".length();
                }
            }
        }
    }

    static {
        KillAuraE.I();
    }

    public KillAuraE() {
        super(Check.CheckType.KILL_AURAE, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.number = "".length();
        this.violations = -1.0;
    }
}

