/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class TimerB
extends PacketCheck {
    private /* synthetic */ long lastPacket;
    private final /* synthetic */ Queue<Long> delays;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int count;

    public TimerB() {
        super(Check.CheckType.TIMERB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.delays = new ConcurrentLinkedQueue<Long>();
        this.lastPacket = 0L;
        this.count = "".length();
        this.violations = -2.0;
        this.setMaxViolation(0x2B ^ 0x21);
    }

    private static void I() {
        I = new String["   ".length()];
        TimerB.I["".length()] = TimerB.I("2fVnCmT52BY=", "scubR");
        TimerB.I[" ".length()] = TimerB.I("glqI1jIxb6Q=", "ewDfd");
        TimerB.I["  ".length()] = TimerB.I("ZUy/BLDMq7E=", "FNiBC");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x20 ^ 0x28), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        TimerB.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            PacketPlayInFlying packetPlayInFlying = (PacketPlayInFlying)packet;
            if (playerData.getTeleportTicks() > playerData.getPingTicks()) {
                long delay = timestamp - this.lastPacket;
                if (delay > 100L) {
                    int n = this.count;
                    this.count = n + " ".length();
                    if (n > (0xAF ^ 0xA9)) {
                        this.count = "".length();
                        AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()], 1.0);
                        "".length();
                        if (0 == 3) {
                            throw null;
                        }
                    }
                } else {
                    this.count = "".length();
                    this.violations -= Math.min(this.violations + 2.0, 0.001);
                }
                this.lastPacket = timestamp;
            }
        }
    }
}

