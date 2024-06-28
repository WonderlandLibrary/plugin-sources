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
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class BadPacketsJ
extends PacketCheck {
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying && (double)((PacketPlayInFlying)packet).d() == -8.0E307) {
            AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
            playerData.fuckOff();
        }
    }

    private static void I() {
        I = new String["   ".length()];
        BadPacketsJ.I["".length()] = BadPacketsJ.I("nhhM8PWLPUw=", "hVuLq");
        BadPacketsJ.I[" ".length()] = BadPacketsJ.l("4\u0012\u001b:1\u00154\u001b-'\u000f\u0012\u001b", "gwiLT");
        BadPacketsJ.I["  ".length()] = BadPacketsJ.l("", "mTDeI");
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
        BadPacketsJ.I();
    }

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
            if (true) continue;
            throw null;
        }
        return sb.toString();
    }

    public BadPacketsJ() {
        super(Check.CheckType.BADPACKETSJ, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
    }
}

