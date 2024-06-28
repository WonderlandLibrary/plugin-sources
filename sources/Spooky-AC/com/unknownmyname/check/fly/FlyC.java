/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.GameMode
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.fly;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MaterialList;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Set;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FlyC
extends PacketCheck {
    private /* synthetic */ int threshold;
    private /* synthetic */ int lastBypassTick;
    private /* synthetic */ Double lastY;
    private static final /* synthetic */ String[] I;

    public FlyC() {
        super(Check.CheckType.FLYC, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastY = null;
        this.threshold = "".length();
        this.lastBypassTick = -(0x54 ^ 0x5E);
        this.setMaxViolation(0x23 ^ 0x1F);
        this.violations = -1.5;
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
            if (4 > -1) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String["  ".length()];
        FlyC.I["".length()] = FlyC.I("$\u001a\u0015\u001a!\t\u0010\u001c", "guxxH");
        FlyC.I[" ".length()] = FlyC.l("RWzDJKUb93U=", "YmtVZ");
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

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (player.isFlying() || player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
            PacketPlayInFlying packetPlayInFlying = (PacketPlayInFlying)packet;
            if (this.lastY != null) {
                double d;
                if (packetPlayInFlying.g()) {
                    d = packetPlayInFlying.b();
                    "".length();
                    if (0 <= -1) {
                        throw null;
                    }
                } else {
                    d = this.lastY;
                }
                double y = d;
                double d2 = d;
                if (!(this.lastY != y || player.getVehicle() != null || packetPlayInFlying.f() || player.isFlying() || playerData.getTeleportTicks() <= playerData.getPingTicks() || playerData.getTotalTicks() - (0x28 ^ 0x22) <= this.lastBypassTick || playerData.getVelocityTicks() <= playerData.getPingTicks() * "  ".length() || !playerData.isSpawnedIn() || playerData.hasLag() || playerData.hasFast())) {
                    World world = player.getWorld();
                    Cuboid cuboid = new Cuboid(playerData.getLocation()).add(new Cuboid(-0.5, 0.5, 0.0, 1.5, -0.5, 0.5));
                    int totalTicks = playerData.getTotalTicks();
                    this.run(() -> {
                        if (cuboid.checkBlocks(world, type -> {
                            boolean bl;
                            if (MaterialList.BAD_VELOCITY.contains(type)) {
                                bl = "".length();
                                "".length();
                                if (3 >= 4) {
                                    throw null;
                                }
                            } else {
                                bl = " ".length();
                            }
                            return bl;
                        })) {
                            int n2 = this.threshold;
                            this.threshold = n2 + " ".length();
                            if (n2 > "  ".length()) {
                                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(y), this.threshold - " ".length());
                                "".length();
                                if (2 <= 1) {
                                    throw null;
                                }
                            }
                        } else {
                            this.threshold = "".length();
                            this.violations -= Math.min(this.violations + 1.5, 0.01);
                            this.lastBypassTick = totalTicks;
                        }
                    });
                    "".length();
                    if (0 == -1) {
                        throw null;
                    }
                } else {
                    this.run(() -> {
                        this.threshold = "".length();
                        this.violations -= Math.min(this.violations + 1.5, 0.01);
                    });
                }
            }
            if (packetPlayInFlying.g()) {
                this.lastY = packetPlayInFlying.b();
            }
        }
    }

    static {
        FlyC.I();
    }
}

