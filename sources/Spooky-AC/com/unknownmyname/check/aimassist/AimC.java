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
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;

public class AimC
extends AimCheck {
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (playerData.getLastAttackTicks() <= (0xA1 ^ 0xC5)) {
            float yawChange = Math.abs(to.getYaw() - from.getYaw());
            float pitchChange = Math.abs(to.getPitch() - from.getPitch());
            if (yawChange > 0.0f && (double)yawChange < 0.01 && (double)pitchChange > 0.2) {
                AlertsManager.getInstance().handleViolation(playerData, this, yawChange + I["  ".length()] + pitchChange);
                "".length();
                if (3 == 1) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 5.0, 0.05);
            }
        }
    }

    public AimC() {
        super(Check.CheckType.AIM_ASSISTC, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.violations = -5.0;
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

    static {
        AimC.I();
    }

    private static void I() {
        I = new String["   ".length()];
        AimC.I["".length()] = AimC.I("YmSDD1KsutQ=", "wdpPl");
        AimC.I[" ".length()] = AimC.I("DtP4KfR0gfAlLrsCua6kaw==", "BuVcE");
        AimC.I["  ".length()] = AimC.I("VhV7QX6PzuQ=", "hCFsJ");
    }
}

