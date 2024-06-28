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

public class FlyA
extends MovementCheck {
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int lastBypassTick;

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

    static {
        FlyA.I();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x86 ^ 0x8E), "DES");
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
        if (!player.isFlying() && playerData.getTeleportTicks() > " ".length()) {
            if (to.getY() % 0.5 == 0.0 && !to.getOnGround().booleanValue() && from.getY() < to.getY() && playerData.getTotalTicks() - (0x11 ^ 0x1B) > this.lastBypassTick) {
                World world = player.getWorld();
                Cuboid cuboid = new Cuboid(playerData.getLocation()).expand(0.5, 0.5, 0.5);
                int totalTicks = playerData.getTotalTicks();
                this.run(() -> {
                    if (cuboid.checkBlocks(world, type -> {
                        boolean bl;
                        if (MaterialList.INVALID_SHAPE.contains(type)) {
                            bl = "".length();
                            "".length();
                            if (0 == 3) {
                                throw null;
                            }
                        } else {
                            bl = " ".length();
                        }
                        return bl;
                    })) {
                        this.threshold += " ".length();
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(to.getY() % 1.0), this.threshold);
                        "".length();
                        if (3 == 2) {
                            throw null;
                        }
                    } else {
                        this.threshold = "".length();
                        this.violations -= Math.min(this.violations + 1.0, 0.05);
                        this.lastBypassTick = totalTicks;
                    }
                });
                "".length();
                if (true < false) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 1.0, 0.025);
                this.threshold = "".length();
            }
        }
    }

    public FlyA() {
        super(Check.CheckType.FLYA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.threshold = "".length();
        this.lastBypassTick = -(4 ^ 0xE);
        this.setMaxViolation(0x39 ^ 0x36);
        this.violations = -1.0;
    }

    private static void I() {
        I = new String["  ".length()];
        FlyA.I["".length()] = FlyA.I("jvNSNn8WO14=", "xqnnb");
        FlyA.I[" ".length()] = FlyA.l("hBz8KvidXwY=", "bnDkq");
    }
}

