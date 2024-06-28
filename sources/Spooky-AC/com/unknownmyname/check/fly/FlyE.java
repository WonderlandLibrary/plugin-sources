/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.fly;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MaterialList;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class FlyE
extends MovementCheck {
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int lastBypassTick;

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
            if (-1 < 2) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (from.getOnGround().booleanValue() && to.getY() > from.getY() && playerData.getVelocityTicks() > (playerData.getPingTicks() + " ".length()) * (8 ^ 0xC) && playerData.getTeleportTicks() > playerData.getMaxPingTicks() * "  ".length() && playerData.isSpawnedIn() && !player.isFlying() && playerData.getTotalTicks() - (0x22 ^ 0x28) > this.lastBypassTick) {
            if (to.getY() - from.getY() < 0.41999998688697815 && (to.getY() - 0.41999998688697815) % 1.0 > 1.0E-15) {
                if (player.getMaximumNoDamageTicks() <= (0x2C ^ 0x21)) {
                    return;
                }
                World world = player.getWorld();
                Cuboid cuboid = new Cuboid(playerData.getLocation()).move(0.0, 1.8, 0.0).expand(0.5, 0.5, 0.5);
                Cuboid cuboidUnder = new Cuboid(playerData.getLocation()).move(0.0, -0.25, 0.0).expand(0.5, 0.75, 0.5);
                double yDiff = to.getY() - from.getY();
                int totalTicks = playerData.getTotalTicks();
                this.run(() -> {
                    if (cuboid.checkBlocks(world, type -> {
                        boolean bl;
                        if (type == Material.AIR) {
                            bl = " ".length();
                            "".length();
                            if (!true) {
                                throw null;
                            }
                        } else {
                            bl = "".length();
                        }
                        return bl;
                    }) && cuboidUnder.checkBlocks(world, type -> {
                        int n;
                        if (!MaterialList.INVALID_SHAPE.contains(type) && !MaterialList.BAD_VELOCITY.contains(type)) {
                            n = " ".length();
                            "".length();
                            if (4 != 4) {
                                throw null;
                            }
                        } else {
                            n = "".length();
                        }
                        return n != 0;
                    })) {
                        this.threshold += " ".length();
                        AlertsManager.getInstance().handleViolation(playerData, this, yDiff + I["  ".length()] + to.getY(), this.threshold);
                        return;
                    }
                    this.threshold = "".length();
                    this.violations -= Math.min(this.violations + 2.5, 0.025);
                    this.lastBypassTick = totalTicks;
                });
                "".length();
                if (1 == -1) {
                    throw null;
                }
            } else {
                this.threshold = "".length();
                this.violations -= Math.min(this.violations + 2.5, 0.025);
            }
        }
    }

    static {
        FlyE.I();
    }

    private static void I() {
        I = new String["   ".length()];
        FlyE.I["".length()] = FlyE.I("gQO4TLa4HeU=", "HxAAo");
        FlyE.I[" ".length()] = FlyE.l("<\u0016:", "zzCIA");
        FlyE.I["  ".length()] = FlyE.l("n", "NvzCv");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x73 ^ 0x7B), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FlyE() {
        super(Check.CheckType.FLYE, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.threshold = "".length();
        this.lastBypassTick = -(0x4B ^ 0x41);
        this.setMaxViolation(0x1F ^ 0x3C);
        this.violations = -4.5;
    }
}

