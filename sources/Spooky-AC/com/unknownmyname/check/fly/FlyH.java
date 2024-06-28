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

public class FlyH
extends MovementCheck {
    private /* synthetic */ Double lastYDiff;
    private /* synthetic */ int lastBypassTick;
    private static final /* synthetic */ String[] I;

    private static void I() {
        I = new String[0x93 ^ 0x95];
        FlyH.I["".length()] = FlyH.I("\u001d", "UrOYU");
        FlyH.I[" ".length()] = FlyH.I("?9\f", "yUuJn");
        FlyH.I["  ".length()] = FlyH.l("zkyQuu4nf94=", "vDeUe");
        FlyH.I["   ".length()] = FlyH.lI("PVimtf4KmAA=", "YvghO");
        FlyH.I[75 ^ 79] = FlyH.lI("/jLPk9e6g8I=", "YFdpa");
        FlyH.I[26 ^ 31] = FlyH.lI("Pq6SuyThdyo=", "WDNaD");
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x96 ^ 0x9E), "DES");
            Cipher des = Cipher.getInstance("DES");
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
        if (!from.getOnGround().booleanValue() && !to.getOnGround().booleanValue() && to.getY() != from.getY() && playerData.getTeleportTicks() > playerData.getMaxPingTicks() * "  ".length()) {
            boolean bl;
            if (player.isFlying()) {
                return;
            }
            double yDiff = to.getY() - from.getY();
            if (from.getX() != to.getX() && from.getZ() != to.getZ()) {
                bl = " ".length();
                "".length();
                if (4 == 0) {
                    throw null;
                }
            } else {
                bl = "".length();
            }
            boolean XZ = bl;
            boolean bl2 = bl;
            if (this.lastYDiff != null && Math.abs(yDiff / 0.9800000190734863 + 0.08) > 1.0E-12 && Math.abs(yDiff + 0.9800000190734863) > 1.0E-12 && Math.abs(yDiff - 0.019999999105930755) > 1.0E-12) {
                double predictedYDiff = (this.lastYDiff - 0.08) * 0.9800000190734863;
                double diff = Math.abs(predictedYDiff - yDiff);
                if (playerData.getVelocityTicks() > (0xCC ^ 0xC3) && playerData.getTeleportTicks() > playerData.getPingTicks() && !player.getAllowFlight() && playerData.getTotalTicks() - (0x43 ^ 0x49) > this.lastBypassTick) {
                    if (diff > 0.01) {
                        World world = player.getWorld();
                        Cuboid cuboid = new Cuboid(playerData.getLocation()).add(new Cuboid(-0.5, 0.5, -0.5, 1.5, -0.5, 0.5));
                        Cuboid cuboidAbove = new Cuboid(playerData.getLocation()).move(0.0, 2.0, 0.0).add(new Cuboid(-0.5, 0.5, -0.5, 0.5, -0.5, 0.5));
                        int totalTicks = playerData.getTotalTicks();
                        this.run(() -> {
                            if (cuboid.checkBlocks(world, type -> {
                                int n;
                                if (!MaterialList.BAD_VELOCITY.contains(type) && !MaterialList.INVALID_SHAPE.contains(type)) {
                                    n = " ".length();
                                    "".length();
                                    if (1 <= -1) {
                                        throw null;
                                    }
                                } else {
                                    n = "".length();
                                }
                                return n != 0;
                            }) && cuboidAbove.checkBlocks(world, type -> {
                                boolean bl;
                                if (type == Material.AIR) {
                                    bl = " ".length();
                                    "".length();
                                    if (4 <= 2) {
                                        throw null;
                                    }
                                } else {
                                    bl = "".length();
                                }
                                return bl;
                            })) {
                                double d3;
                                if (XZ) {
                                    d3 = 1.0;
                                    "".length();
                                    if (2 != 2) {
                                        throw null;
                                    }
                                } else {
                                    d3 = 0.5;
                                }
                                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(playerData.getVelocityTicks()) + I["  ".length()] + diff + I["   ".length()] + yDiff + I[0x8D ^ 0x89] + to.getY() % 1.0 + I[9 ^ 0xC] + XZ, d3);
                                "".length();
                                if (4 <= 3) {
                                    throw null;
                                }
                            } else {
                                this.violations -= Math.min(this.violations + 2.5, 0.025);
                                this.lastBypassTick = totalTicks;
                            }
                        });
                        "".length();
                        if (4 <= 3) {
                            throw null;
                        }
                    } else {
                        this.violations -= Math.min(this.violations + 2.5, 0.025);
                    }
                }
            }
            this.lastYDiff = yDiff;
            "".length();
            if (true <= false) {
                throw null;
            }
        } else {
            this.lastYDiff = null;
        }
    }

    static {
        FlyH.I();
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
            if (2 >= 2) continue;
            throw null;
        }
        return sb.toString();
    }

    public FlyH() {
        super(Check.CheckType.FLYH, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastYDiff = null;
        this.lastBypassTick = "".length();
        this.setMaxViolation(0x20 ^ 0x44);
        this.violations = -10.0;
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
}

