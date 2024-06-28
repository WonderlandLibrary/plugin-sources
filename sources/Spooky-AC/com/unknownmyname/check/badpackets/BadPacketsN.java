/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInEntityAction
 *  net.minecraft.server.v1_8_R3.PacketPlayInEntityAction$EnumPlayerAction
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
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
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.Player;

public class BadPacketsN
extends PacketCheck {
    private /* synthetic */ int ticks;
    private /* synthetic */ int stage;
    private static final /* synthetic */ String[] I;

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
            if (0 < 3) continue;
            throw null;
        }
        return sb.toString();
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

    public BadPacketsN() {
        super(Check.CheckType.BADPACKETSM, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.ticks = "".length();
        this.stage = "".length();
    }

    static {
        BadPacketsN.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.stage == "  ".length()) {
                this.ticks += " ".length();
                "".length();
                if (2 >= 4) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInUseEntity) {
            this.stage = " ".length();
            "".length();
            if (4 <= 3) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInEntityAction) {
            PacketPlayInEntityAction packetPlayInEntityAction = (PacketPlayInEntityAction)packet;
            if (packetPlayInEntityAction.b() == PacketPlayInEntityAction.EnumPlayerAction.START_SPRINTING) {
                int n;
                if (this.stage == " ".length()) {
                    n = "  ".length();
                    "".length();
                    if (1 >= 2) {
                        throw null;
                    }
                } else {
                    n = "".length();
                }
                this.stage = n;
                "".length();
                if (3 >= 4) {
                    throw null;
                }
            } else if (packetPlayInEntityAction.b() == PacketPlayInEntityAction.EnumPlayerAction.STOP_SLEEPING) {
                if (this.stage == "  ".length()) {
                    AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(this.ticks));
                }
                this.ticks = "".length();
                this.stage = "".length();
            }
        }
    }

    private static void I() {
        I = new String["  ".length()];
        BadPacketsN.I["".length()] = BadPacketsN.I("UQE2SSuitbA=", "qpbxO");
        BadPacketsN.I[" ".length()] = BadPacketsN.l("'_;#2", "proBB");
    }
}

