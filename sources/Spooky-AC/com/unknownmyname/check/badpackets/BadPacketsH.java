/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.ItemStack
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
import com.unknownmyname.util.MaterialList;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class BadPacketsH
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ boolean placed;

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x17 ^ 0x1F), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BadPacketsH() {
        super(Check.CheckType.BADPACKETSH, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.placed = "".length();
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
        I = new String["   ".length()];
        BadPacketsH.I["".length()] = BadPacketsH.I("zPgArViMfVA=", "qhZRM");
        BadPacketsH.I[" ".length()] = BadPacketsH.I("IGNEjeeem1s=", "slLPc");
        BadPacketsH.I["  ".length()] = BadPacketsH.l("iwHrn4D5Jww=", "uGcPJ");
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        PacketPlayInBlockPlace packetPlayInBlockPlace;
        if (packet instanceof PacketPlayInFlying) {
            if (this.placed && playerData.getSprinting() != null && playerData.getSprinting().booleanValue()) {
                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
            }
            this.placed = "".length();
            "".length();
            if (4 == 3) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockPlace && player.getGameMode() != GameMode.CREATIVE && (packetPlayInBlockPlace = (PacketPlayInBlockPlace)packet).a().getX() == -" ".length() && (packetPlayInBlockPlace.a().getY() == -" ".length() || packetPlayInBlockPlace.a().getY() == 224 + 202 - 172 + 1) && packetPlayInBlockPlace.a().getZ() == -" ".length() && packetPlayInBlockPlace.d() == 0.0f && packetPlayInBlockPlace.e() == 0.0f && packetPlayInBlockPlace.f() == 0.0f && packetPlayInBlockPlace.getFace() == 14 + 135 - 31 + 137 && packetPlayInBlockPlace.getItemStack() != null && MaterialList.canPlace(packetPlayInBlockPlace.getItemStack())) {
            this.placed = " ".length();
        }
    }

    static {
        BadPacketsH.I();
    }
}

