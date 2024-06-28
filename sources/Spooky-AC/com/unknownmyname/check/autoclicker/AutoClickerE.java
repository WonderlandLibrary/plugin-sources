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
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerE
extends PacketCheck {
    private /* synthetic */ int ticks;
    private /* synthetic */ Queue<Integer> tickList;
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (playerData.isDigging() && !playerData.isAbortedDigging()) {
                this.ticks += " ".length();
                "".length();
                if (4 != 4) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                this.ticks = "".length();
                "".length();
                if (true <= false) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                BlockPosition blockPosition = packetPlayInBlockDig.a();
                EnumDirection enumDirection = packetPlayInBlockDig.b();
                if (blockPosition.equals((Object)playerData.getDiggingBlock()) && !enumDirection.equals((Object)playerData.getDiggingBlockFace())) {
                    this.tickList.add(this.ticks);
                    if (this.tickList.size() > (0x6B ^ 0x43)) {
                        double deviation = MathUtil.deviation(this.tickList);
                        if (deviation < 0.325) {
                            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(deviation), 1.325 - deviation, " ".length() != 0);
                            "".length();
                            if (3 == 2) {
                                throw null;
                            }
                        } else {
                            this.violations -= Math.min(this.violations + 2.0, 0.25);
                        }
                        this.tickList.clear();
                    }
                }
                this.ticks = "".length();
            }
        }
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xB ^ 3), "DES");
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
        AutoClickerE.I["".length()] = AutoClickerE.I("N8D1HJa10ck=", "xKeqr");
        AutoClickerE.I[" ".length()] = AutoClickerE.I("TS+xSFAo6swVFd2J/zr1lQ==", "PjZLX");
    }

    public AutoClickerE() {
        super(Check.CheckType.AUTO_CLICKERE, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.ticks = "".length();
        this.tickList = new ConcurrentLinkedQueue<Integer>();
        this.setMaxViolation(267 + 212 - 296 + 117);
        this.violations = -2.0;
    }

    static {
        AutoClickerE.I();
    }
}

