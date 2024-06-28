/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.autoclicker;

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
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerB
extends PacketCheck {
    private /* synthetic */ boolean initalSwing;
    private /* synthetic */ boolean swing;
    private /* synthetic */ int stage;
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInArmAnimation) {
            if (this.stage == " ".length()) {
                this.stage = "  ".length();
                "".length();
                if (3 < 2) {
                    throw null;
                }
            } else if (this.stage == "   ".length() || this.stage == 0) {
                this.swing = " ".length();
                "".length();
                if (1 >= 3) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                if (this.stage == 0) {
                    this.stage = " ".length();
                    this.initalSwing = this.swing;
                    "".length();
                    if (1 < -1) {
                        throw null;
                    }
                } else {
                    this.stage = "".length();
                    "".length();
                    if (3 < 2) {
                        throw null;
                    }
                }
            } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                if (this.stage == "   ".length()) {
                    if (this.swing) {
                        AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()], 1.0, " ".length() != 0);
                        "".length();
                        if (!true) {
                            throw null;
                        }
                    } else if (this.initalSwing) {
                        this.violations -= Math.min(this.violations + 5.0, 0.2);
                    }
                }
                this.stage = "".length();
                "".length();
                if (false < false) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInFlying) {
            if (this.stage == "  ".length()) {
                this.stage = "   ".length();
            }
            this.swing = "".length();
        }
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

    private static String lI(String obj, String key) {
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
            if (4 > 0) continue;
            throw null;
        }
        return sb.toString();
    }

    public AutoClickerB() {
        super(Check.CheckType.AUTO_CLICKERB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.stage = "".length();
        this.swing = "".length();
        this.initalSwing = "".length();
        this.setMaxViolation(0x2C ^ 0x26);
        this.violations = -5.0;
    }

    private static void I() {
        I = new String["   ".length()];
        AutoClickerB.I["".length()] = AutoClickerB.I("Ssx0shxCq+U=", "ezHbq");
        AutoClickerB.I[" ".length()] = AutoClickerB.l("et9SNQPAhzx2bfXpxG69GQ==", "ObAih");
        AutoClickerB.I["  ".length()] = AutoClickerB.lI("", "HNKdr");
    }

    static {
        AutoClickerB.I();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xC9 ^ 0xC1), "DES");
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

