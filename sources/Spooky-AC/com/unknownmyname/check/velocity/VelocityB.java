/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package com.unknownmyname.check.velocity;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityB
extends MovementCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int lastBypassTicks;
    private /* synthetic */ double total;

    private static void I() {
        I = new String["   ".length()];
        VelocityB.I["".length()] = VelocityB.I("sdI3cTNpBvQ=", "KkvZa");
        VelocityB.I[" ".length()] = VelocityB.l("R3p4VfOXErkQNVowN81Ndg==", "lcMGp");
        VelocityB.I["  ".length()] = VelocityB.l("5nan2AKz8eg=", "ZqOWm");
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (playerData.getVelX() != 0.0 && playerData.getVelZ() != 0.0 && playerData.getHorizontalVelocityTicks() > playerData.getMoveTicks()) {
            double d;
            double y = to.getY() - from.getY();
            if (d > 0.0) {
                double[] arrd = new double["  ".length()];
                arrd["".length()] = playerData.getVelX();
                arrd[" ".length()] = playerData.getVelZ();
                double velocity = MathUtil.hypot(arrd);
                if (playerData.getLastLastLocation().getOnGround().booleanValue() && from.getOnGround().booleanValue() && !to.getOnGround().booleanValue() && MathUtil.onGround(from.getY()) && !MathUtil.onGround(to.getY()) && velocity > 0.0 && playerData.getTotalTicks() - this.lastBypassTicks > (0x35 ^ 0x3F)) {
                    Vector vector = new Vector(playerData.getLastLastLocation().getX(), playerData.getLastLastLocation().getY(), playerData.getLastLastLocation().getZ());
                    vector.subtract(new Vector(from.getX(), from.getY(), from.getZ()));
                    PlayerLocation newTo = to.add(vector.getX(), vector.getY(), vector.getZ());
                    double[] arrd2 = new double["  ".length()];
                    arrd2["".length()] = to.getX() - from.getX();
                    arrd2[" ".length()] = to.getZ() - from.getZ();
                    double distance = MathUtil.hypot(arrd2);
                    double[] arrd3 = new double["  ".length()];
                    arrd3["".length()] = newTo.getX() - from.getX();
                    arrd3[" ".length()] = newTo.getZ() - from.getZ();
                    double properDistance = MathUtil.hypot(arrd3);
                    double percentage = Math.max(distance, properDistance) / velocity;
                    this.violations -= Math.min(this.violations, 0.01);
                    if (percentage < 1.0) {
                        World world = player.getWorld();
                        Cuboid cuboid = new Cuboid(to).add(new Cuboid(-1.0, 1.0, 0.0, 2.05, -1.0, 1.0));
                        int totalTicks = playerData.getTotalTicks();
                        this.run(() -> {
                            if (cuboid.checkBlocks(world, type -> {
                                boolean bl;
                                if (type == Material.AIR) {
                                    bl = " ".length();
                                    "".length();
                                    if (3 < 3) {
                                        throw null;
                                    }
                                } else {
                                    bl = "".length();
                                }
                                return bl;
                            })) {
                                this.total += 1.0 - percentage;
                                if (this.total > 2.0) {
                                    this.total = 0.0;
                                    AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()], 1.0);
                                    "".length();
                                    if (2 <= 0) {
                                        throw null;
                                    }
                                }
                            } else {
                                this.total -= Math.min(this.total, 0.03);
                                this.lastBypassTicks = totalTicks;
                            }
                        });
                        "".length();
                        if (4 <= 0) {
                            throw null;
                        }
                    } else {
                        this.total -= Math.min(this.total, 0.03);
                    }
                }
                playerData.setVelX(0.0);
                playerData.setVelZ(0.0);
            }
        }
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x93 ^ 0x9B), "DES");
            Cipher des = Cipher.getInstance("DES");
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
        VelocityB.I();
    }

    public VelocityB() {
        super(Check.CheckType.VELOCITYB, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastBypassTicks = "".length();
        this.total = 0.0;
    }
}

