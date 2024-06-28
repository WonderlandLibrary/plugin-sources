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
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class FlyD
extends PacketCheck {
    private /* synthetic */ int jumped;
    private /* synthetic */ int lastBypassTick;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ boolean jumping;
    private /* synthetic */ Double lastY;

    private static void I() {
        I = new String["   ".length()];
        FlyD.I["".length()] = FlyD.I("xzPxAKt1m/M=", "FjSbr");
        FlyD.I[" ".length()] = FlyD.l("\f?\u0017", "JSnCS");
        FlyD.I["  ".length()] = FlyD.I("xiqlfaoJA+w=", "QPrNA");
    }

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
            if (1 < 2) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (player.isFlying()) {
                return;
            }
            PacketPlayInFlying packetPlayInFlying = (PacketPlayInFlying)packet;
            if (!packetPlayInFlying.f() && playerData.getVelocityTicks() > playerData.getPingTicks() * "  ".length() && playerData.getTeleportTicks() > playerData.getPingTicks() && !player.isFlying() && playerData.getTotalTicks() - (0xBB ^ 0xB1) > this.lastBypassTick) {
                if (this.lastY != null) {
                    if (this.jumping && packetPlayInFlying.b() < this.lastY) {
                        int n = this.jumped;
                        this.jumped = n + " ".length();
                        if (n > " ".length()) {
                            World world = player.getWorld();
                            Cuboid cuboid = new Cuboid(playerData.getLocation()).add(new Cuboid(-0.5, 0.5, -0.5, 1.5, -0.5, 0.5));
                            int totalTicks = playerData.getTotalTicks();
                            this.run(() -> {
                                if (cuboid.checkBlocks(world, type -> {
                                    boolean bl;
                                    if (MaterialList.BAD_VELOCITY.contains(type)) {
                                        bl = "".length();
                                        "".length();
                                        if (0 >= 4) {
                                            throw null;
                                        }
                                    } else {
                                        bl = " ".length();
                                    }
                                    return bl;
                                })) {
                                    AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()], this.jumped - " ".length());
                                    "".length();
                                    if (2 <= 1) {
                                        throw null;
                                    }
                                } else {
                                    this.jumped = "".length();
                                    this.violations -= Math.min(this.violations + 1.0, 0.05);
                                    this.lastBypassTick = totalTicks;
                                }
                            });
                        }
                        this.jumping = "".length();
                        "".length();
                        if (!true) {
                            throw null;
                        }
                    } else if (packetPlayInFlying.b() > this.lastY) {
                        this.jumping = " ".length();
                        "".length();
                        if (2 <= 1) {
                            throw null;
                        }
                    }
                }
            } else if (playerData.getLocation().getY() % 0.5 == 0.0 || (playerData.getLocation().getY() - 0.41999998688697815) % 1.0 > 1.0E-15) {
                this.jumped = "".length();
                this.jumping = "".length();
            }
            this.violations -= Math.min(this.violations + 5.0, 0.025);
            if (packetPlayInFlying.h()) {
                this.lastY = packetPlayInFlying.b();
            }
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

    public FlyD() {
        super(Check.CheckType.FLYD, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.jumped = "".length();
        this.jumping = "".length();
        this.lastY = null;
        this.lastBypassTick = -(0x1B ^ 0x11);
        this.setMaxViolation(0x39 ^ 0x33);
        this.violations = -5.0;
    }

    static {
        FlyD.I();
    }
}

