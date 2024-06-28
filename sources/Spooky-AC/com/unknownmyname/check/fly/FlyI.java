/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.fly;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class FlyI
extends PacketCheck {
    private /* synthetic */ int threshold;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ boolean wasOnGround;
    private /* synthetic */ int lastCheckTicks;

    static {
        FlyI.I();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xA8 ^ 0xA0), "DES");
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

    private static void I() {
        I = new String["   ".length()];
        FlyI.I["".length()] = FlyI.I("VjXpbgcKq0c=", "KWfSz");
        FlyI.I[" ".length()] = FlyI.I("4J4lwySOYGw=", "guesJ");
        FlyI.I["  ".length()] = FlyI.l("MSQ3FcmpWrI=", "tvNIi");
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (player.isFlying()) {
                return;
            }
            if (playerData.isOnGround() && this.wasOnGround) {
                int n = this.lastCheckTicks;
                this.lastCheckTicks = n + " ".length();
                if (n > (0xB ^ 0x1F)) {
                    World world = player.getWorld();
                    Cuboid cuboid = new Cuboid(playerData.getLocation()).move(0.0, -0.5, 0.0).expand(0.5, 0.75, 0.5);
                    this.run(() -> {
                        if (cuboid.checkBlocks(world, type -> {
                            boolean bl;
                            if (type == Material.AIR) {
                                bl = " ".length();
                                "".length();
                                if (2 == 1) {
                                    throw null;
                                }
                            } else {
                                bl = "".length();
                            }
                            return bl;
                        })) {
                            int n = this.threshold;
                            this.threshold = n + " ".length();
                            if (n > "   ".length()) {
                                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                                this.lastCheckTicks = 0x22 ^ 0x37;
                                "".length();
                                if (1 >= 3) {
                                    throw null;
                                }
                            }
                        } else {
                            this.threshold = "".length();
                            this.violations -= Math.min(this.violations + 2.5, 1.0);
                        }
                    });
                    this.lastCheckTicks = "".length();
                }
            }
            this.wasOnGround = playerData.isOnGround();
        }
    }

    public FlyI() {
        super(Check.CheckType.FLYI, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastCheckTicks = "".length();
        this.wasOnGround = "".length();
        this.threshold = "".length();
        this.violations = -2.5;
    }
}

