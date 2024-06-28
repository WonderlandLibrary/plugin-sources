/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
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
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class BadPacketsB
extends PacketCheck {
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            double d;
            PacketPlayInFlying packetPlayInFlying = (PacketPlayInFlying)packet;
            double pitch = Math.abs(packetPlayInFlying.e());
            if (Math.abs(d) > 90.0) {
                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(pitch), 1.0, " ".length() != 0);
            }
        }
    }

    private static void I() {
        I = new String["  ".length()];
        BadPacketsB.I["".length()] = BadPacketsB.I("50IWZnD9pJc=", "MXMVK");
        BadPacketsB.I[" ".length()] = BadPacketsB.I("0FaIkjZj6rdyonDBrv1aKg==", "tXDIy");
    }

    static {
        BadPacketsB.I();
    }

    public BadPacketsB() {
        super(Check.CheckType.BADPACKETSB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.setMaxViolation(" ".length());
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x83 ^ 0x8B), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

