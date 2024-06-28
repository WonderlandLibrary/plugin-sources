/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffectType
 */
package com.unknownmyname.check.speed;

import com.google.common.util.concurrent.AtomicDouble;
import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.BukkitUtils;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MaterialList;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpeedA
extends MovementCheck {
    private /* synthetic */ int lastBypassTick;
    private /* synthetic */ int sprintTicks;
    private /* synthetic */ int bypassFallbackTicks;
    private /* synthetic */ int lastFastAirTick;
    private /* synthetic */ int lastGroundTick;
    private /* synthetic */ int lastBlockAboveTick;
    private /* synthetic */ int lastAirTick;
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
            if (2 != -1) continue;
            throw null;
        }
        return sb.toString();
    }

    private static String lI(String obj, String key) {
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

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x23 ^ 0x2B), "DES");
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
        if (((CraftPlayer)player).getHandle().ping >= 4 + 22 - -121 + 3) {
            return;
        }
        if (!(to.getX() == from.getX() && to.getZ() == from.getZ() || playerData.getTeleportTicks() <= playerData.getMaxPingTicks() || playerData.getHorizontalSpeedTicks() <= (playerData.getPingTicks() + (0xA6 ^ 0xAC)) * "  ".length() || player.getAllowFlight() || playerData.hasLag(timestamp - 50L))) {
            double[] arrd = new double["  ".length()];
            arrd["".length()] = to.getX() - from.getX();
            arrd[" ".length()] = to.getZ() - from.getZ();
            double distance = MathUtil.hypot(arrd);
            AtomicDouble limit = new AtomicDouble(0.0);
            if (to.getOnGround().booleanValue()) {
                double d;
                int n;
                int n2;
                double angle;
                double angleDiff;
                if (this.bypassFallbackTicks > 0) {
                    this.bypassFallbackTicks -= 0xB1 ^ 0xBB;
                }
                this.lastGroundTick = playerData.getTotalTicks();
                if (playerData.getSprinting() != null && !playerData.getSprinting().booleanValue()) {
                    n2 = this.sprintTicks = this.sprintTicks + " ".length();
                    "".length();
                    if (2 >= 4) {
                        throw null;
                    }
                } else {
                    n2 = this.sprintTicks = "".length();
                }
                if ((angleDiff = MathUtil.getDistanceBetweenAngles360(angle = Math.toDegrees(-Math.atan2(to.getX() - from.getX(), to.getZ() - from.getZ())), to.getYaw())) < 5.0 && angleDiff < 90.0) {
                    n = " ".length();
                    "".length();
                    if (2 <= 0) {
                        throw null;
                    }
                } else {
                    n = "".length();
                }
                int straightSprint = n;
                limit.addAndGet((double)BukkitUtils.getPotionLevel(player, PotionEffectType.SPEED) * 0.0573);
                if (this.sprintTicks <= " ".length()) {
                    if (straightSprint != 0) {
                        d = 0.281;
                        "".length();
                        if (!true) {
                            throw null;
                        }
                    } else {
                        d = 0.2865;
                        "".length();
                        if (-1 == 4) {
                            throw null;
                        }
                    }
                } else if (straightSprint != 0) {
                    d = 0.217;
                    "".length();
                    if (-1 != -1) {
                        throw null;
                    }
                } else {
                    d = 0.2325;
                }
                limit.addAndGet(d);
                if ((double)player.getWalkSpeed() > 0.2) {
                    limit.set(limit.get() * (double)player.getWalkSpeed() / 0.2);
                }
                if (this.lastAirTick >= this.lastGroundTick - (0xE ^ 4)) {
                    limit.addAndGet((double)(this.lastGroundTick - this.lastAirTick) * 0.125);
                    "".length();
                    if (false) {
                        throw null;
                    }
                }
            } else {
                double d;
                if (this.bypassFallbackTicks > 0) {
                    limit.addAndGet(0.1);
                    this.bypassFallbackTicks -= " ".length();
                }
                this.lastAirTick = playerData.getTotalTicks();
                int fastAir = "".length();
                if (distance > 0.36 && this.lastFastAirTick < this.lastGroundTick) {
                    this.lastFastAirTick = playerData.getTotalTicks();
                    limit.addAndGet(0.6125);
                    fastAir = " ".length();
                    "".length();
                    if (3 != 3) {
                        throw null;
                    }
                } else {
                    limit.addAndGet(0.36);
                }
                if (fastAir != 0) {
                    d = 0.0375;
                    "".length();
                    if (2 == -1) {
                        throw null;
                    }
                } else {
                    d = 0.0175;
                }
                limit.addAndGet((double)BukkitUtils.getPotionLevel(player, PotionEffectType.SPEED) * d);
                if ((double)player.getWalkSpeed() > 0.2) {
                    limit.addAndGet(limit.get() * ((double)player.getWalkSpeed() - 0.2) * 2.0);
                }
            }
            if (distance > limit.get()) {
                World world = player.getWorld();
                PlayerLocation playerLocation = playerData.getLocation();
                Cuboid cuboidAbove = new Cuboid(playerLocation).move(0.0, 2.0, 0.0).expand(0.5, 0.5, 0.5);
                Cuboid cuboid = new Cuboid(playerLocation).move(0.0, -0.5, 0.0).expand(0.5, 1.0, 0.5);
                int totalTicks = playerData.getTotalTicks();
                int sprintTicks = this.sprintTicks;
                int lastAttackTicks = playerData.getLastAttackTicks();
                this.run(() -> {
                    int n3;
                    int n4;
                    if (totalTicks - (0x47 ^ 0x53) < this.lastBlockAboveTick) {
                        n4 = " ".length();
                        "".length();
                        if (3 <= 0) {
                            throw null;
                        }
                    } else {
                        n4 = "".length();
                    }
                    int blockAbove = n4;
                    int bl = n4;
                    if (blockAbove == 0 && !cuboidAbove.checkBlocks(world, type -> {
                        boolean bl;
                        if (type == Material.AIR) {
                            bl = " ".length();
                            "".length();
                            if (2 <= -1) {
                                throw null;
                            }
                        } else {
                            bl = "".length();
                        }
                        return bl;
                    })) {
                        blockAbove = " ".length();
                        this.lastBlockAboveTick = totalTicks;
                    }
                    if (totalTicks - (0x78 ^ 0x50) < this.lastBypassTick) {
                        n3 = " ".length();
                        "".length();
                        if (2 < 2) {
                            throw null;
                        }
                    } else {
                        n3 = "".length();
                    }
                    int bypass = n3;
                    int bl2 = n3;
                    if (bypass == 0 && !cuboid.checkBlocks(world, type -> {
                        int n;
                        if (!(MaterialList.ICE.contains(type) || MaterialList.SLABS.contains(type) || MaterialList.STAIRS.contains(type) || type == Material.SLIME_BLOCK)) {
                            n = " ".length();
                            "".length();
                            if (4 <= 0) {
                                throw null;
                            }
                        } else {
                            n = "".length();
                        }
                        return n != 0;
                    })) {
                        bypass = " ".length();
                        this.lastBypassTick = totalTicks;
                    }
                    if (blockAbove != 0) {
                        limit.addAndGet(0.2);
                    }
                    if (bypass != 0) {
                        double d2;
                        if (to.getOnGround().booleanValue()) {
                            d2 = 0.2;
                            "".length();
                            if (2 < 1) {
                                throw null;
                            }
                        } else {
                            d2 = 0.3;
                        }
                        limit.addAndGet(d2);
                        this.bypassFallbackTicks = 0x78 ^ 0x44;
                    }
                    if (distance > limit.get()) {
                        String string;
                        if (to.getOnGround().booleanValue()) {
                            string = I["   ".length()];
                            "".length();
                            if (-1 != -1) {
                                throw null;
                            }
                        } else {
                            string = I[0xA1 ^ 0xA5];
                        }
                        AlertsManager.getInstance().handleViolation(playerData, this, distance - limit.get() + I["  ".length()] + string + I[0x6F ^ 0x6A] + I[0xB1 ^ 0xB7] + sprintTicks, distance - limit.get() + 0.3);
                        "".length();
                        if (3 == 4) {
                            throw null;
                        }
                    } else {
                        this.violations -= Math.min(this.violations + 5.0, 0.025);
                    }
                });
                "".length();
                if (1 <= -1) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 5.0, 0.025);
            }
        }
    }

    static {
        SpeedA.I();
    }

    private static void I() {
        I = new String[0x3C ^ 0x3B];
        SpeedA.I["".length()] = SpeedA.I("\r", "LNnAh");
        SpeedA.I[" ".length()] = SpeedA.l("pWvLV7NI2u4=", "qiwur");
        SpeedA.I["  ".length()] = SpeedA.lI("dsSYHEMNAao=", "YyhaM");
        SpeedA.I["   ".length()] = SpeedA.lI("f9g43Su3bSE=", "sUAgJ");
        SpeedA.I[10 ^ 14] = SpeedA.lI("RDnuTsX6PiQ=", "OPpTP");
        SpeedA.I[187 ^ 190] = SpeedA.lI("YEwLHExav1g=", "dkRVA");
        SpeedA.I[155 ^ 157] = SpeedA.l("oJpPu4+TSL06tcb83LJKqg==", "mtdeP");
    }

    public SpeedA() {
        super(Check.CheckType.SPEEDA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastGroundTick = "".length();
        this.lastFastAirTick = "".length();
        this.lastAirTick = "".length();
        this.lastBlockAboveTick = -(0xB9 ^ 0xAD);
        this.lastBypassTick = -(0x74 ^ 0x5C);
        this.bypassFallbackTicks = "".length();
        this.sprintTicks = "".length();
        this.setMaxViolation(0x80 ^ 0x8F);
        this.violations = -5.0;
    }
}

