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
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerJ
extends PacketCheck {
    private /* synthetic */ double value;
    private /* synthetic */ int combo;
    private /* synthetic */ int lastticks;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int abortTicks;
    private /* synthetic */ int ticks;

    static {
        AutoClickerJ.I();
    }

    private static void I() {
        I = new String["   ".length()];
        AutoClickerJ.I["".length()] = AutoClickerJ.I("=", "wXNuz");
        AutoClickerJ.I[" ".length()] = AutoClickerJ.I("\u000f\r:\u0006p\r\u0014'\n;+\n", "NxNiP");
        AutoClickerJ.I["  ".length()] = AutoClickerJ.I("", "NbDoP");
    }

    public AutoClickerJ() {
        super(Check.CheckType.AUTO_CLICKERJ, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.ticks = "".length();
        this.abortTicks = "".length();
        this.lastticks = -" ".length();
        this.combo = "".length();
        this.value = 0.0;
        this.violations = 0.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (playerData.isDigging() && !playerData.isAbortedDigging()) {
                this.ticks += " ".length();
            }
            this.abortTicks += " ".length();
            "".length();
            if (-1 >= 3) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                this.ticks = "".length();
                "".length();
                if (4 <= 2) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                BlockPosition blockPosition = packetPlayInBlockDig.a();
                EnumDirection enumDirection = packetPlayInBlockDig.b();
                if (blockPosition.equals((Object)playerData.getDiggingBlock()) && !enumDirection.equals((Object)playerData.getDiggingBlockFace())) {
                    if (this.ticks == this.lastticks && this.abortTicks < (0x9E ^ 0x9A)) {
                        this.value += 0.3 + (double)this.combo * 0.2;
                        if (this.value > 25.0) {
                            AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                            this.value = 0.0;
                        }
                        this.combo += " ".length();
                        "".length();
                        if (3 <= 0) {
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
            if (false < true) continue;
            throw null;
        }
        return sb.toString();
    }
}

