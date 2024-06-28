/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.autoclicker;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerD
extends PacketCheck {
    private /* synthetic */ boolean swung;
    private /* synthetic */ boolean digging;
    private /* synthetic */ int ticks;
    private /* synthetic */ boolean abortedDigging;
    private /* synthetic */ int done;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int lastTicks;
    private /* synthetic */ Queue<Integer> averageTicks;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.swung && !this.digging) {
                if (this.ticks < (0xBD ^ 0xBA)) {
                    this.averageTicks.add(this.ticks);
                    if (this.averageTicks.size() > (0x42 ^ 0x70)) {
                        this.averageTicks.poll();
                    }
                }
                if (this.averageTicks.size() > (0x6F ^ 0x47)) {
                    double average = MathUtil.average(this.averageTicks);
                    if (average < 2.5) {
                        if (this.ticks > "   ".length() && this.ticks < (0x89 ^ 0x9D) && this.lastTicks < (0x69 ^ 0x7D)) {
                            this.violations -= Math.min(this.violations, 0.25);
                            this.done = "".length();
                            "".length();
                            if (false) {
                                throw null;
                            }
                        } else {
                            int n = this.done;
                            this.done = n + " ".length();
                            if ((double)n > 600.0 / (average * 1.5)) {
                                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(average), 1.0);
                                this.done = "".length();
                                "".length();
                                if (-1 >= 0) {
                                    throw null;
                                }
                            }
                        }
                    } else {
                        this.done = "".length();
                    }
                }
                this.lastTicks = this.ticks;
                this.ticks = "".length();
            }
            if (this.abortedDigging) {
                this.digging = "".length();
                this.abortedDigging = "".length();
            }
            this.swung = "".length();
            this.ticks += " ".length();
            "".length();
            if (4 != 4) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInArmAnimation) {
            this.swung = " ".length();
            "".length();
            if (2 <= 0) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                this.digging = " ".length();
                this.abortedDigging = "".length();
                "".length();
                if (4 == 2) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                this.abortedDigging = " ".length();
            }
        }
    }

    public AutoClickerD() {
        super(Check.CheckType.AUTO_CLICKERD, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.averageTicks = new ConcurrentLinkedQueue<Integer>();
        this.ticks = "".length();
        this.swung = "".length();
        this.digging = "".length();
        this.abortedDigging = "".length();
        this.done = "".length();
        this.lastTicks = "".length();
    }

    private static void I() {
        I = new String["  ".length()];
        AutoClickerD.I["".length()] = AutoClickerD.I("4nTgIChzvOk=", "ewmcm");
        AutoClickerD.I[" ".length()] = AutoClickerD.l("\u0012\u0004\u0003\tx\u0010\u001d\u001e\u000536\u0003", "SqwfX");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x13 ^ 0x1B), "DES");
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
            if (2 != 0) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        AutoClickerD.I();
    }
}

