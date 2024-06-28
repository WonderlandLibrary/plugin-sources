/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity$EnumEntityUseAction
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.killaura;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.Player;

public class KillAuraC
extends PacketCheck {
    private /* synthetic */ boolean dig;
    private static final /* synthetic */ String[] I;

    static {
        KillAuraC.I();
    }

    private static void I() {
        I = new String["   ".length()];
        KillAuraC.I["".length()] = KillAuraC.I("\u0017", "TYGaV");
        KillAuraC.I[" ".length()] = KillAuraC.I("\u0007;\u000b.89 \u0006", "LRgBy");
        KillAuraC.I["  ".length()] = KillAuraC.I("", "WZUrJ");
    }

    public KillAuraC() {
        super(Check.CheckType.KILL_AURAC, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.dig = "".length();
        this.setMaxViolation(0x71 ^ 0x7E);
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
            if (3 > 1) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            this.dig = "".length();
            "".length();
            if (2 >= 4) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() != PacketPlayInBlockDig.EnumPlayerDigType.DROP_ITEM && packetPlayInBlockDig.c() != PacketPlayInBlockDig.EnumPlayerDigType.DROP_ALL_ITEMS) {
                this.dig = " ".length();
                "".length();
                if (1 >= 3) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInUseEntity && this.dig && ((PacketPlayInUseEntity)packet).a() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
            AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()], 1.0, " ".length() != 0);
        }
    }
}

