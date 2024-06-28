/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
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
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class MiscC
extends PacketCheck {
    private /* synthetic */ int stage;
    private /* synthetic */ long lastFlying;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int moves;

    public MiscC() {
        super(Check.CheckType.MISCC, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastFlying = 0L;
        this.moves = "".length();
        this.stage = "".length();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x9B ^ 0x93), "DES");
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
        if (packet instanceof PacketPlayInFlying) {
            int n;
            int n2;
            PacketPlayInFlying packetPlayInFlying = (PacketPlayInFlying)packet;
            if (this.stage == "   ".length() && timestamp - this.lastFlying <= 3L) {
                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
            }
            if (packetPlayInFlying.h() && !packetPlayInFlying.g() && this.moves > "  ".length() && timestamp - this.lastFlying > 40L) {
                n2 = " ".length();
                "".length();
                if (1 >= 2) {
                    throw null;
                }
            } else {
                n2 = this.stage = "".length();
            }
            if (packetPlayInFlying.g()) {
                n = this.moves = this.moves + " ".length();
                "".length();
                if (1 >= 4) {
                    throw null;
                }
            } else {
                n = "".length();
            }
            this.moves = n;
            this.lastFlying = timestamp;
            "".length();
            if (-1 >= 1) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockPlace) {
            if (this.stage == " ".length()) {
                this.stage = "  ".length();
                "".length();
                if (3 <= 0) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInArmAnimation && this.stage == "  ".length()) {
            this.stage = "   ".length();
        }
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
            if (2 >= 0) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        MiscC.I();
    }

    private static void I() {
        I = new String["   ".length()];
        MiscC.I["".length()] = MiscC.I(".", "odnev");
        MiscC.I[" ".length()] = MiscC.l("2DH2RusVawki60w5kiNiFw==", "bTXuG");
        MiscC.I["  ".length()] = MiscC.l("Rv4cDyK1UTA=", "MmiYS");
    }
}

