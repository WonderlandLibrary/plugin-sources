/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.misc;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class MiscB
extends PacketCheck {
    private /* synthetic */ int stage;
    private static final /* synthetic */ String[] I;

    public MiscB() {
        super(Check.CheckType.MISCB, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.stage = "".length();
        this.violations = -2.5;
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
        MiscB.I["".length()] = MiscB.I("qEwXG4uGAqY=", "qWstt");
        MiscB.I[" ".length()] = MiscB.l("$<%\u0003'", "pSRfU");
        MiscB.I["  ".length()] = MiscB.l("", "sYnnM");
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
            if (-1 >= -1) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.stage == "  ".length() && !((PacketPlayInFlying)packet).g()) {
                this.stage = "   ".length();
                return;
            }
            if (this.stage == "   ".length() && playerData.getLocation().getY() % 1.0 == 0.41999998688697815) {
                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()], 1.25);
            }
            this.stage = "".length();
            "".length();
            if (4 <= 2) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockPlace) {
            PacketPlayInBlockPlace packetPlayInBlockPlace = (PacketPlayInBlockPlace)packet;
            if (packetPlayInBlockPlace.a().getX() != -" ".length() && packetPlayInBlockPlace.a().getY() != -" ".length() && packetPlayInBlockPlace.a().getZ() != -" ".length()) {
                this.stage = " ".length();
                this.violations -= Math.min(this.violations + 2.5, 0.25);
                "".length();
                if (1 >= 2) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInArmAnimation) {
            this.stage = "  ".length();
        }
    }

    static {
        MiscB.I();
    }
}

