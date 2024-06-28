/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive
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
import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;
import org.bukkit.entity.Player;

public class BadPacketsK
extends PacketCheck {
    private /* synthetic */ Integer lastKeepAlive;
    private static final /* synthetic */ String[] I;

    public BadPacketsK() {
        super(Check.CheckType.BADPACKETSK, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.setMaxViolation(0x35 ^ 0x3F);
        this.lastKeepAlive = null;
    }

    private static void I() {
        I = new String["  ".length()];
        BadPacketsK.I["".length()] = BadPacketsK.I("bs//HaKZ+tA=", "AvzUn");
        BadPacketsK.I[" ".length()] = BadPacketsK.l("utt/WVTLE1GD1J60GPqzJg==", "ZvdAZ");
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x8C ^ 0x84), "DES");
            Cipher des = Cipher.getInstance("DES");
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

    static {
        BadPacketsK.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInKeepAlive) {
            PacketPlayInKeepAlive packetPlayInKeepAlive = (PacketPlayInKeepAlive)packet;
            if (this.lastKeepAlive != null && this.lastKeepAlive > packetPlayInKeepAlive.a() && playerData.getTotalTicks() > (0x59 ^ 0x3D) && !playerData.hasLag()) {
                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(this.lastKeepAlive - packetPlayInKeepAlive.a()));
            }
            this.lastKeepAlive = packetPlayInKeepAlive.a();
        }
    }
}

