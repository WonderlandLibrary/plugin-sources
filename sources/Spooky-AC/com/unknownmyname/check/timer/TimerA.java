/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPosition
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.timer;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import org.bukkit.entity.Player;

public class TimerA
extends PacketCheck {
    private static final /* synthetic */ long CHECK_TIME_LINEAR;
    private /* synthetic */ long offset;
    private /* synthetic */ Long lastPacket;
    private static final /* synthetic */ String[] I;

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x8E ^ 0x86), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TimerA() {
        super(Check.CheckType.TIMERA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastPacket = null;
        this.offset = -100L;
        this.violations = -2.0;
        this.setMaxViolation(0xC1 ^ 0xC4);
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            long now = System.nanoTime();
            if (this.lastPacket != null) {
                long diff = now - this.lastPacket;
                this.offset += TimerA.toNanos(50L) - diff;
                if (this.offset <= CHECK_TIME_LINEAR) {
                    this.violations -= Math.min(this.violations, 0.005);
                    "".length();
                    if (false < false) {
                        throw null;
                    }
                } else {
                    if (playerData.getTotalTicks() > (0xF2 ^ 0x96) && playerData.getTeleportTicks() > playerData.getMaxPingTicks() * "  ".length() && playerData.isSpawnedIn() && (playerData.getSteerTicks() == 0 || playerData.getSteerTicks() > playerData.getPingTicks())) {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(TimerA.fromNanos(this.offset)));
                    }
                    this.offset = 0L;
                }
            }
            this.lastPacket = now;
            "".length();
            if (2 >= 3) {
                throw null;
            }
        } else if (packet instanceof PacketPlayOutPosition) {
            this.offset = -100L;
        }
    }

    public static long toNanos(long number) {
        return TimeUnit.MILLISECONDS.toNanos(number);
    }

    public static long fromNanos(long number) {
        return TimeUnit.NANOSECONDS.toMillis(number);
    }

    static {
        TimerA.I();
        CHECK_TIME_LINEAR = TimerA.toNanos(45L);
    }

    private static void I() {
        I = new String["  ".length()];
        TimerA.I["".length()] = TimerA.I("31lo0bVpNsQ=", "GZNTF");
        TimerA.I[" ".length()] = TimerA.I("ooBY+3kuW2A=", "NaCsT");
    }
}

