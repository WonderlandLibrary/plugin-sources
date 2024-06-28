/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.GameMode
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
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class AutoClickerG
extends PacketCheck {
    private /* synthetic */ int ticksSinceArm;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int ticksSinceBreak;
    private /* synthetic */ boolean swing;
    private /* synthetic */ Queue<Integer> swings;

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

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x7C ^ 0x74), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String lI(String obj, String key) {
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
            if (3 > 0) continue;
            throw null;
        }
        return sb.toString();
    }

    public AutoClickerG() {
        super(Check.CheckType.AUTO_CLICKERG, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.swing = "".length();
        this.ticksSinceBreak = 0x18 ^ 0x7C;
        this.ticksSinceArm = 0x14 ^ 0x1E;
        this.swings = new ConcurrentLinkedQueue<Integer>();
        this.violations = -2.0;
    }

    public double getCPS() {
        return 20.0 / MathUtil.average(this.swings);
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.swing && !playerData.isSwingDigging()) {
                this.swings.add(this.ticksSinceArm);
                if (this.swings.size() > (0x6D ^ 0x64)) {
                    this.swings.poll();
                }
                this.ticksSinceArm = "".length();
            }
            this.swing = "".length();
            this.ticksSinceBreak += " ".length();
            this.ticksSinceArm += " ".length();
            "".length();
            if (2 != 2) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInArmAnimation) {
            this.swing = " ".length();
            "".length();
            if (4 < -1) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK && player.getGameMode() == GameMode.SURVIVAL) {
                double cps = this.getCPS();
                if (this.ticksSinceBreak > (0x7D ^ 0x79) && this.ticksSinceArm <= "  ".length() && cps > 7.0) {
                    if (this.swing) {
                        this.violations -= Math.min(this.violations + 2.0, 0.2);
                        "".length();
                        if (3 < 2) {
                            throw null;
                        }
                    } else {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(this.ticksSinceBreak) + I["  ".length()] + this.ticksSinceArm + I["   ".length()] + cps, 1.0, " ".length() != 0);
                    }
                }
                this.ticksSinceBreak = "".length();
                "".length();
                if (4 <= 0) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                this.ticksSinceBreak = "".length();
            }
        }
    }

    private static void I() {
        I = new String[0x7F ^ 0x7B];
        AutoClickerG.I["".length()] = AutoClickerG.I("m1XvOLbWWXA=", "PmzaO");
        AutoClickerG.I[" ".length()] = AutoClickerG.I("kGx5Qh+eJM8keZIsWgBCzQ==", "mXfFU");
        AutoClickerG.I["  ".length()] = AutoClickerG.l("JnVmQi2Jrng=", "RWCHD");
        AutoClickerG.I["   ".length()] = AutoClickerG.lI("i", "IxGyk");
    }

    static {
        AutoClickerG.I();
    }
}

