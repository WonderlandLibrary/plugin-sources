/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInWindowClick
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
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import org.bukkit.entity.Player;

public class BadPacketsO
extends PacketCheck {
    private /* synthetic */ Long lastWindowClick;
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.lastWindowClick != null) {
                if (timestamp - this.lastWindowClick > 40L) {
                    AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(timestamp - this.lastWindowClick));
                }
                this.lastWindowClick = null;
                "".length();
                if (4 <= 2) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInWindowClick) {
            long lastWindowClick = playerData.getLastWindowClick();
            if (timestamp - lastWindowClick < 10L) {
                this.lastWindowClick = lastWindowClick;
                "".length();
                if (-1 != -1) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 1.0, 0.1);
            }
        }
    }

    private static void I() {
        I = new String["  ".length()];
        BadPacketsO.I["".length()] = BadPacketsO.I("AUXKVVD3YZI=", "DBRWd");
        BadPacketsO.I[" ".length()] = BadPacketsO.l("ebmCeyOA564=", "QLCuz");
    }

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

    public BadPacketsO() {
        super(Check.CheckType.BADPACKETSO, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastWindowClick = null;
        this.violations = -5.0;
        this.setBanwave(" ".length() != 0);
    }

    static {
        BadPacketsO.I();
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x84 ^ 0x8C), "DES");
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

