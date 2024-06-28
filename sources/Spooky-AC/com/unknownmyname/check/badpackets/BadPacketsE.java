/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot
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
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import org.bukkit.entity.Player;

public class BadPacketsE
extends PacketCheck {
    private /* synthetic */ int stage;
    private static final /* synthetic */ String[] I;

    private static void I() {
        I = new String["   ".length()];
        BadPacketsE.I["".length()] = BadPacketsE.I("Bzf42/QmSX4=", "wQCrO");
        BadPacketsE.I[" ".length()] = BadPacketsE.I("yd182VrxsJQLpxizNDuMYFoIKXOIzrMX", "NFaeF");
        BadPacketsE.I["  ".length()] = BadPacketsE.l("oqPLpQgXr8s=", "aVCqQ");
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

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x52 ^ 0x5A), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        BadPacketsE.I();
    }

    public BadPacketsE() {
        super(Check.CheckType.BADPACKETSE, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.stage = "".length();
        this.setMaxViolation(0x61 ^ 0x6B);
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            this.stage = "".length();
            "".length();
            if (true <= false) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockPlace) {
            if (this.stage == " ".length()) {
                this.stage = "  ".length();
                "".length();
                if (3 <= 2) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInHeldItemSlot) {
            if (this.stage == "  ".length()) {
                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
            }
            this.stage = " ".length();
        }
    }
}

