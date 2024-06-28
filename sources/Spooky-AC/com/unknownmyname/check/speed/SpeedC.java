/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.speed;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MaterialList;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Set;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpeedC
extends MovementCheck {
    private /* synthetic */ Double lastAngle;
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (playerData.getSprinting() != null && playerData.getSprinting().booleanValue() && to.getOnGround().booleanValue() && from.getOnGround().booleanValue()) {
            double angle = Math.toDegrees(-Math.atan2(to.getX() - from.getX(), to.getZ() - from.getZ()));
            double angleDiff = Math.min(MathUtil.getDistanceBetweenAngles360(angle, to.getYaw()), MathUtil.getDistanceBetweenAngles360(angle, from.getYaw()));
            if (this.lastAngle != null) {
                double lastAngleDiff = MathUtil.getDistanceBetweenAngles360(this.lastAngle, angleDiff);
                if (angleDiff > 47.5) {
                    if (lastAngleDiff < 5.0) {
                        int n = this.threshold;
                        this.threshold = n + " ".length();
                        if (n > (0x9B ^ 0x9E)) {
                            World world = player.getWorld();
                            Cuboid cuboid = new Cuboid(playerData.getLocation()).expand(0.5, 0.5, 0.5);
                            this.run(() -> {
                                if (cuboid.checkBlocks(world, type -> {
                                    boolean bl;
                                    if (MaterialList.BAD_VELOCITY.contains(type)) {
                                        bl = "".length();
                                        "".length();
                                        if (false) {
                                            throw null;
                                        }
                                    } else {
                                        bl = " ".length();
                                    }
                                    return bl;
                                })) {
                                    AlertsManager.getInstance().handleViolation(playerData, this, angleDiff + I["  ".length()] + lastAngleDiff);
                                    "".length();
                                    if (4 < 3) {
                                        throw null;
                                    }
                                } else {
                                    this.threshold = -(0x6D ^ 0x79);
                                }
                            });
                            this.threshold = "".length();
                            "".length();
                            if (3 >= 4) {
                                throw null;
                            }
                        }
                    }
                } else {
                    this.threshold = "".length();
                }
            }
            this.lastAngle = angleDiff;
            "".length();
            if (2 != 2) {
                throw null;
            }
        } else {
            this.lastAngle = null;
            this.threshold = "".length();
        }
    }

    private static void I() {
        I = new String["   ".length()];
        SpeedC.I["".length()] = SpeedC.I("3", "wrEcV");
        SpeedC.I[" ".length()] = SpeedC.I("&\u0018\u00073\u0000", "uhbVd");
        SpeedC.I["  ".length()] = SpeedC.l("h45jqNqMON8=", "iDFgq");
    }

    public SpeedC() {
        super(Check.CheckType.SPEEDC, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.threshold = "".length();
        this.lastAngle = null;
        this.violations = -1.0;
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
            if (-1 < 2) continue;
            throw null;
        }
        return sb.toString();
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

    static {
        SpeedC.I();
    }
}

