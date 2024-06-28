/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
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
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class BadPacketsM
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int stage;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            int n;
            if (this.stage == "  ".length()) {
                this.violations -= Math.min(this.violations + 1.0, 0.01);
            }
            if (this.stage == " ".length()) {
                n = "  ".length();
                "".length();
                if (!true) {
                    throw null;
                }
            } else {
                n = "".length();
            }
            this.stage = n;
            "".length();
            if (3 != 3) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK) {
                this.stage = " ".length();
                "".length();
                if (3 == 2) {
                    throw null;
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK && this.stage == "  ".length()) {
                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                this.stage = "".length();
            }
        }
    }

    static {
        BadPacketsM.I();
    }

    private static void I() {
        I = new String["   ".length()];
        BadPacketsM.I["".length()] = BadPacketsM.I("0", "qJLsa");
        BadPacketsM.I[" ".length()] = BadPacketsM.I("-106\u000f\u00195\")", "kPCBM");
        BadPacketsM.I["  ".length()] = BadPacketsM.l("UTPtFUb0JdQ=", "RIrGY");
    }

    private static String I(String obj, String key) {
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
            if (4 >= -1) continue;
            throw null;
        }
        return sb.toString();
    }

    public BadPacketsM() {
        super(Check.CheckType.BADPACKETSM, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.stage = "".length();
        this.violations = -1.0;
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
}

