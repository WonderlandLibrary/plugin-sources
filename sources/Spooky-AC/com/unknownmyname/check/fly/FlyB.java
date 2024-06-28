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
import com.unknownmyname.util.MathUtil;
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

public class FlyB
extends MovementCheck {
    private /* synthetic */ int lastBypassTick;
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x2F ^ 0x27), "DES");
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
        I = new String["  ".length()];
        FlyB.I["".length()] = FlyB.I("lglZVkvTKgw=", "fJiVX");
        FlyB.I[" ".length()] = FlyB.I("0fDmSq0frCw=", "dDyBV");
    }

    static {
        FlyB.I();
    }

    public FlyB() {
        super(Check.CheckType.FLYB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.threshold = "".length();
        this.lastBypassTick = -(0xA6 ^ 0xAC);
        this.setMaxViolation(0xAA ^ 0xA5);
        this.violations = -4.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (from.getOnGround().booleanValue() && to.getOnGround().booleanValue() && from.getY() != to.getY() && !MathUtil.onGround(from.getY()) && !MathUtil.onGround(to.getY()) && playerData.isSpawnedIn() && playerData.getTotalTicks() - (0xBC ^ 0xB6) > this.lastBypassTick) {
            World world = player.getWorld();
            Cuboid cuboid = new Cuboid(playerData.getLocation()).expand(0.5, 0.5, 0.5);
            double yDiff = to.getY() - from.getY();
            int totalTicks = playerData.getTotalTicks();
            this.run(() -> {
                if (cuboid.checkBlocks(world, type -> {
                    boolean bl;
                    if (MaterialList.INVALID_SHAPE.contains(type)) {
                        bl = "".length();
                        "".length();
                        if (3 != 3) {
                            throw null;
                        }
                    } else {
                        bl = " ".length();
                    }
                    return bl;
                })) {
                    this.threshold += " ".length();
                    AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(yDiff), this.threshold);
                    "".length();
                    if (3 != 3) {
                        throw null;
                    }
                } else {
                    this.threshold = "".length();
                    this.violations -= Math.min(this.violations + 4.0, 0.05);
                    this.lastBypassTick = totalTicks;
                }
            });
            "".length();
            if (3 <= 0) {
                throw null;
            }
        } else {
            this.threshold = "".length();
            this.violations -= Math.min(this.violations + 4.0, 0.05);
        }
    }
}

