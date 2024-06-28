/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.badpackets;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class BadPacketsL
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int placed;

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
            if (4 == 4) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        PacketPlayInBlockPlace packetPlayInBlockPlace;
        if (packet instanceof PacketPlayInFlying) {
            this.placed = "".length();
            "".length();
            if (4 <= -1) {
                throw null;
            }
        } else if (!(!(packet instanceof PacketPlayInBlockPlace) || (packetPlayInBlockPlace = (PacketPlayInBlockPlace)packet).a().getX() != -" ".length() || packetPlayInBlockPlace.a().getY() != -" ".length() && packetPlayInBlockPlace.a().getY() != 71 + 227 - 179 + 136 || packetPlayInBlockPlace.a().getZ() != -" ".length() || packetPlayInBlockPlace.d() != 0.0f || packetPlayInBlockPlace.e() != 0.0f || packetPlayInBlockPlace.f() != 0.0f || packetPlayInBlockPlace.getFace() != 169 + 4 - -36 + 46 || packetPlayInBlockPlace.getItemStack() == null || playerData.hasLag() || playerData.hasFast())) {
            int n = this.placed;
            this.placed = n + " ".length();
            if (n > 0) {
                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(this.placed), this.placed - " ".length());
                "".length();
                if (2 <= 0) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 1.0, 1.0);
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

    private static void I() {
        I = new String["  ".length()];
        BadPacketsL.I["".length()] = BadPacketsL.I("2vVZzeK4RTg=", "fgatf");
        BadPacketsL.I[" ".length()] = BadPacketsL.l("/0\u0016\u0002\r76\u001f", "bEzvd");
    }

    public BadPacketsL() {
        super(Check.CheckType.BADPACKETSL, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.placed = "".length();
        this.violations = -1.0;
    }

    static {
        BadPacketsL.I();
    }
}

