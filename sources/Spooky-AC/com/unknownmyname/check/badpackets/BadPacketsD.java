/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Entity
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BadPacketsD
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Float lastPitch;
    private /* synthetic */ Float lastYaw;

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
            if (3 > -1) continue;
            throw null;
        }
        return sb.toString();
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x57 ^ 0x5F), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        PacketPlayInFlying packetPlayInFlying;
        if (packet instanceof PacketPlayInFlying && (packetPlayInFlying = (PacketPlayInFlying)packet).h()) {
            if (this.lastYaw != null && this.lastPitch != null && player.getVehicle() == null && this.lastYaw.floatValue() == packetPlayInFlying.d() && this.lastPitch.floatValue() == packetPlayInFlying.e() && playerData.getTeleportTicks() > playerData.getPingTicks() * "  ".length()) {
                AlertsManager.getInstance().handleViolation(playerData, this, this.lastYaw + I["  ".length()] + this.lastPitch);
            }
            this.lastYaw = Float.valueOf(packetPlayInFlying.d());
            this.lastPitch = Float.valueOf(packetPlayInFlying.e());
        }
    }

    static {
        BadPacketsD.I();
    }

    public BadPacketsD() {
        super(Check.CheckType.BADPACKETSD, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastYaw = null;
        this.lastPitch = null;
        this.setMaxViolation(0x89 ^ 0x8C);
    }

    private static void I() {
        I = new String["   ".length()];
        BadPacketsD.I["".length()] = BadPacketsD.I("XgVOu31gKNk=", "VnNRv");
        BadPacketsD.I[" ".length()] = BadPacketsD.I("Teh7Lvh0xV8=", "YSoXd");
        BadPacketsD.I["  ".length()] = BadPacketsD.l("g", "GQbIE");
    }
}

