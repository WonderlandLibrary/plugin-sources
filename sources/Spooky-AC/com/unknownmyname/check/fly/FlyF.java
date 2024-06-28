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
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class FlyF
extends MovementCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int threshold;
    private /* synthetic */ List<Double> whitelistedValues;
    private /* synthetic */ Double lastYChange;
    private /* synthetic */ int lastBypassTick;

    private static void I() {
        I = new String["  ".length()];
        FlyF.I["".length()] = FlyF.I("Dxx1vfUdVqU=", "tFOmy");
        FlyF.I[" ".length()] = FlyF.l("WbtWbU+qSn8=", "IifTy");
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 6 ^ 0xE), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    public FlyF() {
        super(Check.CheckType.FLYF, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastYChange = null;
        this.threshold = "".length();
        this.lastBypassTick = -(0x8C ^ 0x86);
        Double[] arrdouble = new Double["  ".length()];
        arrdouble["".length()] = 0.15523200451660202;
        arrdouble[" ".length()] = 0.23052736891296366;
        this.whitelistedValues = Arrays.asList(arrdouble);
        this.setMaxViolation(0x26 ^ 5);
        this.violations = -5.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (!player.isFlying() && !to.getOnGround().booleanValue() && !from.getOnGround().booleanValue() && playerData.getTotalTicks() - (0x7C ^ 0x76) > this.lastBypassTick && playerData.getTeleportTicks() > playerData.getPingTicks() && playerData.getSteerTicks() > playerData.getPingTicks() && playerData.isSpawnedIn()) {
            double yChange = Math.abs(from.getY() - to.getY());
            if (this.whitelistedValues.contains(yChange)) {
                return;
            }
            if (player.getNoDamageTicks() <= (0x61 ^ 0x6C)) {
                return;
            }
            if (player.getMaximumNoDamageTicks() <= (6 ^ 0xB)) {
                return;
            }
            if (this.lastYChange != null && yChange > 0.0) {
                if (yChange == this.lastYChange && (yChange < 0.098 || yChange > 0.09800001)) {
                    World world = player.getWorld();
                    Cuboid cuboid = new Cuboid(playerData.getLocation()).add(new Cuboid(-0.5, 0.5, -0.5, 1.5, -0.5, 0.5));
                    Cuboid cuboidAbove = new Cuboid(playerData.getLocation()).move(0.0, 2.0, 0.0).expand(0.5, 0.5, 0.5);
                    int totalTicks = playerData.getTotalTicks();
                    this.run(() -> {
                        if (cuboid.checkBlocks(world, type -> {
                            int n;
                            if (!MaterialList.BAD_VELOCITY.contains(type) && !MaterialList.STAIRS.contains(type)) {
                                n = " ".length();
                                "".length();
                                if (4 <= 1) {
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
                                if (3 <= 2) {
                                    throw null;
                                }
                            } else {
                                bl = "".length();
                            }
                            return bl;
                        })) {
                            if (yChange % 0.05 == 0.0) {
                                this.threshold += " ".length();
                            }
                            int n2 = this.threshold;
                            this.threshold = n2 + " ".length();
                            if (n2 <= " ".length()) {
                                return;
                            }
                            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(yChange), (double)this.threshold / 2.0);
                            return;
                        }
                        this.threshold = "".length();
                        this.violations -= Math.min(this.violations + 5.0, 0.01);
                        this.lastBypassTick = totalTicks;
                    });
                    "".length();
                    if (2 == -1) {
                        throw null;
                    }
                } else {
                    this.violations -= Math.min(this.violations + 5.0, 0.01);
                    this.threshold = "".length();
                }
            }
            this.lastYChange = yChange;
        }
    }

    static {
        FlyF.I();
    }
}

