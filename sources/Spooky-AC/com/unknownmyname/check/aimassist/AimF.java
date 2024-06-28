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

public class AimF
extends AimCheck {
    private /* synthetic */ float lastPitchRate;
    private /* synthetic */ float lastYawRate;
    private static final /* synthetic */ String[] I;

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
            if (true >= true) continue;
            throw null;
        }
        return sb.toString();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x4E ^ 0x46), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        AimF.I();
    }

    public AimF() {
        super(Check.CheckType.AIM_ASSISTF, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.violations = -1.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        float diffPitch = (float)MathUtil.getDistanceBetweenAngles(to.getPitch(), from.getPitch());
        float diffYaw = (float)MathUtil.getDistanceBetweenAngles(to.getYaw(), from.getYaw());
        float diffPitchRate = Math.abs(this.lastPitchRate - diffPitch);
        float diffYawRate = Math.abs(this.lastYawRate - diffYaw);
        float diffPitchRatePitch = Math.abs(diffPitchRate - diffPitch);
        float diffYawRateYaw = Math.abs(diffYawRate - diffYaw);
        if (playerData.getLastAttackTicks() <= (0xCC ^ 0xC6) && (double)diffPitch < 0.009 && (double)diffPitch > 0.001 && (double)diffPitchRate > 0.5 && (double)diffYawRate > 0.5 && (double)diffYaw > 2.0 && (double)this.lastYawRate > 1.5 && (diffPitchRatePitch > 1.0f || diffYawRateYaw > 1.0f)) {
            Object[] arrobject = new Object[0x34 ^ 0x3C];
            arrobject["".length()] = Float.valueOf(diffPitchRate);
            arrobject[" ".length()] = Float.valueOf(diffYawRate);
            arrobject["  ".length()] = Float.valueOf(this.lastPitchRate);
            arrobject["   ".length()] = Float.valueOf(this.lastYawRate);
            arrobject[147 ^ 151] = Float.valueOf(diffPitch);
            arrobject[11 ^ 14] = Float.valueOf(diffYaw);
            arrobject[91 ^ 93] = Float.valueOf(diffPitchRatePitch);
            arrobject[62 ^ 57] = Float.valueOf(diffYawRateYaw);
            AlertsManager.getInstance().handleViolation(playerData, this, String.format(I["  ".length()], arrobject));
        }
        this.lastPitchRate = diffPitch;
        this.lastYawRate = diffYaw;
    }

    private static void I() {
        I = new String["   ".length()];
        AimF.I["".length()] = AimF.I("\b", "NLGFh");
        AimF.I[" ".length()] = AimF.l("e8YxUuj4576L9RmehfnrCg==", "kcFrF");
        AimF.I["  ".length()] = AimF.l("U5axyJ2DOZzlpT578b8XBXe9HUKa8Mj1nDoENoYzCREwGyoYDy+ClTJsUIbcc/Nqv03L7eKV28CWK4E4T20zKEJXLw2vbotnPVr8KG4IJuk=", "pauRb");
    }
}

