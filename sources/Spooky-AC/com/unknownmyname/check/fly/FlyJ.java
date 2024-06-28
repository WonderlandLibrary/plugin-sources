/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.fly;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
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

public abstract class FlyJ
extends MovementCheck {
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (player.isFlying()) {
            return;
        }
        if (from.getOnGround().booleanValue() && from.getY() - to.getY() > 0.079 && !player.getAllowFlight() && playerData.getTeleportTicks() > playerData.getPingTicks() * "  ".length() && !playerData.hasLag() && Math.abs(from.getY() % 0.5 - 0.015555072702202466) > 1.0E-12) {
            double yDiff = from.getY() - to.getY();
            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(yDiff) + I["  ".length()] + to.getOnGround());
            "".length();
            if (true <= false) {
                throw null;
            }
        } else {
            this.violations -= Math.min(this.violations + 1.0, 0.005);
        }
    }

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
            if (0 < 3) continue;
            throw null;
        }
        return sb.toString();
    }

    public FlyJ() {
        super(Check.CheckType.FLYJ, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.violations = -1.0;
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

    private static void I() {
        I = new String["   ".length()];
        FlyJ.I["".length()] = FlyJ.I("E0mtQbrOQQ8=", "cvcqp");
        FlyJ.I[" ".length()] = FlyJ.l("\u0005*=", "CFDxj");
        FlyJ.I["  ".length()] = FlyJ.I("WTYKvShpxWs=", "UqdjY");
    }

    static {
        FlyJ.I();
    }
}

