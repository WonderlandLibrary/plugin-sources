/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.misc;

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

public class MiscA
extends MovementCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int combo;

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
            if (1 < 3) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        MiscA.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        int n;
        double yDiff = to.getY() - from.getY();
        if (yDiff > 0.419 && yDiff < 0.42) {
            n = " ".length();
            "".length();
            if (2 <= -1) {
                throw null;
            }
        } else {
            n = "".length();
        }
        int jump = n;
        int bl = n;
        if (from.getOnGround().booleanValue()) {
            if (playerData.getLastAttackTicks() <= " ".length()) {
                if (!to.getOnGround().booleanValue() && yDiff > 0.419 && yDiff < 0.42) {
                    int n2 = this.combo;
                    this.combo = n2 + " ".length();
                    if (n2 > (0x21 ^ 0x25)) {
                        AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                        this.combo = "".length();
                        "".length();
                        if (-1 == 1) {
                            throw null;
                        }
                    }
                } else {
                    this.combo = "".length();
                    this.violations -= Math.min(this.violations + 1.0, 0.5);
                    "".length();
                    if (-1 == 3) {
                        throw null;
                    }
                }
            } else if (jump != 0) {
                this.combo = (int)((double)this.combo - Math.min(this.violations, 1.0));
            }
        }
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x97 ^ 0x9F), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MiscA() {
        super(Check.CheckType.MISCA, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.combo = "".length();
        this.violations = -1.0;
    }

    private static void I() {
        I = new String["   ".length()];
        MiscA.I["".length()] = MiscA.I(".", "oQhOp");
        MiscA.I[" ".length()] = MiscA.l("494pKVqx+WA+iDgH7f8X7A==", "rvoMQ");
        MiscA.I["  ".length()] = MiscA.lI("WoQ4eFNHfVI=", "sUtOr");
    }
}

