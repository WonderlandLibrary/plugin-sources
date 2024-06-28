/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.GameMode
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
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class BadPacketsI
extends PacketCheck {
    private /* synthetic */ Long lastFlying;
    private static final /* synthetic */ String[] I;

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
        BadPacketsI.I["".length()] = BadPacketsI.I("4ilHLp9GavM=", "ZxLuR");
        BadPacketsI.I[" ".length()] = BadPacketsI.I("xuZNTBsqw7zI3b4qXjZONg==", "PyRgr");
    }

    public BadPacketsI() {
        super(Check.CheckType.BADPACKETSI, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastFlying = null;
        this.setMaxViolation(9 ^ 0xC);
        this.violations = -1.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.lastFlying != null) {
                if (timestamp - this.lastFlying > 40L && player.getGameMode() == GameMode.SURVIVAL) {
                    AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(timestamp - this.lastFlying), 0.25);
                }
                this.lastFlying = null;
                "".length();
                if (4 <= 3) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInBlockPlace) {
            long lastFlying = playerData.getLastFlying();
            if (timestamp - lastFlying < 10L) {
                this.lastFlying = lastFlying;
                "".length();
                if (0 == 3) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 1.0, 0.025);
            }
        }
    }

    static {
        BadPacketsI.I();
    }
}

