/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.speed;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;

public class SpeedD
extends MovementCheck {
    private /* synthetic */ int combo;
    private static final /* synthetic */ String[] I;

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
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        double[] arrd = new double["  ".length()];
        arrd["".length()] = to.getX() - from.getX();
        arrd[" ".length()] = to.getZ() - from.getZ();
        double distance = MathUtil.hypot(arrd);
        if (from.getOnGround() != to.getOnGround() && distance > 0.2865) {
            int n = this.combo;
            this.combo = n + " ".length();
            if (n > (0x4C ^ 0x49)) {
                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(String.valueOf(distance) + I["  ".length()] + this.combo));
                "".length();
                if (1 < -1) {
                    throw null;
                }
            }
        } else {
            this.combo = "".length();
        }
    }

    public SpeedD() {
        super(Check.CheckType.SPEEDD, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.setMaxViolation(0x81 ^ 0xB6);
        this.combo = "".length();
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
            if (3 == 3) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String["   ".length()];
        SpeedD.I["".length()] = SpeedD.I("0", "tuSQJ");
        SpeedD.I[" ".length()] = SpeedD.l("AOZKjBJ/e8g=", "eiOVh");
        SpeedD.I["  ".length()] = SpeedD.I("u", "UvYhM");
    }

    static {
        SpeedD.I();
    }
}

