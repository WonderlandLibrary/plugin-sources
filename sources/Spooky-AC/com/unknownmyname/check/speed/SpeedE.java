/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffectType
 */
package com.unknownmyname.check.speed;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.BukkitUtils;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpeedE
extends MovementCheck {
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x2E ^ 0x26), "DES");
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
        SpeedE.I["".length()] = SpeedE.I("jN30b3QywOk=", "MCIGr");
        SpeedE.I[" ".length()] = SpeedE.l("xkdlKDQ0Cos=", "bTbhb");
        SpeedE.I["  ".length()] = SpeedE.l("ibXwcwI8uZ4=", "HgWKS");
    }

    static {
        SpeedE.I();
    }

    public SpeedE() {
        super(Check.CheckType.SPEEDE, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.violations = -1.0;
        this.setMaxViolation(0xA ^ 0xF);
        this.threshold = "".length();
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

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (to.getOnGround().booleanValue() && from.getOnGround().booleanValue()) {
            if (playerData.getSneaking() != null && playerData.getSneaking().booleanValue()) {
                double d;
                double angle = Math.toDegrees(-Math.atan2(to.getX() - from.getX(), to.getZ() - from.getZ()));
                double angleDiff = MathUtil.getDistanceBetweenAngles360((float)angle, to.getYaw());
                double[] arrd = new double["  ".length()];
                arrd["".length()] = to.getX() - from.getX();
                arrd[" ".length()] = to.getZ() - from.getZ();
                double distance = MathUtil.hypot(arrd);
                if (angleDiff > 15.0) {
                    d = 0.09165;
                    "".length();
                    if (3 != 3) {
                        throw null;
                    }
                } else {
                    d = 0.0651;
                }
                double limit = d;
                limit += (double)BukkitUtils.getPotionLevel(player, PotionEffectType.SPEED) * 0.02;
                if ((double)player.getWalkSpeed() > 0.2) {
                    limit *= (double)player.getWalkSpeed() / 0.2;
                }
                if (distance > limit) {
                    int n = this.threshold;
                    this.threshold = n + " ".length();
                    if (n > (0xAF ^ 0xBC)) {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(distance - limit) + I["  ".length()] + angleDiff);
                        this.threshold = "".length();
                        "".length();
                        if (1 >= 4) {
                            throw null;
                        }
                    }
                } else {
                    this.violations -= Math.min(this.violations + 1.0, 0.001);
                    this.threshold = "".length();
                    "".length();
                    if (3 != 3) {
                        throw null;
                    }
                }
            } else {
                this.threshold = "".length();
            }
        }
    }
}

