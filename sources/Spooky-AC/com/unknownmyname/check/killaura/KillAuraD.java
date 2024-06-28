/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.killaura;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.ReachCheck;
import com.unknownmyname.data.DistanceData;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.ReachData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;

public class KillAuraD
extends ReachCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int threshhold;

    private static void I() {
        I = new String[0 ^ 4];
        KillAuraD.I["".length()] = KillAuraD.I("#", "gOXZz");
        KillAuraD.I[" ".length()] = KillAuraD.l("7FSPt0mCjYykAl5w7TRa7w==", "ePYVF");
        KillAuraD.I["  ".length()] = KillAuraD.I("D", "dWeSd");
        KillAuraD.I["   ".length()] = KillAuraD.l("We5HXyug2XA=", "EXhOd");
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

    @Override
    public void handle(Player player, PlayerData playerData, ReachData reachData, long timestamp) {
        DistanceData distanceData = reachData.getDistanceData();
        double hitboxX = distanceData.getX() / distanceData.getDist() * reachData.getExtra();
        double hitboxZ = distanceData.getZ() / distanceData.getDist() * reachData.getExtra();
        if (Math.max(Math.abs(hitboxX), Math.abs(hitboxZ)) > 0.55) {
            int n = this.threshhold;
            this.threshhold = n + " ".length();
            if (n > "  ".length() + playerData.getPingTicks() / "   ".length()) {
                AlertsManager.getInstance().handleViolation(playerData, this, hitboxX + I["  ".length()] + hitboxZ + I["   ".length()] + reachData.getExtra());
                "".length();
                if (0 < -1) {
                    throw null;
                }
            }
        } else {
            this.violations -= Math.min(this.violations, 0.1);
            this.threshhold = "".length();
        }
    }

    public KillAuraD() {
        super(Check.CheckType.KILL_AURAD, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.threshhold = "".length();
        this.setMaxViolation(0xB3 ^ 0xB9);
        this.violations = -3.0;
    }

    static {
        KillAuraD.I();
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
            if (true) continue;
            throw null;
        }
        return sb.toString();
    }
}

