/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity$EnumEntityUseAction
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.killaura;

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
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.Player;

public class KillAuraA
extends PacketCheck {
    private /* synthetic */ int invalidTicks;
    private /* synthetic */ int totalTicks;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Integer lastTicks;
    private /* synthetic */ int ticks;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        PacketPlayInUseEntity wrappedPacketPlayInUseEntity;
        if (packet instanceof PacketPlayInFlying) {
            this.ticks += " ".length();
            "".length();
            if (!true) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInUseEntity && (wrappedPacketPlayInUseEntity = (PacketPlayInUseEntity)packet).a() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
            if (this.ticks <= (0x6A ^ 0x62)) {
                if (this.lastTicks != null) {
                    if (this.lastTicks == this.ticks) {
                        this.invalidTicks += " ".length();
                    }
                    this.totalTicks += " ".length();
                    if (this.totalTicks >= (0x1F ^ 6)) {
                        if (this.invalidTicks > (0x9E ^ 0x88)) {
                            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(this.invalidTicks), 1.0 + (double)(this.invalidTicks - (3 ^ 0x15)) / 6.0);
                            "".length();
                            if (4 <= -1) {
                                throw null;
                            }
                        } else {
                            this.violations -= Math.min(this.violations + 1.0, 1.0);
                        }
                        this.invalidTicks = "".length();
                        this.totalTicks = "".length();
                    }
                }
                this.lastTicks = this.ticks;
            }
            this.ticks = "".length();
        }
    }

    public KillAuraA() {
        super(Check.CheckType.KILL_AURAA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.ticks = "".length();
        this.lastTicks = null;
        this.invalidTicks = "".length();
        this.totalTicks = "".length();
        this.setMaxViolation(0xA4 ^ 0xAE);
        this.violations = -1.0;
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
            if (2 < 4) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String["  ".length()];
        KillAuraA.I["".length()] = KillAuraA.I("hKaLYdOsoBk=", "YUKCN");
        KillAuraA.I[" ".length()] = KillAuraA.l("\u000e\u0010\n\u0015\"0\u000b\u0007", "Eyfyc");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x11 ^ 0x19), "DES");
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
        KillAuraA.I();
    }
}

