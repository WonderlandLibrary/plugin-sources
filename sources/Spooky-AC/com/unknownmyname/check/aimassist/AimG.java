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

public class AimG
extends AimCheck {
    private /* synthetic */ float lastPitchRate;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ float lastYawRate;

    public AimG() {
        super(Check.CheckType.AIM_ASSISTG, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.violations = -1.0;
    }

    static {
        AimG.I();
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

    private static void I() {
        I = new String["   ".length()];
        AimG.I["".length()] = AimG.I("DPUJLieECBA=", "OicJe");
        AimG.I[" ".length()] = AimG.l("M5mq2h12u7jhvTBrH1LG1Q==", "SfUrH");
        AimG.I["  ".length()] = AimG.I("h8Oih1yvzW/UPb8LnBuMpKt6VXr57njvDVUNHJlH2ipwR+F8TgGaJejWuMD2y7SzZWO6WDHn+Lr9V+ONsfzPxCGRXdlA84cmMdkCVTL4wns=", "vKqfQ");
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        float diffPitch = (float)MathUtil.getDistanceBetweenAngles(to.getPitch(), from.getPitch());
        float diffYaw = (float)MathUtil.getDistanceBetweenAngles(to.getYaw(), from.getYaw());
        float diffYawPitch = Math.abs(diffYaw - diffPitch);
        float diffPitchRate = Math.abs(this.lastPitchRate - diffPitch);
        float diffYawRate = Math.abs(this.lastYawRate - diffYaw);
        float diffPitchRatePitch = Math.abs(diffPitchRate - diffPitch);
        float diffYawRateYaw = Math.abs(diffYawRate - diffYaw);
        if (playerData.getLastAttackTicks() < (0x69 ^ 0x63) && diffYaw > 0.05f && (double)diffPitch > 0.05 && ((double)diffPitchRate > 0.9 || (double)diffYawRate > 0.9) && (diffPitchRatePitch > 1.0f || diffYawRateYaw > 1.0f) && diffYawPitch < 0.009f && diffYawPitch > 0.001f) {
            Object[] arrobject = new Object[0x16 ^ 0x1E];
            arrobject["".length()] = Float.valueOf(diffPitchRate);
            arrobject[" ".length()] = Float.valueOf(diffYawRate);
            arrobject["  ".length()] = Float.valueOf(this.lastPitchRate);
            arrobject["   ".length()] = Float.valueOf(this.lastYawRate);
            arrobject[152 ^ 156] = Float.valueOf(diffPitch);
            arrobject[8 ^ 13] = Float.valueOf(diffYaw);
            arrobject[87 ^ 81] = Float.valueOf(diffPitchRatePitch);
            arrobject[152 ^ 159] = Float.valueOf(diffYawRateYaw);
            AlertsManager.getInstance().handleViolation(playerData, this, String.format(I["  ".length()], arrobject));
        }
        this.lastPitchRate = diffPitch;
        this.lastYawRate = diffYaw;
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x87 ^ 0x8F), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

