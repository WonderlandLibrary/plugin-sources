/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerI
extends PacketCheck {
    private /* synthetic */ Integer releaseTime;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Queue<Integer> clickQueue;
    private /* synthetic */ int start;

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
            if (4 >= 0) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String["  ".length()];
        AutoClickerI.I["".length()] = AutoClickerI.I("(", "asYKW");
        AutoClickerI.I[" ".length()] = AutoClickerI.I("\u001b\u000f!%y\u0019\u0016<)2?\b", "ZzUJY");
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (playerData.isDigging()) {
                this.start += " ".length();
            }
            if (this.releaseTime != null) {
                this.releaseTime = this.releaseTime + " ".length();
                "".length();
                if (2 < 2) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                this.releaseTime = "".length();
                "".length();
                if (2 >= 3) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK && this.releaseTime != null && this.releaseTime < (0xA7 ^ 0xA3) && this.releaseTime > 0) {
                this.clickQueue.add(this.releaseTime);
                if (this.clickQueue.size() > (0xBF ^ 0xB5)) {
                    double value = MathUtil.variance(" ".length(), this.clickQueue) / (double)this.start;
                    if (value > 0.2) {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(value), 1.0, " ".length() != 0);
                        "".length();
                        if (1 < -1) {
                            throw null;
                        }
                    } else {
                        this.violations -= Math.min(this.violations + 1.0, 0.25);
                    }
                    this.clickQueue.clear();
                    this.start = "".length();
                }
                this.releaseTime = null;
            }
        }
    }

    public AutoClickerI() {
        super(Check.CheckType.AUTO_CLICKERI, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.releaseTime = null;
        this.clickQueue = new ConcurrentLinkedQueue<Integer>();
        this.start = "".length();
        this.violations = -1.0;
    }

    static {
        AutoClickerI.I();
    }
}

