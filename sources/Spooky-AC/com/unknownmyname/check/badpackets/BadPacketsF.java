/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload
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
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class BadPacketsF
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int combo;

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
        BadPacketsF.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInCustomPayload) {
            PacketPlayInCustomPayload packetPlayInCustomPayload = (PacketPlayInCustomPayload)packet;
            String channel = packetPlayInCustomPayload.a();
            if (channel.equals(I["  ".length()]) || channel.equals(I["   ".length()])) {
                this.combo += "  ".length();
                if (this.combo > (0xB6 ^ 0xB2)) {
                    AlertsManager.getInstance().handleViolation(playerData, this, channel, this.combo / (0x4E ^ 0x4A));
                    if (this.violations > (double)this.getMaxViolation()) {
                        playerData.fuckOff();
                        "".length();
                        if (1 >= 3) {
                            throw null;
                        }
                    }
                }
            }
        } else if (packet instanceof PacketPlayInFlying) {
            this.combo -= Math.min(this.combo, " ".length());
        }
    }

    private static void I() {
        I = new String[0xAD ^ 0xA9];
        BadPacketsF.I["".length()] = BadPacketsF.I("RbHG34aMByo=", "YRxMD");
        BadPacketsF.I[" ".length()] = BadPacketsF.l("4HMat0QqhSCdRWRzG74eVg==", "HFswI");
        BadPacketsF.I["  ".length()] = BadPacketsF.l("NqgTRugkOSewGkz4QXr60g==", "gFqXB");
        BadPacketsF.I["   ".length()] = BadPacketsF.l("sXn8oywZj0MK3KUDWLM03w==", "FlHOf");
    }

    public BadPacketsF() {
        super(Check.CheckType.BADPACKETSF, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.combo = "".length();
        this.setMaxViolation(0x83 ^ 0x86);
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x5B ^ 0x53), "DES");
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

