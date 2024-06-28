/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.EnumDirection
 *  net.minecraft.server.v1_8_R3.Packet
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
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerF
extends PacketCheck {
    private /* synthetic */ int abortTicks;
    private /* synthetic */ int ticks;
    private /* synthetic */ double value;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int combo;
    private /* synthetic */ int lastticks;

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
            if (1 < 4) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (playerData.isDigging() && !playerData.isAbortedDigging()) {
                this.ticks += " ".length();
            }
            this.abortTicks += " ".length();
            "".length();
            if (true < true) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                this.ticks = "".length();
                "".length();
                if (4 <= 1) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                BlockPosition blockPosition = packetPlayInBlockDig.a();
                EnumDirection enumDirection = packetPlayInBlockDig.b();
                if (blockPosition.equals((Object)playerData.getDiggingBlock()) && !enumDirection.equals((Object)playerData.getDiggingBlockFace())) {
                    if (this.ticks == this.lastticks && this.abortTicks < (0xC4 ^ 0xC0)) {
                        this.value += 0.3 + (double)this.combo * 0.2;
                        if (this.value > 20.0) {
                            AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                            this.value = 0.0;
                            "".length();
                            if (-1 < -1) {
                                throw null;
                            }
                        } else {
                            this.violations -= Math.min(this.violations + 2.0, 0.1);
                        }
                        this.combo += " ".length();
                        "".length();
                        if (3 < 1) {
                            throw null;
                        }
                    } else {
                        this.value -= Math.min(this.value, 1.0);
                        this.combo = "".length();
                    }
                    this.lastticks = this.ticks;
                }
                this.abortTicks = "".length();
                this.ticks = "".length();
            }
        }
    }

    private static void I() {
        I = new String["   ".length()];
        AutoClickerF.I["".length()] = AutoClickerF.I("\b>, /", "APhEY");
        AutoClickerF.I[" ".length()] = AutoClickerF.l("FOIg1kAoAyOYa8dWpW8LEw==", "aSbpn");
        AutoClickerF.I["  ".length()] = AutoClickerF.l("z1QI41tdf3M=", "bBTlB");
    }

    public AutoClickerF() {
        super(Check.CheckType.AUTO_CLICKERINDEV, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.ticks = "".length();
        this.abortTicks = "".length();
        this.lastticks = -" ".length();
        this.combo = "".length();
        this.value = 0.0;
        this.violations = -2.0;
    }

    private static String l(String obj, String key) {
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

    static {
        AutoClickerF.I();
    }
}

